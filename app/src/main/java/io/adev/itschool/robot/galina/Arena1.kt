package io.adev.itschool.robot.galina

import io.adev.itschool.robot.global.*

fun galinaArena1() {
    setArena1()
    legRight()
    handRight()
    head()
    handLeft()
}

private fun legRight() {
    moveRight()
    moveUp(stepsCount = 3)
}

private fun handRight() {
    moveLeft(stepsCount = 2)
    moveUp(stepsCount = 2)
    moveRight(stepsCount = 2)
}

private fun head() {
    moveUp(stepsCount = 2)
    moveRight(stepsCount = 2)
    moveDown(stepsCount = 2)
}

private fun handLeft() {
    moveRight(stepsCount = 2)
    moveDown(stepsCount = 2)
}