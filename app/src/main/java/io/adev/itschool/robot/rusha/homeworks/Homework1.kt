package io.adev.itschool.robot.rusha.homeworks

import io.adev.itschool.robot.global.moveDown
import io.adev.itschool.robot.global.moveLeft
import io.adev.itschool.robot.global.moveRight
import io.adev.itschool.robot.global.moveUp
import io.adev.itschool.robot.global.setArena
import io.adev.itschool.robot.levels.homework1DenisArena

fun rushaHomeWork1() {
    setArena(homework1DenisArena)
    leftLeg()
    leftHand()
    head()
    rightHand()
}
fun leftLeg() {
    moveRight()
    moveUp()
    moveUp()
    moveUp()
}
fun leftHand() {
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
    moveDown()
}
fun rightHand() {
    moveRight()
    moveRight()
    moveDown()
    moveDown()
}
