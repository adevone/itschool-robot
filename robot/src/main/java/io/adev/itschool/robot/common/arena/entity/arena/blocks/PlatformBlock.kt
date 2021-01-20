package io.adev.itschool.robot.common.arena.entity.arena.blocks

import io.adev.itschool.robot.common.arena.entity.Position
import io.adev.itschool.robot.common.arena.entity.RobotState

class PlatformBlock(position: Position) : Block(position) {
    override val texture = Texture.Platform

    override fun beforeRobotMove(robotState: RobotState): RobotState? {
        return if (robotState.position == position)
            robotState.destroyed(source = this)
        else
            null
    }
}