package io.adev.itschool.robot.mocks

import io.adev.itschool.robot.common.arena.RobotController
import io.adev.itschool.robot.common.arena.RobotExecutor
import io.adev.itschool.robot.common.arena.RobotStatesApplier
import io.adev.itschool.robot.common.arena.entity.RobotState
import io.adev.itschool.robot.common.arena.entity.arena.Arena

fun runMockRobot(arena: Arena, robotController: RobotController) {
    var exception: Exception? = null
    val executor = MockRobotExecutor()
    val statesApplier = MockRobotStatesApplier()
    robotController.applyStates = { states ->
        statesApplier.applyStates(
            states = states,
            callback = object : RobotStatesApplier.Callback {

                override fun moveRobot(state: RobotState) {
                    statesApplier.robotMoved()
                }

                override fun onStateApplied(state: RobotState) {
                    robotController.onStateApplied(state)
                }
            },
            useCallback = {}
        )
    }
    robotController.setArena(arena)
    executor.execute(
        robotController = robotController,
        callback = object : RobotExecutor.Callback {

            override fun onWon() {
                // do nothing
            }

            override fun onFailure(e: Exception) {
                exception = e
            }
        },
        useCallback = {}
    )
    if (exception != null) throw exception!!
}