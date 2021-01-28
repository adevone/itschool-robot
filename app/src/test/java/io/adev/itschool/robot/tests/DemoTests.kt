package io.adev.itschool.robot.tests

import io.adev.itschool.robot.common.arena.NotCompleteException
import io.adev.itschool.robot.common.arena.RobotController
import io.adev.itschool.robot.common.arena.FinishedException
import io.adev.itschool.robot.common.arena.entity.Arena
import io.adev.itschool.robot.common.arena.entity.parseArena
import io.adev.itschool.robot.levels.arena1
import io.adev.itschool.robot.mocks.runMockRobot
import org.junit.Test
import kotlin.test.assertFailsWith

class DemoTests {

    @Test
    fun notCompleted() {
        assertFailsWith<NotCompleteException> {
            runMockRobot(
                arena = arena1.parseArena(),
                run = fun(robotController: RobotController, arena: Arena) {

                }
            )
        }
    }

    @Test
    fun brokenRight() {
        assertFailsWith<FinishedException> {
            runMockRobot(
                arena = arena1.parseArena(),
                run = fun(robotController: RobotController, arena: Arena) {
                    robotController.moveRight()
                    robotController.moveRight()
                    robotController.moveRight()
                }
            )
        }
    }

    @Test
    fun completed() {
        runMockRobot(
            arena = arena1.parseArena(),
            run = fun(robotController: RobotController, arena: Arena) {
                robotController.moveRight()
                robotController.moveRight()
                robotController.moveDown()
            }
        )
    }
}