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
    moveRight(stepsCount = 2)
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
    moveRight(stepsCount = 2)
    moveUp()
    moveRight(stepsCount = 9)

}

private fun head() {
    moveRight()
    moveDown()
    moveRight()
    moveDown()
}