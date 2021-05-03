package io.adev.itschool.robot.galina

import io.adev.itschool.robot.global.moveDown
import io.adev.itschool.robot.global.moveRight
import io.adev.itschool.robot.global.setArena
import io.adev.itschool.robot.levels.demoArena

fun task1() {
    setArena(demoArena)
    //используем функцию переместить вправо
    moveRight()
    //используем функцию переместить вправо
    moveRight()
    //используем функцию переместить вниз
    moveDown()
}