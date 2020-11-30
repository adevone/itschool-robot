package io.adev.itschool.robot.common.arena.entity

data class Position(
    val x: SizePoint.Virtual,
    val y: SizePoint.Virtual
) {
    fun isIn(size: Size): Boolean {
        val isInHor = 0.vp <= x && x < size.width
        val isInVer = 0.vp <= y && y < size.height
        return isInHor && isInVer
    }

    fun move(movement: Movement): Position {
        return when (movement) {
            Movement.Left -> copy(x = x - 1.vp)
            Movement.Right -> copy(x = x + 1.vp)
            Movement.Up -> copy(y = y - 1.vp)
            Movement.Down -> copy(y = y + 1.vp)
        }
    }

    enum class Movement {
        Left,
        Right,
        Up,
        Down
    }

    override fun toString(): String = "$x,$y"
}
