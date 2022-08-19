package io.adev.itschool.robot.maxim

import io.adev.itschool.robot.global.getKey
import io.adev.itschool.robot.global.moveDown
import io.adev.itschool.robot.global.moveRight
import io.adev.itschool.robot.global.setArena
import io.adev.itschool.robot.global.useKey
import io.adev.itschool.robot.levels.homework3EduardArena

fun passHomeWork4() {
    setArena(arena = homework3EduardArena)
    val passMe = getKey()
    moveDown(5)
    useKey(key = passMe)
    moveDown(6)
    moveRight(3)
    useKey(key = passMe)
    moveRight(2)
    moveDown(2)
    useKey(key = passMe)
    moveDown(5)
    useKey(key = passMe)
    moveDown(3)
}
