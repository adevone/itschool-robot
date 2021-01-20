package io.adev.itschool.robot.platform.arena

import android.os.ConditionVariable
import io.adev.itschool.robot.common.arena.Robot
import io.adev.itschool.robot.common.arena.RobotExecutor
import io.adev.itschool.robot.common.arena.RobotStatesApplier
import io.adev.itschool.robot.common.arena.UserAction
import io.adev.itschool.robot.common.arena.entity.RobotState
import io.adev.itschool.robot.common.arena.entity.arena.Arena

class AndroidRobotExecutor : RobotExecutor {

    override fun execute(
        robot: Robot, arena: Arena, userAction: UserAction,
        callback: RobotExecutor.Callback, useCallback: (() -> Unit) -> Unit,
    ) {
        Thread {
            try {
                robot.applyInitialState()
                userAction(robot, arena)
                robot.requireWon()
                useCallback {
                    callback.onWon()
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