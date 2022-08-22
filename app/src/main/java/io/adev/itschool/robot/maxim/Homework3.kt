package io.adev.itschool.robot.maxim

import io.adev.itschool.robot.global.getKey
import io.adev.itschool.robot.global.moveDown
import io.adev.itschool.robot.global.moveRight
import io.adev.itschool.robot.global.setArena
import io.adev.itschool.robot.global.useKey
import io.adev.itschool.robot.levels.homework3EduardArena

fun passHomeWork3() {
    setArena(arena = homework3EduardArena)
    val passMe = getKey()
    passForehead(passMe)
    passEye(passMe)
    passNose(passMe)
    passLips(passMe)
}

fun passForehead(passMe: String) {
    moveDown(5)
    useKey(key = passMe)
    moveDown(1)
}

fun passEye(passMe: String) {
    moveDown(5)
    moveRight(3)
    useKey(key = passMe)
    moveRight(1)
}

fun passNose(passMe: String) {
    moveRight(1)
    moveDown(2)
    useKey(key = passMe)
    moveDown(1)
}

fun passLips(passMe: String) {
    moveDown(4)
    useKey(key = passMe)
    moveDown(3)
}