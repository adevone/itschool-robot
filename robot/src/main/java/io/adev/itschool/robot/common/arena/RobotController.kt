package io.adev.itschool.robot.common.arena

import io.adev.itschool.robot.common.arena.entity.Position
import io.adev.itschool.robot.common.arena.entity.RobotState
import io.adev.itschool.robot.common.arena.entity.arena.Arena
import io.adev.itschool.robot.common.arena.entity.arena.AuthBlock
import io.adev.itschool.robot.common.arena.entity.arena.RobotStateMutationsProvider
import io.adev.itschool.robot.platform.arena.ArenaHolder

class RobotController(
    private val applyStates: (List<RobotState>) -> Unit,
) : RobotState.Source {

    lateinit var stateMutationsProvider: RobotStateMutationsProvider

    fun requireWon() {
        if (!currentState.isWon) {
            throw NotCompleteException(currentState, stateHistory)
        }
    }

    private lateinit var currentState: RobotState

    fun authenticate(): String {
        updateState(currentState.authenticate())
        return currentState.getToken()
    }

    fun authorize(token: String) {
        updateState(currentState.authorize(token))
    }

    fun isAuthLeft(arena: Arena): Boolean {
        return isAuth(arena, direction = Position.Direction.Left)
    }

    fun isAuthRight(arena: Arena): Boolean {
        return isAuth(arena, direction = Position.Direction.Right)
    }

    fun isAuthUp(arena: Arena): Boolean {
        return isAuth(arena, direction = Position.Direction.Up)
    }

    fun isAuthDown(arena: Arena): Boolean {
        return isAuth(arena, direction = Position.Direction.Down)
    }

    private fun isAuth(arena: Arena, direction: Position.Direction): Boolean {
        val rightPosition = currentState.position.move(direction)
        return arena.blockOn(rightPosition) is AuthBlock
    }

    fun display(password: String) {
        updateState(state = currentState.display(password))
    }

    fun moveRight(stepsCount: Int = 1) {
        repeat(stepsCount) {
            move(direction = Position.Direction.Right)
        }
    }

    fun moveLeft(stepsCount: Int = 1) {
        repeat(stepsCount) {
            move(direction = Position.Direction.Left)
        }
    }

    fun moveDown(stepsCount: Int = 1) {
        repeat(stepsCount) {
            move(direction = Position.Direction.Down)
        }
    }

    fun moveUp(stepsCount: Int = 1) {
        repeat(stepsCount) {
            move(direction = Position.Direction.Up)
        }
    }

    fun move(direction: Position.Direction) {
        updateState(state = currentState.move(direction, source = this))
    }

    fun setBeforeMove(beforeMove: () -> Unit) {
        updateState(state = currentState.withBeforeMove(beforeMove))
    }

    fun updateState(state: RobotState) {
        val statesList = makeStatesList(state)
        applyStates(statesList)
    }

    fun finish(reason: String?) {
        updateState(currentState.finish(reason))
    }

    private fun makeStatesList(state: RobotState): List<RobotState> {

        val list = mutableListOf<RobotState>()

        val beforeState = stateMutationsProvider.beforeRobotMove(robotState = state).takeIf { it != state }
        if (beforeState != null) {
            val beforeStates = makeStatesList(beforeState)
            list.addAll(beforeStates)
        }

        list.add(state)

        val afterState = stateMutationsProvider.afterRobotMove(robotState = state).takeIf { it != state }
        if (afterState != null) {
            val afterStates = makeStatesList(afterState)
            list.addAll(afterStates)
        }

        return list
    }

    private val stateHistory = mutableListOf<RobotState>()
    fun onStateApplied(state: RobotState) {
        stateHistory.add(state)
        currentState = state
        if (state.finishReason != null) {
            throw FinishedException(state.finishReason, state, stateHistory)
        }
    }

    override fun sourceRepresentation(): String {
        return this::class.simpleName.toString()
    }
}

interface RobotExecutor {

    fun execute(
        robotController: RobotController, arenaHolder: ArenaHolder, userAction: UserAction,
        callback: Callback, useCallback: (() -> Unit) -> Unit,
    )

    interface Callback {
        fun onWon()
        fun onFailure(e: Exception)
    }
}

typealias UserAction = (RobotController, ArenaHolder) -> Unit

interface RobotStatesApplier {

    fun applyStates(
        states: List<RobotState>,
        callback: Callback, useCallback: (() -> Unit) -> Unit,
    )

    fun robotMoved()

    interface Callback {
        fun moveRobot(state: RobotState)
        fun onStateApplied(state: RobotState)
    }
}

class FinishedException(
    message: String,
    state: RobotState,
    stateHistory: List<RobotState>,
) : IllegalStateException(
    "$message, state=$state, history:\n${formatHistory(stateHistory)}"
)

class NotCompleteException(
    state: RobotState,
    stateHistory: List<RobotState>,
) : IllegalStateException(
    "level is not completed, state=$state, history:\n${formatHistory(stateHistory)}"
)

private fun formatHistory(stateHistory: List<RobotState>): String {
    return stateHistory.joinToString(separator = " ->\n") { "(${it})" }
}