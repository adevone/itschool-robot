package io.adev.itschool.robot.mocks

import io.adev.itschool.robot.common.arena.Robot
import io.adev.itschool.robot.common.arena.RobotExecutor
import io.adev.itschool.robot.common.arena.RobotStatesApplier
import io.adev.itschool.robot.common.arena.UserAction
import io.adev.itschool.robot.common.arena.entity.RobotState
import io.adev.itschool.robot.common.arena.entity.arena.Arena
import kotlinx.coroutines.flow.MutableStateFlow

fun runMockRobot(arena: Arena, run: UserAction) {
    var exception: Exception? = null
    val executor = MockRobotExecutor()
    val statesApplier = MockRobotStatesApplier()
    lateinit var robot: Robot
    robot = Robot(
        applyStates = { states ->
            statesApplier.applyStates(
                states = states,
                callback = object : RobotStatesApplier.Callback {

                    override fun moveRobot(state: RobotState) {
                        statesApplier.robotMoved()
                    }

                    override fun onStateApplied(state: RobotState) {
                        robot.onStateApplied(state)
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
        robot = robot,
        arenaFlow = MutableStateFlow(arena),
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