package io.adev.itschool.robot.eduard.homeworks

import io.adev.itschool.robot.global.moveDown
import io.adev.itschool.robot.levels.homework1EdwardArena
import io.adev.itschool.robot.global.moveRight
import io.adev.itschool.robot.global.setArena
import io.adev.itschool.robot.global.moveUp

fun eduardHomework1() {
    setArena(homework1EdwardArena)
    koleso1()
    bamper()
    kyzov()
    koleso2()
}

fun koleso1() {
    moveRight()
    moveUp()
    moveUp()
    moveRight()
}

fun bamper() {
    moveUp()
    moveRight()
}

fun kyzov() {
    moveRight()
    moveRight()
    moveRight()
    moveRight()
    moveRight()
    moveRight()
    moveRight()
}

fun koleso2() {
    moveDown()
    moveDown()
    moveDown()
}