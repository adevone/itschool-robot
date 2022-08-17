package io.adev.itschool.robot.platform

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.adev.itschool.robot.app.R
import io.adev.itschool.robot.createRobotController
import io.adev.itschool.robot.global.globalRobotController
import io.adev.itschool.robot.platform.arena.ArenaFragment

class MainActivity : AppCompatActivity() {

    private val arenaFragment = ArenaFragment.newInstance {
        val robotController = createRobotController()
        globalRobotController = robotController
        robotController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentsContainer, arenaFragment)
            .commit()
    }
}