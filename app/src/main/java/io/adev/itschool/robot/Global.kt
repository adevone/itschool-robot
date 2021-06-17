package io.adev.itschool.robot

import io.adev.itschool.robot.global.moveDown
import io.adev.itschool.robot.global.moveLeft
import io.adev.itschool.robot.global.moveRight
import io.adev.itschool.robot.global.moveUp
import io.adev.itschool.robot.global.setArena1

// определяем функцию выполнить
fun run() {
    setArena1()
    leftLeg()
    leftHand()
    head()
    rightLeg()
}

fun leftLeg() {
    moveRight()
    moveUp(stepsCount = 3)
}

fun leftHand() {
    moveLeft(stepsCount = 2)
    moveUp(stepsCount = 2)
    moveRight(stepsCount = 2)
}

fun head() {
    moveUp(stepsCount = 2)
    moveRight(stepsCount = 2)
    moveDown(stepsCount = 2)
}

fun rightLeg() {
    moveRight(stepsCount = 2)
    moveDown(2)
}