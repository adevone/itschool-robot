package io.adev.itschool.robot.maxim

import io.adev.itschool.robot.common.arena.RobotController
import io.adev.itschool.robot.global.moveLeft
import io.adev.itschool.robot.global.moveUp
import io.adev.itschool.robot.levels.arena6

fun passHomeWork5(controller: RobotController) {
    controller.arena = arena6
    controller.moveRight(6)
    val key = controller.getKey()
    controller.useKey(key)
    controller.moveDown(2)
    controller.moveLeft()
    controller.moveDown()
    controller.moveLeft(2)
    controller.moveUp()
    controller.moveLeft(2)
    controller.useKey(key)
    controller.moveDown(2)
    controller.moveRight()
    controller.moveDown()
    controller.moveRight(2)
    controller.useKey(key)
    controller.moveRight(4)
    controller.moveUp()


}