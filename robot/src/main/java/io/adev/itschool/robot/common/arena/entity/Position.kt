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

    fun moved(direction: Direction): Position {
        return when (direction) {
            Direction.Left -> copy(x = x - 1.vp)
            Direction.Right -> copy(x = x + 1.vp)
            Direction.Up -> copy(y = y - 1.vp)
            Direction.Down -> copy(y = y + 1.vp)
        }
    }

    enum class Direction {
        Left,
        Right,
        Up,
        Down
    }

    fun hash(): String {
        return "p${x.hash()}${y.hash()}"
    }

    override fun toString(): String = "$x,$y"
}
