package io.adev.itschool.robot.galina

import io.adev.itschool.robot.global.*

fun homework1Wariant2() {
    setHomework1Variant2Arena()
    visitO()
    visitConnektion()
    visitK()
}

fun visitO() {
    moveLeft()
    moveUp()
    moveLeft()
    moveUp()
    moveUp()
    moveUp()
    moveRight()
    moveUp()
    moveRight()
    moveRight()
    moveRight()
    moveRight()
    moveDown()
    moveRight()
    moveDown()
    moveDown()
    moveRight()
}

fun visitConnektion() {
    moveRight()
    moveRight()
}

fun visitK() {
    moveRight()
    moveRight()
    moveRight()
    moveRight()
    moveUp()
    moveUp()
    moveRight()
    moveUp()
    moveRight()
}