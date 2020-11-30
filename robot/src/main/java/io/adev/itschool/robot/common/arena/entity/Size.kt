package io.adev.itschool.robot.common.arena.entity

sealed class Size(
    val width: SizePoint,
    val height: SizePoint
) {
    /**
     * For which value you must multiply [width] to get [height]
     */
    val ratio: Float = height / width

    class Virtual(width: SizePoint, height: SizePoint) : Size(width, height)
    class Real(width: SizePoint, height: SizePoint) : Size(width, height)

    override fun toString(): String = "$width*$height"

    override fun equals(other: Any?): Boolean {
        if (other !is Size) return false
        return width == other.width &&
                height == other.height
    }

    override fun hashCode(): Int {
        var result = width.hashCode()
        result = 31 * result + height.hashCode()
        return result
    }
}