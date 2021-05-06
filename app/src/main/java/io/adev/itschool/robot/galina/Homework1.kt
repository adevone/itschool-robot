package io.adev.itschool.robot.galina

import io.adev.itschool.robot.global.moveDown
import io.adev.itschool.robot.global.moveRight
import io.adev.itschool.robot.global.moveUp
import io.adev.itschool.robot.global.setHomework1Variant1Arena

fun homework1() {
    setHomework1Variant1Arena()
//    используем функцию движение по горе
    upHill()
//    используем функцию движение вдоль горы
    onTheHill()
//    используем функцию движение с горы
    fromTheHill()
}

fun upHill() {
    moveRight()
    moveUp()
    moveUp()
    moveRight()
    moveUp()
}

fun onTheHill() {
    moveRight()
    moveRight()
    moveRight()
    moveRight()
    moveRight()
    moveRight()
    moveRight()
    moveRight()
}

fun fromTheHill() {
    moveDown()
    moveDown()
    moveDown()
}