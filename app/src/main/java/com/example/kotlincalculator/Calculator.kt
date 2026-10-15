package com.example.kotlincalculator

// Handles the calculator's arithmetic operations.
class Calculator {

    // Calculates a result based on the selected operation.
    fun calculate(firstNumber: Double, secondNumber: Double, operation: String): Double {
        return when (operation) {
            "+" -> firstNumber + secondNumber
            "-" -> firstNumber - secondNumber
            "*" -> firstNumber * secondNumber
            "/" -> {
                if (secondNumber == 0.0) {
                    throw IllegalArgumentException("Cannot divide by zero.")
                }
                firstNumber / secondNumber
            }
            else -> throw IllegalArgumentException("Invalid operation.")
        }
    }
}