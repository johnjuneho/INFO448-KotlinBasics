package edu.uw.basickotlin

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn(arg: Any): String = when (arg) {
    is String -> if (arg == "Hello") "world" else "Say what?"
    is Int -> when {
        arg == 0 -> "zero"
        arg == 1 -> "one"
        arg in 2..10 -> "low number"
        else -> "a number"
    }
    else -> "I don't understand"
}


// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(lhs: Int, rhs: Int): Int = lhs + rhs

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(lhs: Int, rhs: Int): Int = lhs - rhs 

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(lhs: Int, rhs: Int, op: (Int, Int) -> Int) = op(lhs, rhs)

// write a class "Person" with first name, last name and age
class Person(val firstName: String, val lastName: String, val age: Int) {
    val debugString: String
        get() = "[Person firstName:$firstName lastName:$lastName age:$age]"
}

// write a class "Money"
class Money(val amount: Int, val currency: String) {

    init {
        require(amount >= 0) { "Amount cannot be less than zero." }
        require(currency in listOf("USD", "EUR", "CAN", "GBP")) { "Invalid currency." }
    }

    fun convert(toCurrency: String): Money {
        if (currency == toCurrency) return Money(amount, currency)

        val conversionRate = getConversionRate(currency, toCurrency)
        val convertedAmount = (amount * conversionRate).toInt()
        return Money(convertedAmount, toCurrency)
    }

    operator fun plus(other: Money): Money {
        val convertedOther = other.convert(this.currency)
        return Money(this.amount + convertedOther.amount, this.currency)
    }

    fun getConversionRate(fromCurrency: String, toCurrency: String): Double {
        val conversionMap = mapOf(
            "USD" to mapOf("GBP" to 0.5, "EUR" to 1.5, "CAN" to 1.25),
            "GBP" to mapOf("USD" to 2.0, "EUR" to 3.0, "CAN" to 2.5),
            "EUR" to mapOf("USD" to 2.0 / 3.0, "GBP" to 1.0 / 3.0, "CAN" to 5.0 / 3.0),
            "CAN" to mapOf("USD" to 4.0 / 5.0, "GBP" to 2.0 / 5.0, "EUR" to 3.0 / 5.0)
        )
        return conversionMap[fromCurrency]?.get(toCurrency) ?: 1.0
    }
}
