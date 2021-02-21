import io.adev.itschool.robot.denis.homeworks.displayCurrentCode
import io.adev.itschool.robot.global.*
import io.adev.itschool.robot.levels.homework4DenisArena


fun homeworkDenis4Turtule() {

    setArena(arena = homework4DenisArena)

    val token = authenticate()

    backLeg()
    body(robotToken = token)
    neck()
    head()



//    // задняя нога
//    moveUp()
//    moveUp()
//    displayCurrentCode()
//    moveUp()
//    moveLeft(5)
//    // тело
//    moveUp(2)
//    moveLeft(13)
//    authorize(token)
//    moveLeft(2)
//    moveDown(2)
//    moveLeft(1)
//    authorize(token)
//    moveLeft()
//    moveDown()
//    // шея
//    moveLeft(3)
//    displayCurrentCode()
//    // голова
//    moveLeft()
//    moveUp(5)
//    moveRight()

}

fun backLeg() {
    moveUp()
    moveUp()
    displayCurrentCode()
    moveUp()
    moveLeft(5)
}

fun body(robotToken : String) {
    moveUp(2)
    moveLeft(13)
    authorize(robotToken)
    moveLeft(2)
    moveDown(2)
    moveLeft()
    authorize(robotToken)
    moveLeft()
    moveDown()
}

fun neck() {
    moveLeft(3)
    displayCurrentCode()
}

fun head() {
    moveLeft()
    moveUp(5)
    moveRight()
}

