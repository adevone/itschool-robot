package io.adev.itschool.robot.galina

import io.adev.itschool.robot.global.*
import io.adev.itschool.robot.levels.arena5

fun passArena5() {
    setArena(arena = arena5)
    moveRight()
    useKey(key = getKey())
    moveRight()
    moveDown()
}
