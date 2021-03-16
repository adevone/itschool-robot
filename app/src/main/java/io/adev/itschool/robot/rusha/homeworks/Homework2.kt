package io.adev.itschool.robot.rusha.homeworks

import io.adev.itschool.robot.global.display
import io.adev.itschool.robot.global.moveDown
import io.adev.itschool.robot.global.moveLeft
import io.adev.itschool.robot.global.moveRight
import io.adev.itschool.robot.global.moveUp
import io.adev.itschool.robot.global.setArena
import io.adev.itschool.robot.levels.arena4

fun rushaHomework2() {
    setArena(arena = arena4)
    lvl1()
    lvl2()
    lvl3()
}
fun lvl1() {
    moveRight()
    moveRight()
    moveRight()
    moveRight()
    moveRight()
    moveRight()
    display(password = "p62")
}

fun lvl2() {
    moveDown()
    moveDown()
    moveLeft()
    moveDown()
    moveLeft()
    moveLeft()
    moveUp()
    moveLeft()
    moveLeft()
    display(password = "p14")
}

fun lvl3() {
    moveDown()
    moveDown()
    moveRight()
    moveDown()
    moveRight()
    moveRight()
    display(password = "p56")
    moveRight()
    moveRight()
    moveRight()
    moveRight()
    moveUp()
}


