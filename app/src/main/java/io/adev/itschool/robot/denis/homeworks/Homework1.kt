package io.adev.itschool.robot.denis.homeworks

import io.adev.itschool.robot.down
import io.adev.itschool.robot.left
import io.adev.itschool.robot.right
import io.adev.itschool.robot.up

fun leftLowPart() {
    right()
    up()
    up()
}

fun leftMiddlePart() {
    up()
    left()
    left()
    up()
    up()
    right()
    right()
}

fun centralhighPart() {
    up()
    up()
    right()
    right()
    down()
}

fun rightMiddlePart() {
    down()
    right()
    right()
    down()
    down()
}

fun denisHomework1() {
    leftLowPart()
    leftMiddlePart()
    centralhighPart()
    rightMiddlePart()

}