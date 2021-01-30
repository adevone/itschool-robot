package io.adev.itschool.robot.eduard.work

import io.adev.itschool.robot.global.moveDown
import io.adev.itschool.robot.global.moveLeft
import io.adev.itschool.robot.global.moveRight
import io.adev.itschool.robot.global.moveUp


fun eduardLevel2(){
    moveRight(stepsCount = 6)
    moveDown(stepsCount = 2)
    moveLeft(stepsCount = 1)
    moveDown(stepsCount = 1)
    moveLeft(stepsCount = 2)
    moveUp(stepsCount = 1)
    moveLeft(stepsCount = 2)
    moveDown(stepsCount = 2)
    moveRight()
    moveDown()
    moveRight(stepsCount = 6)
    moveUp()
}