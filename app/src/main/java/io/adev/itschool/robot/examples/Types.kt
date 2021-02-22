package io.adev.itschool.robot.examples

fun types() {

    val mufasah = WildLion()
    val shram = WildLion()
    awakeAndFeed(animal = mufasah)
    awakeAndFeed(animal = shram)

    val marti = Zebra()
    awakeAndFeed(animal = marti)

    val sahid = Camel()
    awakeAndFeed(animal = sahid)

    val abdul = Camel()
    awakeAndFeed(animal = abdul)

    sahid.moveToCage()
    abdul.moveToCage()
    shram.moveToCage()
    mufasah.moveToCage()
}

fun awakeAndFeed(animal: Animal) {
    animal.isAwaken = true

    if (!animal.isPredator) {
        animal.feed()
        animal.feed()
    } else {
        animal.feed()
    }
}

abstract class Animal {
    var isAwaken = true
    open fun feed() {
        println("hrum-hrum")
    }
    abstract val isPredator: Boolean
}

interface MovableToCage {
    fun moveToCage()
}

class Food : MovableToCage {

    override fun moveToCage() {
        println("Брямс")
    }
}

class WildLion : Animal(), MovableToCage {

    // Определение переменной isPredator, значение которой изначально true
    override val isPredator = true

    override fun feed() {
        super.feed()
        if (isAwaken) {
            println("rrrrrrr")
        } else {
            throw IllegalStateException("can not feed dreaming leon")
        }
    }

    override fun moveToCage() {
        awakeAndFeed(animal = this)
        println("klathz-klathz")
    }
}


class Zebra : Animal() {

    override val isPredator = false

    override fun feed() {
        if (isAwaken)
            println("Спасибо, было очень вкусно. Вы очень добрый ко мне")
        else {
            throw IllegalAccessException("Ты разбудил бедное животное, ты плохой чевлоек")
        }
    }
}

class Camel : Animal(), MovableToCage {

    override val isPredator = false

    override fun feed() {
        if (isAwaken) {
            println("aaaaaauuuuuuwwww")
        } else {
            throw IllegalStateException("you can not feed dreaming animal")
        }
    }

    override fun moveToCage() {
        println("tsok-tsok")
    }
}