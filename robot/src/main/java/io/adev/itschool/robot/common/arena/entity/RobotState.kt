package io.adev.itschool.robot.common.arena.entity

import io.adev.itschool.robot.common.arena.entity.arena.TargetBlock

data class RobotState(
    val position: Position,
    val text: String = "",
    val finishReason: String? = null,
    val authorizedPosition: Position? = null,
    val nextStepToken: String? = null,
    val currentToken: String? = null,
    val isWon: Boolean = false,
    val source: Source? = null,
) {
    fun destroyed(source: Source): RobotState {
        return copy(finishReason = "Robot is destroyed", source = source)
    }

    fun won(source: TargetBlock): RobotState {
        return copy(isWon = true, source = source)
    }

    fun move(movement: Position.Movement, source: Source): RobotState {
        return copy(
            position = position.move(movement),
            nextStepToken = null,
            currentToken = nextStepToken,
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
        return if (authorizedPosition == null)
            copy(authorizedPosition = position)
        else
            throw AlreadyHaveTokenException()
    }

    fun authorize(token: String): RobotState {
        return copy(nextStepToken = token)
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

class AlreadyHaveTokenException : IllegalStateException("You've already got token. Use it")

class NotAuthenticatedException : IllegalStateException("Robot is not authenticated")

class NotAuthorizedException : IllegalStateException("Robot is not authorized")