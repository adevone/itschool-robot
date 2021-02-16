package io.adev.itschool.robot.tests

import io.adev.itschool.robot.common.arena.FinishedException
import io.adev.itschool.robot.common.arena.NotCompleteException
import io.adev.itschool.robot.common.arena.RobotController
import io.adev.itschool.robot.common.arena.entity.arena.Arena
import io.adev.itschool.robot.levels.arena1
import io.adev.itschool.robot.mocks.runMockRobot
import io.adev.itschool.robot.platform.arena.ArenaHolder
import org.junit.Test
import kotlin.test.assertFailsWith

class DemoTests {

    @Test
    fun notCompleted() {
        assertFailsWith<NotCompleteException> {
            runMockRobot(
                arena = arena1,
                run = fun(robotController: RobotController, arenaHolder: ArenaHolder) {

                }
            )
        }
    }

    @Test
    fun brokenRight() {
        assertFailsWith<FinishedException> {
            runMockRobot(
                arena = arena1,
                run = fun(robotController: RobotController, arenaHolder: ArenaHolder) {
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
            arena = arena1,
            run = fun(robotController: RobotController, arenaHolder: ArenaHolder) {
                robotController.moveRight()
                robotController.moveRight()
                robotController.moveDown()
            }
        )
    }
}