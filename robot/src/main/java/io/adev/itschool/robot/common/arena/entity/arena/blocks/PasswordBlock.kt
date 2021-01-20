package io.adev.itschool.robot.common.arena.entity.arena.blocks

import io.adev.itschool.robot.common.arena.entity.Position
import io.adev.itschool.robot.common.arena.entity.RobotState

class PasswordBlock(position: Position) : Block(position) {
    override val texture = Texture.Password

    val password = position.hash()

    override fun beforeRobotMove(robotState: RobotState): RobotState? {
        return if (robotState.position == position && robotState.text != password)
            robotState.destroyed(source = this)
        else
            null
    }
}