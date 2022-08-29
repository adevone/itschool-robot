package io.adev.itschool.robot.common.arena

import io.adev.itschool.robot.common.arena.entity.Position
import io.adev.itschool.robot.common.arena.entity.RobotException
import io.adev.itschool.robot.common.arena.entity.RobotState
import io.adev.itschool.robot.common.arena.entity.arena.Arena
import io.adev.itschool.robot.common.arena.entity.arena.RandomCodeBlock

abstract class RobotController : RobotState.Source {
    var onArenaSet: (Arena) -> Unit = {}
    lateinit var applyStates: (List<RobotState>) -> Unit

    private var _arena: Arena? = null

    var arena: Arena
        get() = requireNotNull(_arena) { "RobotController.arena must be set" }
        set(arena) {
            _arena = arena
            onArenaSet(arena)
            updateState(arena.initialRobotState)
        }

    private lateinit var currentState: RobotState

    abstract fun run()

    fun requireWon() {
        if (!currentState.isWon) {
            throw NotCompleteException(currentState, stateHistory)
        }
    }

    fun getKey(): String {
        updateState(currentState.withInitKey())
        return currentState.getKey()
    }

    fun useKey(key: String) {
        updateState(currentState.withKey(key))
    }

    fun isAuthLeft(): Boolean {
        return isAuth(direction = Position.Direction.Left)
    }

    fun isAuthRight(): Boolean {
        return isAuth(direction = Position.Direction.Right)
    }

    fun isAuthUp(): Boolean {
        return isAuth(direction = Position.Direction.Up)
    }

    fun isAuthDown(): Boolean {
        return isAuth(direction = Position.Direction.Down)
    }

    fun isAuth(direction: Position.Direction): Boolean {
        val nextPosition = currentState.position.moved(direction)
        return arena.blockOn(nextPosition)?.requiresKey == true
    }

    fun currentCode(): Int {
        val block = arena.blockOn(currentState.position)
        require(block is RandomCodeBlock) { "Robot is not on a CodeBlock now" }
        return block.randomCode
    }

    open fun display(password: String) {
        updateState(state = currentState.displaying(password))
    }

    open fun moveRight(stepsCount: Int = 1) {
        repeat(stepsCount) {
            move(direction = Position.Direction.Right)
        }
    }

    open fun moveLeft(stepsCount: Int = 1) {
        repeat(stepsCount) {
            move(direction = Position.Direction.Left)
        }
    }

    open fun moveDown(stepsCount: Int = 1) {
        repeat(stepsCount) {
            move(direction = Position.Direction.Down)
        }
    }

    open fun moveUp(stepsCount: Int = 1) {
        repeat(stepsCount) {
            move(direction = Position.Direction.Up)
        }
    }

    open fun move(direction: Position.Direction) {
        updateState(state = currentState.moved(direction).withSource(source = this))
    }

    fun setBeforeMove(beforeMove: () -> Unit) {
        updateState(state = currentState.withBeforeMove(beforeMove))
    }

    fun updateState(state: RobotState) {
        val statesList = makeStatesList(state)
        applyStates(statesList)
    }

    open fun finish(reason: RobotException?) {
        updateState(currentState.finished(reason))
    }

    private fun makeStatesList(state: RobotState): List<RobotState> {

        val list = mutableListOf<RobotState>()

        val beforeState = arena.beforeRobotMove(robotState = state).takeIf { it != state }
        if (beforeState != null) {
            val beforeStates = makeStatesList(beforeState)
            list.addAll(beforeStates)
        }

        list.add(state)

        val afterState = arena.afterRobotMove(robotState = state).takeIf { it != state }
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
        robotController: RobotController,
        callback: Callback,
        useCallback: (() -> Unit) -> Unit,
    )

    interface Callback {
        fun onWon()
        fun onFailure(e: Exception)
    }
}

typealias CreateRobotController = () -> RobotController

interface RobotStatesApplier {

    fun applyStates(
        states: List<RobotState>,
        callback: Callback,
        useCallback: (() -> Unit) -> Unit,
    )

    fun robotMoved()

    interface Callback {
        fun moveRobot(state: RobotState)
        fun onStateApplied(state: RobotState)
    }
}

class FinishedException(
    cause: Throwable?,
    state: RobotState,
    stateHistory: List<RobotState>,
) : IllegalStateException("state=$state, history:\n${formatHistory(stateHistory)}", cause)

class NotCompleteException(
    state: RobotState,
    stateHistory: List<RobotState>,
) : IllegalStateException(
    "level is not completed, state=$state, history:\n${formatHistory(stateHistory)}"
)

private fun formatHistory(stateHistory: List<RobotState>): String {
    return stateHistory.joinToString(separator = " ->\n") { "(${it})" }
}