package io.adev.itschool.robot.examples

import io.adev.itschool.robot.global.display
import io.adev.itschool.robot.global.moveLeft

// определяем функцию commentsExample
fun commentsExample() {
    // вызываем display, передавая ей "p62" в качестве password
    display(password = "p62")
    // вызываем left, передавая ей 2 в качестве первого параметра
    moveLeft(2)
}