package io.adev.itschool.robot.denis.homeworks

import io.adev.itschool.robot.global.moveDown
import io.adev.itschool.robot.global.moveLeft
import io.adev.itschool.robot.levels.homework1DenisArena
import io.adev.itschool.robot.global.moveRight
import io.adev.itschool.robot.global.setArena
import io.adev.itschool.robot.global.moveUp

fun denisHomework1() {
    setArena(homework1DenisArena)
    leftFoot()
    leftHand()
    head()
    rightHand()
}

fun leftFoot() {
    moveRight()
    moveUp()
    moveUp()
}

fun leftHand() {
    moveUp()
    moveLeft()
    moveLeft()
    moveUp()
    moveUp()
    moveRight()
    moveRight()
}

fun head() {
    moveUp()
    moveUp()
    moveRight()
    moveRight()
    moveDown()
}

fun rightHand() {
    moveDown()
    moveRight()
    moveRight()
    moveDown()
    moveDown()
}

