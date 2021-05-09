package io.adev.itschool.robot.galina

import io.adev.itschool.robot.global.moveDown
import io.adev.itschool.robot.global.moveRight
import io.adev.itschool.robot.global.moveUp
import io.adev.itschool.robot.global.setHomework1Variant3Arena

fun homework1Variant3() {
    setHomework1Variant3Arena()
    tail()
    back()
    head()
}

private fun tail() {
    moveDown()
    moveRight()
    moveRight()
    moveDown()
    moveRight()
    moveDown()
    moveRight()
}

private fun back() {
    moveRight()
    moveUp()
    moveRight()
    moveUp()
    moveRight()
    moveRight()
    moveUp()
    moveRight()
    moveRight()
    moveRight()
    moveRight()
    moveRight()
    moveRight()
    moveRight()
    moveRight()
    moveRight()
}

private fun head() {
    moveRight()
    moveDown()
    moveRight()
    moveDown()
}