package io.adev.itschool.robot.galina

import io.adev.itschool.robot.global.*
import io.adev.itschool.robot.levels.homework2Variant1Arena

fun homework2() {
    //вызываем setArena передавая homework2Variant1Arena в качестве arena
    //используем функцию setArena определяя параметр sarena как homework2Variant1Arena
    setArena(arena = homework2Variant1Arena)
    eye()
    nose()
    jaw()
}

//движемся по eye
fun eye() {
    //вызываем moveRight передавая 3 в качестве stepsCount
    //используем функцию moveRight определяя параметр stepsCount как 3
    moveRight(stepsCount = 3)
    //вызываем display передавая p710 в качестве password
    //используем функцию display определяя параметр password как p710
    display(password = "p710")
    //вызываем moveDown передавая 2 в качестве stepsCount
    //используем функцию moveDown определяя параметр stepsCount как 2
    moveDown(stepsCount = 2)
}

//движемся по nose
fun nose() {
    //вызываем moveRight передавая 1 в качестве stepsCount
    //используем функцию moveRight определяя параметр stepsCount как 1
    moveRight()
    //вызываем moveDown передавая 2 в качестве stepsCount
    //используем функцию moveDown определяя параметр stepsCount как 2
    moveDown(stepsCount = 2)
    //вызываем display передавая p713 в качестве password
    //используем функцию display определяя параметр password как p713
    display(password = "p713")
    //вызываем moveLeft передавая 1 в качестве stepsCount
    //используем функцию moveLeft определяя параметр stepsCount как 1
    moveLeft()
}

//движемся по jaw
fun jaw() {
    //вызываем moveDown передавая 3 в качестве stepsCount
    //используем функцию moveDown определяя параметр stepsCount как 3
    moveDown(stepsCount = 3)
    //вызываем moveLeft передавая 2 в качестве stepsCount
    //используем функцию moveLeft определяя параметр stepsCount как 2
    moveLeft(stepsCount = 2)
}