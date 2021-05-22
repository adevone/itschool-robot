package io.adev.itschool.robot.galina

import io.adev.itschool.robot.global.*
import io.adev.itschool.robot.levels.arena6

fun passArena6() {
    setArena(arena = arena6)
    val robotKey: String = getKey()
    //    используем функцию движение по второму этажу
    secondFloor()
    //    используем функцию движение по первому этажу
    groundFloor(robotKey = robotKey)
    //    используем функцию движение по цокольному этажу (подвалу)
    basement(robotKey = robotKey)
}

fun secondFloor() {
    //вызываем moveRight передавая 6 в качестве stepsCount
    //используем функцию moveRight определяя параметр stepsCount как 6
    moveRight(stepsCount = 6)
    }

fun groundFloor(robotKey: String) {
    enterKey(key = robotKey)
    //вызываем  moveDown передавая 2 в качестве stepsCount
    //используем функцию  moveDown определяя параметр stepsCount как 2
    moveDown(stepsCount = 2)
    //вызываем   moveLeft передавая 1 в качестве stepsCount
    //используем функцию   moveLeft определяя параметр stepsCount как 1
    moveLeft()
    //вызываем moveDown передавая 1 в качестве stepsCount
    //используем функцию moveDown определяя параметр stepsCount как 1
    moveDown()
    //вызываем  moveLeft передавая 2 в качестве stepsCount
    //используем функцию  moveLeft определяя параметр stepsCount как 2
    moveLeft(stepsCount = 2)
    //вызываем moveUp передавая 1 в качестве stepsCount
    //используем функцию moveUp определяя параметр stepsCount как 1
    moveUp()
    //вызываем  moveLeft передавая 2 в качестве stepsCount
    //используем функцию  moveLeft определяя параметр stepsCount как 2
    moveLeft(stepsCount = 2)
}

fun basement(robotKey: String) {
    enterKey(key = robotKey)
    //вызываем  moveDown передавая 2 в качестве stepsCount
    //используем функцию  moveDown определяя параметр stepsCount как 2
    moveDown(stepsCount = 2)
    //вызываем moveRight передавая 1 в качестве stepsCount
    //используем функцию moveRight определяя параметр stepsCount как 1
    moveRight()
    //вызываем moveDown передавая 1 в качестве stepsCount
    //используем функцию moveDown определяя параметр stepsCount как 1
    moveDown()
    //вызываем moveRight передавая 2 в качестве stepsCount
    //используем функцию moveRight определяя параметр stepsCount как 2
    moveRight(stepsCount = 2)
    enterKey(key = robotKey)
    //вызываем moveRight передавая 4 в качестве stepsCount
    //используем функцию moveRight определяя параметр stepsCount как 4
    moveRight(stepsCount = 4)
    //вызываем moveUp передавая 1 в качестве stepsCount
    //используем функцию moveUp определяя параметр stepsCount как 1
    moveUp()
}
