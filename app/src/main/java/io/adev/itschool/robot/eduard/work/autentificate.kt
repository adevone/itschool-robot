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

// Определяем функцию eduardAuth
fun eduardAuth() {
    // Вызываем функцию setArena, придавая ей значение arena6 в качестве параметра arena
    setArena(arena = arena6)
    // Запомни, robotToken имеет значение authenticate
    val robotToken1 = authenticate()
    // Вызываем функцию track
    track()
    // Вызываем функцию descent
    descent(robotToken = robotToken1)
    // Вызываем функцию cave
    cave(robotToken1)
    // Вызываем функцию output
    output(robotToken1)
}
// Определяем функцию track
fun track() {
    // Вызываем функцию moveRight, придавая ей значение 6 в качестве параметра stepsCount
    moveRight(stepsCount = 6)
}
// Определяем функцию descent
fun descent(robotToken: String) {
    // Вызываем функцию authorize, придывая ей значение robotToken в качестве параметра token
    authorize(token = robotToken)
    // Вызываем функцию moveDown, придавая ей значение 2 в качестве параметра stepsCount
    moveDown(stepsCount = 2)
    // Вызываем функцию moveLeft
    moveLeft()
    // Вызываем функцию moveDown
    moveDown()
    // Вызываем функцию moveLeft, придавая ей значение 2 в качестве параметра stepsCount
    moveLeft(stepsCount = 2)
}
// Определяем функцию cave
fun cave(robotToken: String) {
        moveUp()
    // Вызываем функцию moveLeft, придавая ей значение 2 в качестве параметра stepsCount
    moveLeft(stepsCount = 2)
    // Вызываем функцию authorize, придавая ей значение robotToken в качестве параметра token
    authorize(token = robotToken)
    // Вызываем функцию moveDown, придавая ей значение 2 в качестве параметра stepsCount
    moveDown(stepsCount = 2)
    // Вызываем функцию moveRight
    moveRight()
    // Вызываем функциб moveDown
    moveDown()
}
// Определяем функцию output
fun output(robotToken: String) {
    // Вызываем функцию moveRight, придавая ей значение 2 в качестве парамметра moveRight
    moveRight(stepsCount = 2)
    // Вызываем функцию authorize, придавая ей значение robotToken в качестве параметра token
    authorize(token = robotToken)
    // Вызываемм функцию moveRight, придавай ей значение 4 в качестве парамметр stepsCount
    moveRight(stepsCount = 4)
    // Вызываем функцию moveUp
    moveUp()
}
