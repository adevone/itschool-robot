package io.adev.itschool.robot.common.arena

import io.adev.itschool.robot.common.BaseViewModel
import io.adev.itschool.robot.common.arena.entity.RobotState
import io.adev.itschool.robot.common.arena.entity.arena.Arena
import kotlinx.coroutines.launch

interface ArenaView {
    var arena: Arena?
    var robotState: RobotState?
    val displayWon: () -> Unit
}

class ArenaViewModel(
    private val arena: Arena,
    private val userAction: UserAction,
    private val executor: RobotExecutor,
    private val statesApplier: RobotStatesApplier
) : BaseViewModel<ArenaView>() {

    override val viewProxy = object : ArenaView {
        override var arena by state({ it::arena }, initial = null)
        override var robotState by state({ it::robotState }, initial = null)
        override val displayWon = event { it.displayWon }.doExactlyOnce()
    }

    init {
        viewProxy.arena = arena
    }

    private val statesApplierCallback: RobotStatesApplier.Callback =
        object : RobotStatesApplier.Callback {

            override fun moveRobot(state: RobotState) {
                viewProxy.robotState = state
            }

            override fun onStateApplied(state: RobotState) {
                robot.onStateApplied(state)
            }
        }

    private val robot = Robot(
        initialState = arena.initialRobotState,
        stateMutationsProvider = arena,
        applyStates = { states ->
            statesApplier.applyStates(states, statesApplierCallback, useCallback = { action ->
                launch {
                    action()
                }
            })
        }
    )

    private val executorCallback = object : RobotExecutor.Callback {

        override fun onWon() {
            viewProxy.displayWon()
        }

        override fun onFailure(e: Exception) {
            this@ArenaViewModel.onFailure(e)
        }
    }

    override fun onEnter() {
        super.onEnter()
        executor.execute(robot, arena, userAction, executorCallback, useCallback = { action ->
            launch {
                action()
            }
        })
    }

    fun onRobotMoved() {
        statesApplier.robotMoved()
    }
}
