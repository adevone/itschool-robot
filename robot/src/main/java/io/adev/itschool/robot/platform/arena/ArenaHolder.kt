package io.adev.itschool.robot.platform.arena

import io.adev.itschool.robot.common.arena.entity.arena.Arena

class ArenaHolder(
    private val onSet: (Arena) -> Unit,
) {
    var arena: Arena? = null
        set(arena) {
            field = arena
            if (arena != null) {
                onSet(arena)
            }
        }
}