package io.adev.itschool.robot.maxim

import io.adev.itschool.robot.global.moveDown
import io.adev.itschool.robot.global.moveLeft
import io.adev.itschool.robot.global.moveRight
import io.adev.itschool.robot.global.moveUp
import io.adev.itschool.robot.global.setArena1

fun passArena1() {
    setArena1()
    passLeftLeg()
    passLeftArm()
    passHead()
    passRightArm()
}

private fun passLeftLeg() {
    moveRight()
    moveUp()
    moveUp()
}

private fun passLeftArm() {
    moveUp()
    moveLeft()
    moveLeft()
    moveUp()
    moveUp()
    moveRight()
}

private fun passHead() {
    moveRight()
    moveUp()
    moveUp()
    moveRight()
    moveRight()
    moveDown()
    moveDown()
}

private fun passRightArm() {
    moveRight()
    moveRight()
    moveDown()
    moveDown()
}
