package io.adev.itschool.robot.platform

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.adev.itschool.robot.app.R
import io.adev.itschool.robot.arenaDraw
import io.adev.itschool.robot.run
import io.adev.itschool.robot.globalRobot
import io.adev.itschool.robot.platform.arena.ArenaFragment

class MainActivity : AppCompatActivity() {

    private val arenaFragment = ArenaFragment.newInstance(
        arenaDraw = arenaDraw,
        userAction = { robot, arena ->
            globalRobot = robot
            run()
            run(robot, arena)
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentsContainer, arenaFragment)
            .commit()
    }
}