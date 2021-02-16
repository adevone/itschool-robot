package io.adev.itschool.robot.examples

import io.adev.itschool.robot.global.authenticate
import io.adev.itschool.robot.global.authorize
import io.adev.itschool.robot.global.currentCode
import io.adev.itschool.robot.global.display
import io.adev.itschool.robot.global.moveDown
import io.adev.itschool.robot.global.moveLeft
import io.adev.itschool.robot.global.moveRight
import io.adev.itschool.robot.global.setArena
import io.adev.itschool.robot.levels.arena7

fun simplicityExample() {
    setArena(arena = arena7)
    val robotToken1 = authenticate()
    lestnica()
    coridor()
    spyck()
    praymay(robotToken = robotToken1)
}

fun lestnica() {
    moveDown()
    moveLeft()
    moveDown()
    moveLeft()
    moveDown(stepsCount = 2)
}

fun coridor() {
    moveRight()
    display(password = codeToString(code = currentCode()))
    moveRight()
}

fun spyck() {
    moveDown(stepsCount = 4)
}

fun praymay(robotToken: String) {
    moveLeft(stepsCount = 3)
    // вызвать display
    display(
        // передав в качестве пароля объект возвращённый из codeToString
        password = codeToString(
            // при вызове которого в качестве кода был передан текущий код
            code = currentCode()
        )
    )
    moveLeft(stepsCount = 2)
    authorize(token = robotToken)
    moveLeft(stepsCount = 3)
}

/**
 * Преобразует числовой код в текстовый
 *
 * code должен быть от 0 до 9
 */
fun codeToString(code: Int): String {
    if (code == 0)
        return "zero"
    else if (code == 1)
        return "one"
    else if (code == 2)
        return "two"
    else if (code == 3)
        return "three"
    else if (code == 4)
        return "four"
    else if (code == 5)
        return "five"
    else if (code == 6)
        return "six"
    else if (code == 7)
        return "seven"
    else if (code == 8)
        return "eight"
    else if (code == 9)
        return "nine"
    else
        throw IllegalArgumentException("wrong code")
}