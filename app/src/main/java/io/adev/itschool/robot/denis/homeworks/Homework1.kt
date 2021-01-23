package io.adev.itschool.robot.denis.homeworks

import io.adev.itschool.robot.down
import io.adev.itschool.robot.left
import io.adev.itschool.robot.right
import io.adev.itschool.robot.up

fun denisHomework1() {
    oppositeLetterL()
    leftMiddleSquare()
    centralHighSquare()
    rightMiddleSquare()
}

fun oppositeLetterL() {
    right()
    up()
    up()
}

fun leftMiddleSquare() {
    up()
    left()
    left()
    up()
    up()
    right()
    right()
}

fun centralHighSquare() {
    up()
    up()
    right()
    right()
    down()
}

fun rightMiddleSquare() {
    down()
    right()
    right()
    down()
    down()
}

