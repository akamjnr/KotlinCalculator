package com.example.kotlincalculator

// Stores and manages the calculator's calculation history.
class CalculationHistory {

    // A mutable collection that stores previous calculations.
    private val history = mutableListOf<String>()

    // Adds a calculation to the history.
    fun addCalculation(calculation: String) {
        history.add(calculation)
    }

    // Returns all saved calculations.
    fun getHistory(): List<String> {
        return history
    }

    // Formats history using a loop, numbering each calculation.
    fun formatHistory(): String {
        var formattedHistory = ""

        for ((index, calculation) in history.withIndex()) {
            formattedHistory += "${index + 1}. $calculation\n"
        }

        return formattedHistory.ifEmpty { "No calculations yet." }
    }

    // Removes all calculations from the history.
    fun clearHistory() {
        history.clear()
    }
}