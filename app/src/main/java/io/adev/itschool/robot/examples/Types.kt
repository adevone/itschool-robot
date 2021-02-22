package io.adev.itschool.robot.examples

fun types() {

    // Определение неизменяемой переменной mufasah, значение которой
    // возвращено в результате вызова конструктора WildLion
    //
    // Доп.информация:
    // Конктруктор - функция, возвращающая объект класса.
    // Создаётся автоматически (неявно, без добавления кода) при определении класса
    val mufasah = WildLion()

    // Определение неизменяемой переменной shram, значение которой
    // возвращено в результате вызова конструктора WildLion
    val shram = WildLion()

    // Вызов функции awakeAndFeed с передачей mufasah в качестве animal
    awakeAndFeed(animal = mufasah)

    // Вызов функции awakeAndFeed с передачей shram в качестве animal
    awakeAndFeed(animal = shram)

    // Определение неизменяемой переменной marti, значение которой
    // возвращено в результате вызова конструктора Zebra
    val marti = Zebra()

    // Вызов функции awakeAndFeed с передачей marti в качестве animal
    awakeAndFeed(animal = marti)

    // Определение неизменяемой переменной sahid, значение которой
    // возвращено в результате вызова конструктора Camel
    val sahid = Camel()

    // Вызов функции awakeAndFeed с передачей sahid в качестве animal
    awakeAndFeed(animal = sahid)

    // Определение неизменяемой переменной abdul, значение которой
    // возвращено в результате вызова конструктора Camel
    val abdul = Camel()

    // Вызов функции awakeAndFeed с передачей abdul в качестве animal
    awakeAndFeed(animal = abdul)

    // Вызов функции moveToCage у sahid
    //
    // Доп.информация: так же можно рассмотреть это как
    // вызов функции moveToCage с передачей sahid в качестве this
    // если бы в Kotlin синтаксис вызова функций у объектов был больше похож
    // на синтаксис вызова обычных функций, он мог бы быть таким:
    //
    // Camel.moveToCage(this = sahid)
    sahid.moveToCage()

    // Вызов функции moveToCage у abdul
    abdul.moveToCage()

    // Вызов функции moveToCage у shram
    shram.moveToCage()

    // Вызов функции moveToCage у mufasah
    mufasah.moveToCage()
}

// Определение функции awakeAndFeed, принимающей animal, являющегося Animal
fun awakeAndFeed(animal: Animal) {

    // Установка true в качесте значения isAwaken у animal
    animal.isAwaken = true

    // Если не (isPredator у animal),
    if (!animal.isPredator) { // то
        // Вызов feed у animal
        animal.feed()
        // Вызов feed у animal
        animal.feed()
    } else { // иначе
        // Вызов feed у animal
        animal.feed()
    }
}

// Определение абстрактного класса Animal
// Доп.информация: абстрактный класс - частный случай абстрактного типа
abstract class Animal {

    // Определение изменяемой переменной isAwaken, которая изначально содержит true
    var isAwaken = true

    // Определение переопределяемой (open) функции feed
    open fun feed() {
        println("hrum-hrum")
    }

    // Объявление абстрактной неизменяемой переменной isPredator,
    // являющейся Boolean (true или false)
    abstract val isPredator: Boolean
}

// Определение интерфейса MovableToCage
// доп.информация: интерфейс - частный случай абстрактного типа
interface MovableToCage {

    // Объявление абстрактной функции moveToCage
    //
    // Доп.информация: все переменные и функции,
    // объявленные в интерфейсе - абстрактные по-умолчанию
    fun moveToCage()
}

// Определение класса Food, наследующегося от интерфейса MovableToCage
// или
// Определение класса Food, реализующего MovableToCage
//
// Доп.информация:
// Наследование - это когда наследующийся тип (Food)
// получает все переменные и функции наследуемого (MovableToCage).
// Все объекты наследующегося типа будут являться также и объектами наследуемого.
// Все объекты типа Food будут являться также и объектами типа MovableToCage.
//
// наследуемый тип = супертип (= надтип, родительский тип)
// наследующися тип = подтип (= субтип, дочерний тип)
// реализующего = наследующегося от интерфейса
// класс - способ определить конкретный тип
class Food : MovableToCage {

    // Реализация функции moveToCage для типа Food
    // доп.информация: реализация - это определение абстрактной функции или переменной
    override fun moveToCage() {
        // вызов функции println с передачей "Брямс" в качестве 1 параметра
        println("Брямс")
    }
}

// Определение класса WildLion, наследующегося от класса Animal и вызывающего его конструктор,
// а также наследующегося от интерфейса MovableToCage
// или
// Определение класса WildLion, расширяющего Animal и вызывающего его конструктор,
// а также реализующего MovableToCage
//
// Доп.информация:
// расширяющего = наследующегося от класса
class WildLion : Animal(), MovableToCage {

    // Реализация переменной isPredator, значение которой изначально true
    override val isPredator = true

    // Переопределение функции feed
    //
    // Доп.информация:
    // т.к. функция feed переопределена в WildLion,
    // то при вызове feed у объекта, созданного с помощью конструктора WildLion,
    // будет вызываться функция feed WildLion, а не функция feed Animal
    //
    // переопределение = определение open функции. Повторное определение т.к.
    // если в суперклассе есть open функция, то она обязательно должна быть уже определена
    //
    // Каждая функция, определённая в типе, принимает неявный (не описанный в коде) параметр this
    // Если бы в Kotlin функции типов принимали бы this явно, функция feed могла бы объявляться так:
    //
    // Переопределение функции feed
    // override fun feed(this: WildLion)
    override fun feed() {

        // Вызов feed супертипа (Animal)
        super.feed()

        // Если isAwaken - true у WildLion, у которого вызвана функция feed,
        // Доп.информация: this, чаще всего, может быть опущено если после него идёт "."
        if (this.isAwaken) { // то
            // Вызов функции println с передачей "rrrrrrr" в качестве первого параметра
            println("rrrrrrr")
        } else { // иначе
            // Кинуть возвращённое конструктором IllegalStateException значение
            // с передачей "can not feed dreaming leon" в качесве первого параметра
            // Коротко: Кинуть IllegalStateException с "can not feed a dreaming leon"
            throw IllegalStateException("can not feed a dreaming leon")
        }
    }

    // Реализация функции moveToCage
    //
    // Каждая функция, определённая в типе, принимает неявный (не описанный в коде) параметр this
    // Если бы в Kotlin функции типов принимали бы this явно, функция moveToCage
    // могла бы объявляться так:
    //
    // Реализация функции moveToCage
    // override fun moveToCage(this: WildLion)
    override fun moveToCage() {

        // Вызов функции awakeAndFeed с передачей this в качестве animal
        awakeAndFeed(animal = this)

        // Вызов функции println с передачей "klathz-klathz" в качестве первого параметра
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