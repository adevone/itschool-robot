package io.adev.itschool.robot.common.arena.entity

data class RobotState(
    val position: Position,
    val text: String = "",
    val finishReason: RobotException? = null,
    val initKeyPosition: Position? = null,
    val nextStepKey: String? = null,
    val currentToken: String? = null,
    val code: Int? = null,
    val beforeMove: () -> Unit = {},
    val isWon: Boolean = false,
    val source: Source? = null,
) {
    val size = Size.Virtual(width = 1.vp, height = 1.vp)

    fun destroyed(): RobotState {
        return copy(finishReason = RobotException("Robot is destroyed"))
    }

    fun won(): RobotState {
        return copy(isWon = true)
    }

    fun moved(direction: Position.Direction): RobotState {
        return copy(
            position = position.move(direction),
            nextStepKey = null,
            currentToken = nextStepKey,
        )
    }

    fun displaying(text: String): RobotState {
        return copy(text = text)
    }

    fun withInitKey(): RobotState {
        return if (initKeyPosition == null)
            copy(initKeyPosition = position)
        else
            throw AlreadyHaveKeyException()
    }

    fun withKey(key: String): RobotState {
        return copy(nextStepKey = key)
    }

    fun checkToken() {
        val hash = initKeyPosition?.hash() ?: throw KeyIsNotProducedException()
        if (currentToken != hash) {
            throw KeyIsNotEnteredException()
        }
    }

    fun getKey(): String {
        return initKeyPosition?.hash() ?: throw KeyIsNotProducedException()
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
            else -> throw WrongPasswordException()
        }
        if (text != codeString) {
            throw WrongPasswordException()
        }
    }

    fun withBeforeMove(beforeMove: () -> Unit): RobotState {
        return copy(beforeMove = beforeMove)
    }

    fun finished(reason: RobotException?): RobotState {
        return copy(finishReason = reason)
    }

    fun withSource(source: Source): RobotState {
        return copy(source = source)
    }

    override fun toString(): String {
        return "$position finishReason=$finishReason from=${source?.sourceRepresentation()}"
    }

    interface Source {
        fun sourceRepresentation(): String
    }
}

class RobotException : RuntimeException {
    constructor(message: String?) : super(message)
    constructor(cause: Throwable?) : super(cause)

    override fun equals(other: Any?): Boolean {
        return other is RobotException && message == other.message
    }

    override fun hashCode(): Int {
        return message?.hashCode() ?: 0
    }
}

class AlreadyHaveKeyException : IllegalStateException("You've already got key. Use it")

class KeyIsNotProducedException : IllegalStateException("You need to produce the key")

class KeyIsNotEnteredException : IllegalStateException("You need to enter the key")

class WrongPasswordException : IllegalStateException("Robot is displaying a wrong password")