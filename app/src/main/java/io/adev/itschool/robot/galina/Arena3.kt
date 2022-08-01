package io.adev.itschool.robot.galina

import io.adev.itschool.robot.global.display
import io.adev.itschool.robot.global.moveDown
import io.adev.itschool.robot.global.moveRight
import io.adev.itschool.robot.global.setArena
import io.adev.itschool.robot.levels.arena3

fun galinaArena3() {
    setArena(arena = arena3)
    display(password = "p31")
    moveRight(stepsCount = 2)
    moveDown()
}