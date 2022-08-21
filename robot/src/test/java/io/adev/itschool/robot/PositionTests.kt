package io.adev.itschool.robot

import io.adev.itschool.robot.common.arena.entity.Position
import io.adev.itschool.robot.common.arena.entity.vp
import org.junit.Assert.assertEquals
import org.junit.Test

class PositionTests {

    @Test
    fun right() {
        val expected = Position(x = 1.vp, y = 0.vp)
        val position = Position(x = 0.vp, y = 0.vp).moved(Position.Direction.Right)
        assertEquals(expected, position)
    }

    @Test
    fun left() {
        val expected = Position(x = (-1).vp, y = 0.vp)
        val position = Position(x = 0.vp, y = 0.vp).moved(Position.Direction.Left)
        assertEquals(expected, position)
    }

    @Test
    fun down() {
        val expected = Position(x = 0.vp, y = 1.vp)
        val position = Position(x = 0.vp, y = 0.vp).moved(Position.Direction.Down)
        assertEquals(expected, position)
    }

    @Test
    fun up() {
        val expected = Position(x = 0.vp, y = (-1).vp)
        val position = Position(x = 0.vp, y = 0.vp).moved(Position.Direction.Up)
        assertEquals(expected, position)
    }
}