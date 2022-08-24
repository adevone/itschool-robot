package io.adev.itschool.robot

import io.adev.itschool.robot.common.arena.RobotController

fun createRobotController(): RobotController {
    return GlobalRobotController()
}

class GlobalRobotController : RobotController() {

    override fun run() {
        run(controller = this)
        io.adev.itschool.robot.run()
    }
}