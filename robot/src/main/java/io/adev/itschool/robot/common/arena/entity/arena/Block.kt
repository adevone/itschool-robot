package io.adev.itschool.robot.common.arena.entity.arena

import io.adev.itschool.robot.common.arena.entity.Position
import io.adev.itschool.robot.common.arena.entity.RobotState
import io.adev.itschool.robot.common.arena.entity.Size
import io.adev.itschool.robot.common.arena.entity.SizePoint
import io.adev.itschool.robot.common.arena.entity.vp
import kotlin.math.absoluteValue
import kotlin.random.Random

sealed class Block(
    val position: Position,
) : RobotStateMutationsProvider, RobotState.Source {

    abstract val asset: Asset

    open val requiresKey: Boolean = false

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
        return this.javaClass == other?.javaClass && other is Block &&
                this.position == other.position
    }

    override fun hashCode(): Int {
        return position.hashCode()
    }
}

sealed class Asset {
    object Void : Asset()
    object Platform : Asset()
    object Target : Asset()
    object CheckKey : Asset()
    data class Password(val password: String) : Asset()
    data class Code(val randomCode: Int) : Asset()
    object CheckCode : Asset()
}

class VoidBlock(position: Position) : Block(position) {
    override val asset = Asset.Void
}

class PlatformBlock(position: Position) : Block(position) {
    override val asset = Asset.Platform

    override fun beforeRobotMove(robotState: RobotState): RobotState? {
        return if (robotState.position == position)
            robotState.destroyed().withSource(source = this)
        else
            null
    }
}

class TargetBlock(position: Position) : Block(position) {
    override val asset = Asset.Target

    override fun afterRobotMove(robotState: RobotState): RobotState? {
        return if (robotState.position == position) {
            robotState.won().withSource(source = this)
        } else {
            null
        }
    }
}


open class CheckKeyBlock(position: Position) : Block(position) {
    override val asset: Asset = Asset.CheckKey

    override val requiresKey = true

    override fun beforeRobotMove(robotState: RobotState): RobotState? {
        return if (robotState.position == position && !robotState.isKeyValid()) {
            robotState.destroyed().withSource(this)
        } else {
            null
        }
    }

    override fun sourceRepresentation(): String {
        return "Key is not entered. ${super.sourceRepresentation()}"
    }
}

class MaybeCheckKeyBlock(position: Position) : CheckKeyBlock(position) {
    private val needCheck = System.currentTimeMillis().absoluteValue % 5 == 0L // Шанс 1/5

    override val requiresKey = needCheck
    override val asset: Asset = if (needCheck) Asset.CheckKey else Asset.Void

    override fun beforeRobotMove(robotState: RobotState): RobotState? {
        return if (needCheck)
            super.beforeRobotMove(robotState)
        else
            null
    }
}

class PasswordBlock(position: Position) : Block(position) {
    private val password = position.hash()

    override val asset = Asset.Password(password)

    override fun beforeRobotMove(robotState: RobotState): RobotState? {
        return if (robotState.position == position && robotState.text != password)
            robotState.destroyed().withSource(source = this)
        else
            null
    }
}

class RandomCodeBlock(position: Position) : Block(position) {
    val randomCode = Random.nextInt().absoluteValue % 10

    override val asset = Asset.Code(randomCode)

    override fun afterRobotMove(robotState: RobotState): RobotState? {
        return if (robotState.position == position)
            robotState.withCode(randomCode)
        else
            null
    }
}

class CheckCodeBlock(position: Position) : Block(position) {
    override val asset = Asset.CheckCode

    override fun beforeRobotMove(robotState: RobotState): RobotState? {
        return if (robotState.position == position)
            robotState.also { state ->
                state.checkCode()
            }
        else
            null
    }
}
