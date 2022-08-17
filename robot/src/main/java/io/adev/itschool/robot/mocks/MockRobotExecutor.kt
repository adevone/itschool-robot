package io.adev.itschool.robot.mocks

import io.adev.itschool.robot.common.arena.RobotController
import io.adev.itschool.robot.common.arena.RobotExecutor

class MockRobotExecutor : RobotExecutor {

    override fun execute(
        robotController: RobotController,
        callback: RobotExecutor.Callback,
        useCallback: (() -> Unit) -> Unit,
    ) {
        try {
            robotController.run()
            robotController.requireWon()
            callback.onWon()
        } catch (e: Exception) {
            callback.onFailure(e)
        }
    }
}