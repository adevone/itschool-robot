package io.adev.itschool.robot.maxim

import io.adev.itschool.robot.global.moveDown
import io.adev.itschool.robot.global.moveLeft
import io.adev.itschool.robot.global.moveRight
import io.adev.itschool.robot.global.moveUp
import io.adev.itschool.robot.global.setArena
import io.adev.itschool.robot.levels.arena2

fun passArena2() {
    setArena(arena = arena2)
    moveRight(stepsCount = 6)
    moveDown(2)
    moveLeft()
    moveDown()
    moveLeft(2)
    moveUp()
    moveLeft(2)
    moveDown(2)
    moveRight()
    moveDown()
    moveRight(6)
    moveUp()
}
