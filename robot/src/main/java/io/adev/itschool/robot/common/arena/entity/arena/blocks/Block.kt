package io.adev.itschool.robot.common.arena.entity.arena.blocks

import io.adev.itschool.robot.common.arena.entity.*
import io.adev.itschool.robot.common.arena.entity.arena.RobotStateMutationsProvider

abstract class Block(
    val position: Position,
) : RobotStateMutationsProvider, RobotState.Source {

    abstract val texture: Texture?
    val size = Size.Virtual(width = 1.vp, height = 1.vp)

    val horEnd: SizePoint.Virtual get() = position.x + size.width
    val verEnd: SizePoint.Virtual get() = position.y + size.height

    override fun toString(): String {
        return "${this::class.simpleName} $position $size"
    }

    override fun sourceRepresentation(): String {
        return "${this::class.simpleName} $position $size"
    }

    override fun equals(other: Any?): Boolean {
        return this.javaClass == other?.javaClass && other is Block &&
                this.position == other.position &&
                this.texture == other.texture
    }

    override fun hashCode(): Int {
        var result = position.hashCode()
        result = 31 * result + (texture?.hashCode() ?: 0)
        return result
    }

    enum class Texture {
        Platform,
        Password,
        Target
    }
}