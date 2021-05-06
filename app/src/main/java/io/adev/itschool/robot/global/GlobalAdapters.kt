package io.adev.itschool.robot.global

import io.adev.itschool.robot.common.arena.RobotController
import io.adev.itschool.robot.common.arena.entity.arena.Arena
import io.adev.itschool.robot.levels.arena1
import io.adev.itschool.robot.levels.demoArena
import io.adev.itschool.robot.levels.homework1Variant1Arena
import io.adev.itschool.robot.levels.homework1Variant2Arena
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

fun setHomework1Variant1Arena() {
    setArena(homework1Variant1Arena)
}

fun setHomework1Variant2Arena() {
    setArena(homework1Variant2Arena)
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

fun authenticate(): String {
    return globalRobotController.authenticate()
}

fun authorize(token: String) {
    globalRobotController.authorize(token)
}

fun isAuthLeft(): Boolean {
    return globalRobotController.isAuthLeft(arena = globalArenaHolder.arena!!)
}

fun isAuthRight(): Boolean {
    return globalRobotController.isAuthRight(arena = globalArenaHolder.arena!!)
}

fun isAuthUp(): Boolean {
    return globalRobotController.isAuthUp(arena = globalArenaHolder.arena!!)
}

fun isAuthDown(): Boolean {
    return globalRobotController.isAuthDown(arena = globalArenaHolder.arena!!)
}

fun currentCode(): Int {
    return globalRobotController.currentCode(arena = globalArenaHolder.arena!!)
}

fun setBeforeMove(beforeMove: () -> Unit) {
    return globalRobotController.setBeforeMove(beforeMove)
}