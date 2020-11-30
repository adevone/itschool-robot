package io.adev.itschool.robot

import io.adev.itschool.robot.common.arena.entity.vp
import org.junit.Assert.assertEquals
import org.junit.Test

class PointTests {

    @Test
    fun sum() {
        assertEquals(2.vp, 1.vp + 1.vp)
    }

    @Test
    fun sub() {
        assertEquals(1.vp, 2.vp - 1.vp)
    }

    @Test
    fun subNegative() {
        assertEquals((-1).vp, 1.vp - 2.vp)
    }

    @Test
    fun div() {
        assertEquals(2f, 2.vp / 1.vp)
    }

    @Test
    fun divNotInt() {
        assertEquals(0.5f, 1.vp / 2.vp)
    }

    @Test
    fun compare() {
        assertEquals(true, 1.vp == 1.vp)
    }

    @Test
    fun range() {
        assertEquals(1.vp.rangeTo(), 0 until 1)
    }
}