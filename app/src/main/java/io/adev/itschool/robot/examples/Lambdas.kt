package io.adev.itschool.robot.examples

// Все варианты делают одно и то же. Отличается только синтаксис
fun lambdas() {

    // Вариант 1
    val a = fun(): Int {
        return bar()
    }
    printInt(a)

    // Вариант 2
    val b: () -> Int = {
        bar()
    }
    printInt(b)

    // Вариант 3
    printInt(::f)

    // Вариант 4
    printInt(fun(): Int {
        return bar()
    })

    // Вариант 5
    printInt(what = {
        bar()
    })

    // Вариант 6
    printInt({
        bar()
    })

    // Вариант 7
    printInt() {
        bar()
    }

    // Вариант 8
    printInt {
        bar()
    }
}

val debugMode = true

fun printInt(what: () -> Int) {
    if (debugMode) {
        val toPrint = what()
        print(toPrint)
    }
}

fun bar(): Int {
    return 456
}

fun f(): Int {
    return bar()
}