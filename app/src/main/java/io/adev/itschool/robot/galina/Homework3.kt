package io.adev.itschool.robot.galina

import io.adev.itschool.robot.global.*
import io.adev.itschool.robot.levels.homework3DenisArena

fun passHomework3(){
    setArena(arena = homework3DenisArena)
    val robotKey: String = getKey()
    paw(robotKey = robotKey)
    stomach(robotKey=robotKey)
    head(robotKey= robotKey)
}

fun paw(robotKey: String){
    moveRight(stepsCount = 3)
    useKey(key = robotKey)
    moveUp()
}

fun stomach(robotKey: String){
    moveRight(stepsCount = 2)
    moveDown()
    moveRight(stepsCount = 5)
    moveUp(stepsCount = 2)
    useKey(key = robotKey)
    moveRight()
}

fun head(robotKey: String){
    moveRight(stepsCount = 3)
    useKey(key = robotKey)
    moveRight(stepsCount = 6)
    useKey(key = robotKey)
    moveRight(stepsCount = 3)
    useKey(key = robotKey)
    moveDown(stepsCount = 2)
}