package io.adev.itschool.robot.eduard.work

import io.adev.itschool.robot.global.*
import io.adev.itschool.robot.levels.arena6

//pppppppppp
//r        p
//pppppp#ppp
//p   p  ppp
//p#p   pppp
//p  pppp tp
//pp   #   p
//pppppppppp
fun eduardAuth() {
    setArena(arena = arena6)
    val robotToken = authenticate()
    moveRight(stepsCount = 6)
    authorize(token = robotToken)
    moveDown(stepsCount = 2)
    moveLeft()
    moveDown()
    moveLeft(stepsCount = 2)
    moveUp()
    moveLeft(stepsCount = 2)
    authorize(token = robotToken)
    moveDown(stepsCount = 2)
    moveRight()
    moveDown()
    moveRight(stepsCount = 2)
    authorize(token = robotToken)
    moveRight(stepsCount = 4)
    moveUp()
}
