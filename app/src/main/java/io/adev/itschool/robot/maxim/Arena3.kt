package io.adev.itschool.robot.maxim

import io.adev.itschool.robot.global.moveRight
import io.adev.itschool.robot.global.moveUp
import io.adev.itschool.robot.global.setArena
import io.adev.itschool.robot.levels.arena3

fun passArena3() {
    setArena(arena = arena3)
    passLeftWheel()
    passCarBody()
    passRightWhell()

}

private fun passLeftWheel() {
    moveRight()
    moveUp()
    moveRight()
    moveUp()
}

private fun passCarBody() {
    moveUp()
    moveRight()
    moveRight()
    moveRight()
    moveRight()
    moveRight()
    moveRight()
    moveRight()
    moveRight()
}

private fun passRightWhell() {
    moveDown()
    moveDown()
    moveDown()
}