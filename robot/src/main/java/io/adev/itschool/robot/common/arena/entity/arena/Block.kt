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

class AuthBlock(position: Position) : Block(position) {

    override fun beforeRobotMove(robotState: RobotState): RobotState? {
        return if (robotState.position == position)
            robotState.also { state ->
                state.checkToken()
            }
        else
            null
    }
}

class PasswordBlock(position: Position) : Block(position) {

    val password = position.hash()

    override fun beforeRobotMove(robotState: RobotState): RobotState? {
        return if (robotState.position == position && robotState.text != password)
            robotState.destroyed(source = this)
        else
            null
    }
}

class CodeBlock(position: Position) : Block(position) {

    val code = Random.nextInt().absoluteValue % 10

    override fun afterRobotMove(robotState: RobotState): RobotState? {
        return if (robotState.position == position)
            robotState.withCode(code)
        else
            null
    }
}

class VerifyCodeBlock(position: Position) : Block(position) {

    override fun beforeRobotMove(robotState: RobotState): RobotState? {
        return if (robotState.position == position)
            robotState.also { state ->
                state.checkCode()
            }
        else
            null
    }
}

class PlatformBlock(position: Position) : Block(position) {

    override fun beforeRobotMove(robotState: RobotState): RobotState? {
        return if (robotState.position == position)
            robotState.destroyed(source = this)
        else
            null
    }
}

class TargetBlock(position: Position) : Block(position) {

    override fun afterRobotMove(robotState: RobotState): RobotState? {
        return if (robotState.position == position) {
            robotState.won(source = this)
        } else {
            null
        }
    }
}

class VoidBlock(position: Position) : Block(position)