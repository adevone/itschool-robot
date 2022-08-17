package io.adev.itschool.robot.platform.arena

import android.os.ConditionVariable
import io.adev.itschool.robot.common.arena.RobotController
import io.adev.itschool.robot.common.arena.RobotExecutor
import io.adev.itschool.robot.common.arena.RobotStatesApplier
import io.adev.itschool.robot.common.arena.entity.RobotException
import io.adev.itschool.robot.common.arena.entity.RobotState

class AndroidRobotExecutor : RobotExecutor {

    override fun execute(
        robotController: RobotController,
        callback: RobotExecutor.Callback,
        useCallback: (() -> Unit) -> Unit
    ) {
        Thread {
            try {
                try {
                    robotController.run()
                    robotController.requireWon()
                    useCallback {
                        callback.onWon()
                    }
                } catch (e: Exception) {
                    robotController.finish(RobotException(e))
                    throw e
                }
            } catch (e: Exception) {
                useCallback {
                    callback.onFailure(e)
                }
            }
        }.apply {
            name = "RobotThread"
        }.start()
    }
}

class AndroidRobotStatesApplier : RobotStatesApplier {

    private val conditionVariable = ConditionVariable()
    override fun applyStates(
        states: List<RobotState>,
        callback: RobotStatesApplier.Callback, useCallback: (() -> Unit) -> Unit,
    ) {
        states.forEach { state ->
            useCallback {
                callback.moveRobot(state)
            }
            conditionVariable.close()
            conditionVariable.block()
            callback.onStateApplied(state)
        }
    }

    override fun robotMoved() {
        conditionVariable.open()
    }
}