package io.adev.itschool.robot.maxim

import io.adev.itschool.robot.global.getKey
import io.adev.itschool.robot.global.moveDown
import io.adev.itschool.robot.global.moveLeft
import io.adev.itschool.robot.global.moveRight
import io.adev.itschool.robot.global.moveUp
import io.adev.itschool.robot.global.setArena
import io.adev.itschool.robot.global.useKey
import io.adev.itschool.robot.levels.homework4DenisArena

fun passArena6() {
    setArena(arena = homework4DenisArena)
    moveRight(6)
    val myKey = getKey()
    useKey(key = myKey)
    moveDown(2)
    moveLeft()
    moveDown()
    moveLeft(2)
    moveUp()
    moveLeft(2)
    useKey(key = myKey)
    moveDown(2)
    moveRight()
    moveDown()
    moveRight(2)
    useKey(key = myKey)
    moveRight(4)
    moveUp()
}
