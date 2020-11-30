package io.adev.itschool.robot.common.arena.entity

abstract class Block(
    val position: Position
) : RobotStateMutationsProvider, RobotState.Source {

    abstract val texture: Texture?
    val size = Size.Virtual(width = 1.vp, height = 1.vp)

    val horEnd: SizePoint.Virtual get() = position.x + size.width
    val verEnd: SizePoint.Virtual get() = position.y + size.height

    override fun toString(): String {
        return "${this::class.simpleName} $position $size"
    }

    override fun sourceRepresentation(): String {
        return "${this::class.simpleName} $position $size"
    }

    override fun equals(other: Any?): Boolean {
        return other is Block &&
                this.position == other.position &&
                this.texture == other.texture
    }

    override fun hashCode(): Int {
        var result = position.hashCode()
        result = 31 * result + (texture?.hashCode() ?: 0)
        return result
    }

    enum class Texture {
        Platform,
        Target
    }
}

class PlatformBlock(position: Position) : Block(position) {
    override val texture = Texture.Platform

    override fun beforeRobotMove(robotState: RobotState): RobotState? {
        return if (robotState.position == position)
            robotState.destroyed(source = this)
        else
            null
    }

    override fun equals(other: Any?): Boolean {
        return other is PlatformBlock && super.equals(other)
    }

    @Suppress("RedundantOverride")
    override fun hashCode(): Int {
        return super.hashCode()
    }
}

class TargetBlock(position: Position) : Block(position) {
    override val texture = Texture.Target

    override fun afterRobotMove(robotState: RobotState): RobotState? {
        return if (robotState.position == position) {
            robotState.won(source = this)
        } else {
            null
        }
    }

    override fun equals(other: Any?): Boolean {
        return other is TargetBlock && super.equals(other)
    }

    @Suppress("RedundantOverride")
    override fun hashCode(): Int {
        return super.hashCode()
    }
}

class VoidBlock(position: Position) : Block(position) {
    override val texture: Texture? = null
}