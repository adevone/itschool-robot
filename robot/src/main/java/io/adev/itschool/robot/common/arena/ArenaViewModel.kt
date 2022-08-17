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
    createRobotController: CreateRobotController,
    executor: RobotExecutor,
    private val statesApplier: RobotStatesApplier,
) : BaseViewModel<ArenaView>() {

    override val viewProxy = object : ArenaView {
        override var arena by state({ it::arena }, initial = null)
        override var robotState by state({ it::robotState }, initial = null)
        override val displayWon = event { it.displayWon }.perform.exactlyOnce()
    }

    private val statesApplierCallback: RobotStatesApplier.Callback =
        object : RobotStatesApplier.Callback {

            override fun moveRobot(state: RobotState) {
                viewProxy.robotState = state
            }

            override fun onStateApplied(state: RobotState) {
                robotController.onStateApplied(state)
            }
        }

    private val robotController: RobotController = createRobotController()

    init {
        robotController.onArenaSet = { arena ->
            launch {
                viewProxy.arena = arena
            }
        }
        robotController.applyStates = { states ->
            statesApplier.applyStates(states, statesApplierCallback, useCallback = { action ->
                launch {
                    action()
                }
            })
        }
    }

    private val executorCallback = object : RobotExecutor.Callback {

        override fun onWon() {
            viewProxy.displayWon()
        }

        override fun onFailure(e: Exception) {
            this@ArenaViewModel.onFailure(e)
        }
    }

    init {
        executor.execute(robotController, executorCallback, useCallback = { action ->
            launch {
                action()
            }
        })
    }

    fun onRobotMoved() {
        statesApplier.robotMoved()
    }
}
