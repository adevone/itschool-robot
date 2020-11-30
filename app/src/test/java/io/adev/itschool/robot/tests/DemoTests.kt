package io.adev.itschool.robot.tests

import io.adev.itschool.robot.common.arena.NotCompleteException
import io.adev.itschool.robot.common.arena.Robot
import io.adev.itschool.robot.common.arena.RobotDestroyedException
import io.adev.itschool.robot.common.arena.entity.Arena
import io.adev.itschool.robot.common.arena.entity.parseArena
import io.adev.itschool.robot.levelDemo
import io.adev.itschool.robot.mocks.runMockRobot
import org.junit.Test
import kotlin.test.assertFailsWith

class DemoTests {

    @Test
    fun notCompleted() {
        assertFailsWith<NotCompleteException> {
            runMockRobot(
                arena = levelDemo.parseArena(),
                run = fun(robot: Robot, arena: Arena) {

                }
            )
        }
    }

    @Test
    fun brokenRight() {
        assertFailsWith<RobotDestroyedException> {
            runMockRobot(
                arena = levelDemo.parseArena(),
                run = fun(robot: Robot, arena: Arena) {
                    robot.right()
                    robot.right()
                    robot.right()
                }
            )
        }
    }

    @Test
    fun completed() {
        runMockRobot(
            arena = levelDemo.parseArena(),
            run = fun(robot: Robot, arena: Arena) {
                robot.right()
                robot.right()
                robot.down()
            }
        )
    }
}