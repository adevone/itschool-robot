package io.adev.itschool.robot.platform.arena

import io.adev.itschool.robot.common.arena.entity.arena.Arena

class ArenaSetter(
    private val onSet: (Arena) -> Unit,
) {
    fun set(arena: Arena) {
        onSet(arena)
    }
}