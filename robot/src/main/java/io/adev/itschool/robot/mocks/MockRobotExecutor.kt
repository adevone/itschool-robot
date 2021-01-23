package io.adev.itschool.robot.mocks

import io.adev.itschool.robot.common.arena.Robot
import io.adev.itschool.robot.common.arena.RobotExecutor
import io.adev.itschool.robot.common.arena.UserAction
import io.adev.itschool.robot.common.arena.entity.arena.Arena
import kotlinx.coroutines.flow.MutableStateFlow

class MockRobotExecutor : RobotExecutor {

    override fun execute(
        robot: Robot, arenaFlow: MutableStateFlow<Arena?>, userAction: UserAction,
        callback: RobotExecutor.Callback, useCallback: (() -> Unit) -> Unit,
    ) {
        try {
            userAction(robot, arenaFlow)
            robot.requireWon()
            callback.onWon()
        } catch (e: Exception) {
            callback.onFailure(e)
        }
    }
}