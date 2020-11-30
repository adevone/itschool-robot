@file:Suppress("PackageDirectoryMismatch")

package io.adev.itschool.robot

import io.adev.itschool.robot.common.arena.Robot

lateinit var globalRobot: Robot

fun right(stepsCount: Int = 1) {
    globalRobot.right(stepsCount)
}

fun left(stepsCount: Int = 1) {
    globalRobot.left(stepsCount)
}

fun down(stepsCount: Int = 1) {
    globalRobot.down(stepsCount)
}

fun up(stepsCount: Int = 1) {
    globalRobot.up(stepsCount)
}