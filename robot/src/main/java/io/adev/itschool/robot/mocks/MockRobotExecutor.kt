package io.adev.itschool.robot.mocks

import io.adev.itschool.robot.common.arena.RobotController
import io.adev.itschool.robot.common.arena.RobotExecutor
import io.adev.itschool.robot.common.arena.UserAction
import io.adev.itschool.robot.platform.arena.ArenaHolder

class MockRobotExecutor : RobotExecutor {

    override fun execute(
        robotController: RobotController, arenaHolder: ArenaHolder, userAction: UserAction,
        callback: RobotExecutor.Callback, useCallback: (() -> Unit) -> Unit,
    ) {
        try {
            userAction(robotController, arenaHolder)
            robotController.requireWon()
            callback.onWon()
        } catch (e: Exception) {
            callback.onFailure(e)
        }
    }
}