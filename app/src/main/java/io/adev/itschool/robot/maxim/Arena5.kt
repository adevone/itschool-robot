package io.adev.itschool.robot.maxim

import io.adev.itschool.robot.global.getKey
import io.adev.itschool.robot.global.moveDown
import io.adev.itschool.robot.global.moveRight
import io.adev.itschool.robot.global.setArena
import io.adev.itschool.robot.global.useKey
import io.adev.itschool.robot.levels.arena5

fun passArena5() {
    setArena(arena = arena5)
    moveRight()
    useKey(key = getKey())
    moveRight()
    moveDown()
}