package io.adev.itschool.robot

import io.adev.itschool.robot.common.arena.entity.Position
import io.adev.itschool.robot.common.arena.entity.RobotState
import io.adev.itschool.robot.common.arena.entity.arena.Arena
import io.adev.itschool.robot.common.arena.entity.arena.blocks.PlatformBlock
import io.adev.itschool.robot.common.arena.entity.arena.parseArena
import io.adev.itschool.robot.common.arena.entity.vp
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
        val arena = parseArena("""
rp
pp
        """.trim())
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
        val arena = parseArena("""
r
pp
        """.trim())
        assertEquals(expected, arena)
    }
}