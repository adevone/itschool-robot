package io.adev.itschool.robot.mocks

import io.adev.itschool.robot.common.arena.RobotController
import io.adev.itschool.robot.common.arena.RobotExecutor
import io.adev.itschool.robot.common.arena.RobotStatesApplier
import io.adev.itschool.robot.common.arena.UserAction
import io.adev.itschool.robot.common.arena.entity.RobotState
import io.adev.itschool.robot.common.arena.entity.arena.Arena
import io.adev.itschool.robot.platform.arena.ArenaHolder

fun runMockRobot(arena: Arena, run: UserAction) {
    var exception: Exception? = null
    val executor = MockRobotExecutor()
    val statesApplier = MockRobotStatesApplier()
    lateinit var robotController: RobotController
    robotController = RobotController(
        applyStates = { states ->
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
        },
    ).also {
        it.stateMutationsProvider = arena
        it.updateState(arena.initialRobotState)
    }
    executor.execute(
        robotController = robotController,
        arenaHolder = ArenaHolder {},
        userAction = run,
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