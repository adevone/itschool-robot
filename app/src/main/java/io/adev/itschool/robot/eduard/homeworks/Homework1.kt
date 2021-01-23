package io.adev.itschool.robot.eduard.homeworks

import io.adev.itschool.robot.down
import io.adev.itschool.robot.levels.levelHomework1Denis
import io.adev.itschool.robot.levels.levelHomework1Edward
import io.adev.itschool.robot.right
import io.adev.itschool.robot.setArenaDraw
import io.adev.itschool.robot.up

fun eduardHomework1() {
    setArenaDraw(levelHomework1Edward)
    koleso1()
    bamper()
    kyzov()
    koleso2()
}

fun koleso1() {
    right()
    up()
    up()
    right()
}

fun bamper() {
    up()
    right()
}

fun kyzov() {
    right()
    right()
    right()
    right()
    right()
    right()
    right()
}

fun koleso2() {
    down()
    down()
    down()
}