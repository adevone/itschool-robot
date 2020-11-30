package io.adev.itschool.robot.common.arena.entity

interface RobotStateMutationsProvider {
    fun beforeRobotMove(robotState: RobotState): RobotState? = null
    fun afterRobotMove(robotState: RobotState): RobotState? = null
}