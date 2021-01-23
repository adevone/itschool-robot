package io.adev.itschool.robot

import io.adev.itschool.robot.common.arena.Robot
import io.adev.itschool.robot.common.arena.entity.arena.Arena
import io.adev.itschool.robot.common.arena.entity.arena.parseArena
import kotlinx.coroutines.flow.MutableStateFlow

lateinit var globalRobot: Robot
lateinit var globalArenaFlow: MutableStateFlow<Arena?>

fun setArenaDraw(arenaDraw: String) {
    val arena = arenaDraw.parseArena()
    globalArenaFlow.value = arena
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
 * Показать [text] на дисплее робота.
 * Нужно для прохождения блоков с паролем.
 */
fun display(text: String) {
    globalRobot.display(text)
}