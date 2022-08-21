package io.adev.itschool.robot.global

import io.adev.itschool.robot.common.arena.RobotController
import io.adev.itschool.robot.common.arena.entity.arena.Arena
import io.adev.itschool.robot.levels.arena1
import io.adev.itschool.robot.levels.demoArena
import io.adev.itschool.robot.levels.homework1Variant1Arena
import io.adev.itschool.robot.levels.homework1Variant2Arena
import io.adev.itschool.robot.levels.homework1Variant3Arena

lateinit var globalRobotController: RobotController

fun setArena(arena: Arena) {
    globalRobotController.updateArena(arena)
}

fun setDemoArena() {
    setArena(arena = demoArena)
}

fun setArena1() {
    setArena(arena = arena1)
}

fun setHomework1Variant1Arena() {
    setArena(arena = homework1Variant1Arena)
}

fun setHomework1Variant2Arena() {
    setArena(arena = homework1Variant2Arena)
}

fun setHomework1Variant3Arena() {
    setArena(arena = homework1Variant3Arena)
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

fun getKey(): String {
    return globalRobotController.getKey()
}

fun useKey(key: String) {
    globalRobotController.useKey(key)
}

fun currentCode(): Int {
    return globalRobotController.currentCode()
}

fun setBeforeMove(beforeMove: () -> Unit) {
    return globalRobotController.setBeforeMove(beforeMove)
}