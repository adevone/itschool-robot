package io.adev.itschool.robot

import io.adev.itschool.robot.common.arena.Robot
import io.adev.itschool.robot.common.arena.entity.arena.parseArena
import io.adev.itschool.robot.platform.arena.ArenaHolder

lateinit var globalRobot: Robot
lateinit var globalArenaHolder: ArenaHolder

fun setArenaDraw(arenaDraw: String) {
    globalArenaHolder.arena = parseArena(arenaDraw)
}

/**
 * Передвинуться вправо на [stepsCount]
 * Если [stepsCount] не указано, то на 1 шаг
 */
fun right(stepsCount: Int = 1) {
    globalRobot.right(stepsCount)
}

/**
 * Передвинуться влево на [stepsCount].
 * Если [stepsCount] не указано, то на 1 шаг.
 */
fun left(stepsCount: Int = 1) {
    globalRobot.left(stepsCount)
}

/**
 * Передвинуться вниз на [stepsCount].
 * Если [stepsCount] не указано, то на 1 шаг.
 */
fun down(stepsCount: Int = 1) {
    globalRobot.down(stepsCount)
}

/**
 * Передвинуться вверх на [stepsCount].
 * Если [stepsCount] не указано, то на 1 шаг.
 */
fun up(stepsCount: Int = 1) {
    globalRobot.up(stepsCount)
}

/**
 * Показать [password] на дисплее робота.
 * Нужно для прохождения блоков с паролем.
 */
fun display(password: String) {
    globalRobot.display(password)
}