package io.adev.itschool.robot

import io.adev.itschool.robot.global.moveRight
import io.adev.itschool.robot.global.setArena
import io.adev.itschool.robot.levels.homework3EduardArena

// определяем функцию выполнить
fun run() {
    setArena(arena = homework3EduardArena)
    moveRight()
}