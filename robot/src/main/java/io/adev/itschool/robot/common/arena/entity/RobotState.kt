package io.adev.itschool.robot.common.arena.entity

import io.adev.itschool.robot.common.arena.entity.arena.TargetBlock

data class RobotState(
    val position: Position,
    val text: String = "",
    val finishReason: String? = null,
    val authorizedPosition: Position? = null,
    val nextStepToken: String? = null,
    val currentToken: String? = null,
    val code: Int? = null,
    val isWon: Boolean = false,
    val source: Source? = null,
) {
    val size = Size.Virtual(width = 1.vp, height = 1.vp)

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

    fun withCode(code: Int): RobotState {
        return copy(code = code)
    }

    fun checkCode() {
        val codeString = when (code) {
            0 -> "zero"
            1 -> "one"
            2 -> "two"
            3 -> "three"
            4 -> "four"
            5 -> "five"
            6 -> "six"
            7 -> "seven"
            8 -> "eight"
            9 -> "nine"
            else -> throw WrongCodeException()
        }
        if (text != codeString) {
            throw WrongCodeException()
        }
    }

    fun finish(reason: String?): RobotState {
        return copy(finishReason = reason)
    }

    override fun toString(): String {
        return "$position finishReason=$finishReason from=${source?.sourceRepresentation()}"
    }

    interface Source {
        fun sourceRepresentation(): String
    }
}

class AlreadyHaveTokenException : IllegalStateException("You've already got token. Use it")

class NotAuthenticatedException : IllegalStateException("Robot is not authenticated")

class NotAuthorizedException : IllegalStateException("Robot is not authorized")

class WrongCodeException : IllegalStateException("Robot is displaying wrong code")