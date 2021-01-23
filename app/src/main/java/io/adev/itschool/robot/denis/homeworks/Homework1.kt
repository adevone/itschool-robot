package io.adev.itschool.robot.denis.homeworks

import io.adev.itschool.robot.down
import io.adev.itschool.robot.left
import io.adev.itschool.robot.right
import io.adev.itschool.robot.up

fun denisHomework1() {
    leftFoot()
    leftHand()
    head()
    rightHand()
}

fun leftFoot() {
    right()
    up()
    up()
}

fun leftHand() {
    up()
    left()
    left()
    up()
    up()
    right()
    right()
}

fun head() {
    up()
    up()
    right()
    right()
    down()
}

fun rightHand() {
    down()
    right()
    right()
    down()
    down()
}

