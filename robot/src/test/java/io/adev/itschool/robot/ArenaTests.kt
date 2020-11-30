package io.adev.itschool.robot

import io.adev.itschool.robot.common.arena.entity.*
import org.junit.Assert.assertEquals
import org.junit.Test

class ArenaTests {

    @Test
    fun withNoVoid() {
        val expected = Arena(
            initialRobotState = RobotState(Position(0.vp, 0.vp)),
            nonVoidBlocks = listOf(
                PlatformBlock(Position(x = 1.vp, y = 0.vp)),
                PlatformBlock(Position(x = 0.vp, y = 1.vp)),
                PlatformBlock(Position(x = 1.vp, y = 1.vp))
            )
        )
        val arena = """
rp
pp
        """.trim().parseArena()
        assertEquals(expected, arena)
    }

    @Test
    fun withVoid() {
        val expected = Arena(
            initialRobotState = RobotState(Position(0.vp, 0.vp)),
            nonVoidBlocks = listOf(
                PlatformBlock(Position(x = 0.vp, y = 1.vp)),
                PlatformBlock(Position(x = 1.vp, y = 1.vp))
            )
        )
        val arena = """
r
pp
        """.trim().parseArena()
        assertEquals(expected, arena)
    }
}