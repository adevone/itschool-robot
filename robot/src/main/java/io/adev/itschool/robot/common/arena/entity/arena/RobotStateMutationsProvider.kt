package io.adev.itschool.robot.common.arena.entity.arena

import io.adev.itschool.robot.common.arena.entity.RobotState

interface RobotStateMutationsProvider {
    fun beforeRobotMove(robotState: RobotState): RobotState? = null
    fun afterRobotMove(robotState: RobotState): RobotState? = null
}