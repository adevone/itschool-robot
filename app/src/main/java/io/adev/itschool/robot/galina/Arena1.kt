package io.adev.itschool.robot.galina

import io.adev.itschool.robot.global.*

fun arena1() {
    setArena1()
    leg()
    leftHand()
    head()
    rightHand()
}
private fun leg() {
    moveRight()
    moveUp()
    moveUp()
    moveUp()
}
private fun leftHand() {
    moveLeft()
    moveLeft()
    moveUp()
    moveUp()
    moveRight()
    moveRight()
}
private fun head() {
    moveUp()
    moveUp()
    moveRight()
    moveRight()
    moveDown()
    moveDown()
}
private fun rightHand() {
    moveRight()
    moveRight()
    moveDown()
    moveDown()
}