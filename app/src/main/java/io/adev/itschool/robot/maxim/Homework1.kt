package io.adev.itschool.robot.maxim

import io.adev.itschool.robot.global.moveDown
import io.adev.itschool.robot.global.moveRight
import io.adev.itschool.robot.global.moveUp
import io.adev.itschool.robot.global.setHomework1Variant1Arena

fun passHomework1() {
    setHomework1Variant1Arena()
    passLeftWheel()
    passCarBody()
    passRightWheel()

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
    moveDown()

}

private fun passRightWheel() {
    moveDown()
    moveDown()
}