package io.adev.itschool.robot.maxim

import io.adev.itschool.robot.global.display
import io.adev.itschool.robot.global.moveDown
import io.adev.itschool.robot.global.moveLeft
import io.adev.itschool.robot.global.moveRight
import io.adev.itschool.robot.global.setArena
import io.adev.itschool.robot.levels.homework2Variant1Arena

fun passArena2() {
    setArena(arena = homework2Variant1Arena)
    eye()
    nose()
    jaw()
}

private fun eye() {
    moveRight(stepsCount = 3)
    val p = "p710"
    display(password = p)
    moveDown(stepsCount = 2)
}

private fun nose() {
    moveRight()
    moveDown(stepsCount = 2)
    display(password = "p713")
    moveLeft()
}

private fun jaw() {
    moveDown(3)
    moveLeft(2)
}
