package io.adev.itschool.robot.tests

import io.adev.itschool.robot.common.arena.FinishedException
import io.adev.itschool.robot.common.arena.NotCompleteException
import io.adev.itschool.robot.common.arena.RobotController
import io.adev.itschool.robot.levels.arena1
import io.adev.itschool.robot.levels.demoArena
import io.adev.itschool.robot.mocks.runMockRobot
import org.junit.Test
import kotlin.test.assertFailsWith

class DemoTests {

    @Test
    fun notCompleted() {
        assertFailsWith<NotCompleteException> {
            runMockRobot(
                arena = arena1,
                robotController = object : RobotController() {
                    override fun run() {

                    }
                },
            )
        }
    }

    @Test
    fun brokenRight() {
        assertFailsWith<FinishedException> {
            runMockRobot(
                arena = arena1,
                robotController = object : RobotController() {
                    override fun run() {
                        moveRight()
                        moveRight()
                        moveRight()
                    }
                },
            )
        }
    }

    @Test
    fun completed() {
        runMockRobot(
            arena = demoArena,
            robotController = object : RobotController() {
                override fun run() {
                    moveRight()
                    moveRight()
                    moveDown()
                }
            },
        )
    }
}