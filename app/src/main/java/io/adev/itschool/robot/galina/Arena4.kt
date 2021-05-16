package io.adev.itschool.robot.galina

import io.adev.itschool.robot.global.*
import io.adev.itschool.robot.levels.arena4

fun galinaArena4() {
    setArena(arena = arena4)
    display(password = "p62")
    moveRight(stepsCount = 6)
    moveDown(stepsCount = 2)
    display(password = "p14")
    moveLeft()
    moveDown()
    //вызываем moveLeft передавая 2 в качестве stepsCount
    //используем функцию moveLeft определяя параметр stepsCount как 2
    moveLeft(stepsCount = 2)
    moveUp()
    moveLeft(stepsCount = 2)
    moveDown(stepsCount = 2)
    display(password = "p56")
    moveRight()
    moveDown()
    moveRight(stepsCount = 6)
    moveUp()

}