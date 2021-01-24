package io.adev.itschool.robot

import io.adev.itschool.robot.global.moveDown
import io.adev.itschool.robot.global.moveRight
import io.adev.itschool.robot.global.setDemoArena

fun run() {
    setDemoArena()
    moveRight()
    moveRight()
    moveDown()
}