package io.adev.itschool.robot.common.arena.entity

import io.adev.itschool.robot.common.arena.entity.arena.TargetBlock

data class RobotState(
    val position: Position,
    val text: String = "",
    val finishReason: String? = null,
    val wasMoved: Boolean = false,
    val authorizedPosition: Position? = null,
    val currentToken: String? = null,
    val isWon: Boolean = false,
    val source: Source? = null,
) {
    fun destroyed(source: Source): RobotState {
        return copy(finishReason = "robot is destroyed", source = source)
    }

    fun won(source: TargetBlock): RobotState {
        return copy(isWon = true, source = source)
    }

    fun move(movement: Position.Movement, source: Source): RobotState {
        return copy(
            position = position.move(movement),
            wasMoved = true,
            currentToken = null,
            source = source
        )
    }

    val size = Size.Virtual(width = 1.vp, height = 1.vp)

    override fun toString(): String {
        return "$position finishReason=$finishReason from=${source?.sourceRepresentation()}"
    }

    override fun equals(other: Any?): Boolean {
        if (other !is RobotState) return false
        return position == other.position &&
                isWon == other.isWon &&
                finishReason == other.finishReason &&
                size == other.size
    }

    override fun hashCode(): Int {
        var result = position.hashCode()
        result = 31 * result + finishReason.hashCode()
        result = 31 * result + isWon.hashCode()
        result = 31 * result + size.hashCode()
        return result
    }

    fun display(text: String): RobotState {
        return copy(text = text)
    }

    fun authenticate(): RobotState {
        return if (!wasMoved)
            copy(authorizedPosition = position)
        else
            throw RobotWasMovedException()
    }

    fun authorize(token: String): RobotState {
        return copy(currentToken = token)
    }

    fun checkToken() {
        val hash = authorizedPosition?.hash() ?: throw NotAuthenticatedException()
        if (currentToken != hash) {
            throw NotAuthorizedException()
        }
    }

    fun getToken(): String {
        return authorizedPosition?.hash() ?: throw NotAuthenticatedException()
    }

    fun finish(reason: String?): RobotState {
        return copy(finishReason = reason)
    }

    interface Source {
        fun sourceRepresentation(): String
    }
}

class RobotWasMovedException : IllegalStateException("robot must not be moved yet to authorization")

class NotAuthenticatedException : IllegalStateException("robot is not authenticated")

class NotAuthorizedException : IllegalStateException("robot is not authorized")