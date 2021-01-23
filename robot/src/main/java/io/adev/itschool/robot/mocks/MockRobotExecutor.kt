package io.adev.itschool.robot.mocks

import io.adev.itschool.robot.common.arena.Robot
import io.adev.itschool.robot.common.arena.RobotExecutor
import io.adev.itschool.robot.common.arena.UserAction
import io.adev.itschool.robot.platform.arena.ArenaSetter

class MockRobotExecutor : RobotExecutor {

    override fun execute(
        robot: Robot, arenaSetter: ArenaSetter, userAction: UserAction,
        callback: RobotExecutor.Callback, useCallback: (() -> Unit) -> Unit,
    ) {
        try {
            userAction(robot, arenaSetter)
            robot.requireWon()
            callback.onWon()
        } catch (e: Exception) {
            callback.onFailure(e)
        }
    }
}