package io.adev.itschool.robot.denis.homeworks

import io.adev.itschool.robot.*
import io.adev.itschool.robot.levels.level1
import io.adev.itschool.robot.levels.levelHomework1Denis

fun denisHomework1() {
    setArenaDraw(levelHomework1Denis)
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

