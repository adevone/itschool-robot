package io.adev.itschool.robot

import io.adev.itschool.robot.global.moveRight
import io.adev.itschool.robot.global.moveUp
import io.adev.itschool.robot.global.setArena
import io.adev.itschool.robot.levels.demoArena

// определяем функцию выполнить
fun run() {
    setArena(demoArena)
    moveRight()
    moveUp()
}