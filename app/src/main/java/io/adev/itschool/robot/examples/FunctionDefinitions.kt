package io.adev.itschool.robot.examples

import io.adev.itschool.robot.global.display

fun run() {
    // Объявляем переменную v
    // вызов foo
    // использование функции foo
    val v = foo(
        // переменная a и её значение - 1
        a = 1,
        // Передача b
        // Определение параметра b
        b = "abc"
    )

    // Передача значения переменной v в password
    display(password = v)
}

// определение функцию foo
fun foo(
    a: Int,
    // Приём b
    // Объявлявление параметра b
    b: String,
): String { // возвращаемый foo тип
    // определение возвращаемого foo значение
    return "cda"
}