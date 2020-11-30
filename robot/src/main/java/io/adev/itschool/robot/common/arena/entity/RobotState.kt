package io.adev.itschool.robot.common.arena.entity

data class RobotState(
    val position: Position,
    val isDestroyed: Boolean = false,
    val isWon: Boolean = false,
    val source: Source? = null
) {
    fun destroyed(source: Source): RobotState {
        return copy(isDestroyed = true, source = source)
    }

    fun won(source: TargetBlock): RobotState {
        return copy(isWon = true, source = source)
    }

    fun move(movement: Position.Movement, source: Source): RobotState {
        return copy(position = position.move(movement), source = source)
    }

    val size = Size.Virtual(width = 1.vp, height = 1.vp)

    override fun toString(): String {
        return "$position isDestroyed=$isDestroyed from=${source?.sourceRepresentation()}"
    }

    override fun equals(other: Any?): Boolean {
        if (other !is RobotState) return false
        return position == other.position &&
                isWon == other.isWon &&
                isDestroyed == other.isDestroyed &&
                size == other.size
    }

    override fun hashCode(): Int {
        var result = position.hashCode()
        result = 31 * result + isDestroyed.hashCode()
        result = 31 * result + isWon.hashCode()
        result = 31 * result + size.hashCode()
        return result
    }

    interface Source {
        fun sourceRepresentation(): String
    }
}