package io.adev.itschool.robot.eduard.homeworks

import io.adev.itschool.robot.global.*
import io.adev.itschool.robot.levels.arena4


fun eduardHomework2() {
    // Вызываем функцию setArena, передавая ей arena4
    setArena(arena4)
    // Вызываем функцию perelrestok
    perelrestok()
    // Вызываем функцию stepenkavniz
    stepenkavniz()
    // Вы
    obhodprepyadstviya()
    klyshka()
}

fun perelrestok() {
    moveLeft(stepsCount = 6)
    display(password = "p62")
}

fun stepenkavniz() {
    moveDown(stepsCount = 2)
    moveRight()
    moveDown()
}

fun obhodprepyadstviya() {
    moveLeft(stepsCount = 2)
    moveUp()
    moveLeft(stepsCount = 2)
    display(password = "p14")
    moveDown(stepsCount = 2)
    moveRight()
    moveDown()
}

fun klyshka() {
    display(password = "p56")
    moveRight(stepsCount = 6)
    moveUp()
}





