package io.adev.itschool.robot.mocks

import io.adev.itschool.robot.common.arena.RobotStatesApplier
import io.adev.itschool.robot.common.arena.entity.RobotState

class MockRobotStatesApplier : RobotStatesApplier {

    override fun applyStates(
        states: List<RobotState>,
        callback: RobotStatesApplier.Callback,
        useCallback: (() -> Unit) -> Unit
    ) {
        states.forEach { state ->
            callback.moveRobot(state)
            callback.onStateApplied(state)
        }
    }

    override fun robotMoved() {

    }
}