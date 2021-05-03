package io.adev.itschool.robot.galina

import io.adev.itschool.robot.global.*

fun arena1() {
    setArena1()
    leg()
    leftHand()
    head()
    rightHand()
}
fun leg() {
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