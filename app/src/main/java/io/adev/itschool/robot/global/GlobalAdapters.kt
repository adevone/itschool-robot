package io.adev.itschool.robot.global

import io.adev.itschool.robot.common.arena.RobotController
import io.adev.itschool.robot.common.arena.entity.arena.Arena
import io.adev.itschool.robot.levels.arena1
import io.adev.itschool.robot.levels.demoArena
import io.adev.itschool.robot.platform.arena.ArenaHolder

lateinit var globalRobotController: RobotController
lateinit var globalArenaHolder: ArenaHolder

fun setArena(arena: Arena) {
    globalArenaHolder.arena = arena
}

fun setDemoArena() {
    setArena(demoArena)
}

fun setArena1() {
    setArena(arena1)
}

/**
 * Передвинуться вправо на [stepsCount]
 * Если [stepsCount] не указано, то на 1 шаг
 */
fun moveRight(stepsCount: Int = 1) {
    globalRobotController.moveRight(stepsCount)
}

/**
 * Передвинуться влево на [stepsCount].
 * Если [stepsCount] не указано, то на 1 шаг.
 */
fun moveLeft(stepsCount: Int = 1) {
    globalRobotController.moveLeft(stepsCount)
}

/**
 * Передвинуться вниз на [stepsCount].
 * Если [stepsCount] не указано, то на 1 шаг.
 */
fun moveDown(stepsCount: Int = 1) {
    globalRobotController.moveDown(stepsCount)
}

/**
 * Передвинуться вверх на [stepsCount].
 * Если [stepsCount] не указано, то на 1 шаг.
 */
fun moveUp(stepsCount: Int = 1) {
    globalRobotController.moveUp(stepsCount)
}

/**
 * Показать [password] на дисплее робота.
 * Нужно для прохождения блоков с паролем.
 */
fun display(password: String) {
    globalRobotController.display(password)
}