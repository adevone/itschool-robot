package io.adev.itschool.robot.rusha

import io.adev.itschool.robot.global.getKey
import io.adev.itschool.robot.global.moveDown
import io.adev.itschool.robot.global.moveLeft
import io.adev.itschool.robot.global.moveRight
import io.adev.itschool.robot.global.moveUp
import io.adev.itschool.robot.global.setArena
import io.adev.itschool.robot.global.useKey
import io.adev.itschool.robot.levels.arena3
import io.adev.itschool.robot.levels.arena6

fun rushaHomework6() {
    setArena(arena6)
    val key = getKey()
    moveRight(6)
    useKey(key)
    moveDown(2)
    moveLeft()
    moveDown()
    moveLeft(2)
    moveUp()
    moveLeft(2)
    useKey(key)
    moveDown(2)
    moveRight()
    moveDown()
    moveRight(2)
    useKey(key)
    moveRight(4)
    moveUp()
}



