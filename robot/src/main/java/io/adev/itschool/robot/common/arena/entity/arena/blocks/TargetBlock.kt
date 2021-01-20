package io.adev.itschool.robot.common.arena.entity.arena.blocks

import io.adev.itschool.robot.common.arena.entity.Position
import io.adev.itschool.robot.common.arena.entity.RobotState

class TargetBlock(position: Position) : Block(position) {
    override val texture = Texture.Target

    override fun afterRobotMove(robotState: RobotState): RobotState? {
        return if (robotState.position == position) {
            robotState.won(source = this)
        } else {
            null
        }
    }
}