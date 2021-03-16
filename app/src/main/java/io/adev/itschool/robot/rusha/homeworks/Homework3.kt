package io.adev.itschool.robot.rusha.homeworks

import io.adev.itschool.robot.global.authenticate
import io.adev.itschool.robot.global.authorize
import io.adev.itschool.robot.global.moveDown
import io.adev.itschool.robot.global.moveLeft
import io.adev.itschool.robot.global.moveRight
import io.adev.itschool.robot.global.moveUp
import io.adev.itschool.robot.global.setArena
import io.adev.itschool.robot.levels.arena6

fun rushaHomework3() {
    setArena(arena6)
    val porol = authenticate()
    floor1(porol1 = porol)
    floor2(porol2 = porol)
    floor3(porol3 = porol)
}

fun floor1(porol1: String) {
    moveRight()
    moveRight()
    moveRight()
    moveRight()
    moveRight()
    moveRight()
    authorize(token = porol1)
}

fun floor2(porol2: String) {
    moveDown()
    moveDown()
    moveLeft()
    moveDown()
    moveLeft()
    moveLeft()
    moveUp()
    moveLeft()
    moveLeft()
    authorize(token = porol2)
}

fun floor3(porol3: String) {
    moveDown()
    moveDown()
    moveRight()
    moveDown()
    moveRight()
    moveRight()
    authorize(token = porol3)
    moveRight()
    moveRight()
    moveRight()
    moveRight()
    moveUp()
}