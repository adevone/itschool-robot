package io.adev.itschool.robot.common.arena.entity

data class Arena(
    val initialRobotState: RobotState,
    private val nonVoidBlocks: List<Block>
) : RobotStateMutationsProvider, RobotState.Source {

    val size = Size.Virtual(
        width = nonVoidBlocks.maxByOrNull { block -> block.horEnd }?.horEnd ?: 1.vp,
        height = nonVoidBlocks.maxByOrNull { block -> block.verEnd }?.verEnd ?: 1.vp
    )

    val blocks: List<Block> = size.height.rangeTo().flatMap { lineIndex ->
        size.width.rangeTo().map { rowIndex ->
            val currentPosition = Position(x = rowIndex.vp, y = lineIndex.vp)
            val block = nonVoidBlocks.find { it.position == currentPosition }
            block ?: VoidBlock(position = currentPosition)
        }
    }

    fun blockOn(position: Position): Block? {
        return blocks.find { it.position == position }
    }

    fun robotStartBlock(): Block {
        val block = blockOn(initialRobotState.position)
        return requireNotNull(block)
    }

    fun calculatePointSize(screenSize: Size.Real): Float {
        return if (size.ratio > screenSize.ratio) {
            screenSize.height / size.height
        } else {
            screenSize.width / size.width
        }
    }

    override fun beforeRobotMove(robotState: RobotState): RobotState? {
        if (!robotState.position.isIn(size)) {
            return robotState.destroyed(source = this)
        } else {
            blocks.forEach { block ->
                val newState = block.beforeRobotMove(robotState)
                if (newState != null) {
                    return newState
                }
            }
            return null
        }
    }

    override fun afterRobotMove(robotState: RobotState): RobotState? {
        blocks.forEach { block ->
            val newState = block.afterRobotMove(robotState)
            if (newState != null) {
                return newState
            }
        }
        return null
    }

    override fun sourceRepresentation(): String {
        return this::class.simpleName.toString()
    }

    override fun toString(): String {
        return size.height.rangeTo().joinToString(separator = "\n") { lineIndex ->
            size.width.rangeTo().map { rowIndex ->
                val currentPosition = Position(x = lineIndex.vp, y = rowIndex.vp)
                when (val block = blocks.first { it.position == currentPosition }) {
                    is VoidBlock -> ' '
                    is PlatformBlock -> 'p'
                    is TargetBlock -> 't'
                    else -> IllegalArgumentException("block can not be $block")
                }
            }.joinToString(separator = "")
        }
    }
}

/**
 * 'r': initial robot position
 * 'p': platform block
 * 't': target
 * ' ': void
 */
fun String.parseArena(): Arena {
    var initialRobotPosition: Position? = null
    val blocks = mutableListOf<Block>()
    this.lines().forEachIndexed { lineIndex, line ->
        line.forEachIndexed { charIndex, char ->
            val position = Position(x = charIndex.vp, y = lineIndex.vp)
            when (char) {
                'r' -> initialRobotPosition = position
                'p' -> blocks.add(PlatformBlock(position))
                't' -> blocks.add(TargetBlock(position))
                ' ' -> Unit // skip
                else -> throw IllegalArgumentException("char can not be '$char' position=$position")
            }
        }
    }
    return Arena(
        initialRobotState = RobotState(
            position = initialRobotPosition ?: throw IllegalArgumentException("no 'r' in draw")
        ),
        nonVoidBlocks = blocks
    )
}