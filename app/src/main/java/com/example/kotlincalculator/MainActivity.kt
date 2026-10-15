
package com.example.kotlincalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val calculator = Calculator()
    private val calculationHistory = CalculationHistory()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Applies system bar insets while preserving the layout padding.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            view.setPadding(
                20.dp() + systemBars.left,
                24.dp() + systemBars.top,
                20.dp() + systemBars.right,
                24.dp() + systemBars.bottom
            )

            insets
        }

        val firstNumberInput = findViewById<EditText>(R.id.firstNumberInput)
        val secondNumberInput = findViewById<EditText>(R.id.secondNumberInput)
        val resultText = findViewById<TextView>(R.id.resultText)
        val historyText = findViewById<TextView>(R.id.historyText)

        val addButton = findViewById<Button>(R.id.addButton)
        val subtractButton = findViewById<Button>(R.id.subtractButton)
        val multiplyButton = findViewById<Button>(R.id.multiplyButton)
        val divideButton = findViewById<Button>(R.id.divideButton)
        val clearButton = findViewById<Button>(R.id.clearButton)
        val clearHistoryButton = findViewById<Button>(R.id.clearHistoryButton)

        // Performs a calculation using the selected operation.
        fun performCalculation(operation: String) {
            val firstNumber =
                firstNumberInput.text.toString().toDoubleOrNull()
            val secondNumber =
                secondNumberInput.text.toString().toDoubleOrNull()

            if (firstNumber == null || secondNumber == null) {
                resultText.text = "Please enter both numbers."
                return
            }

            try {
                val result = calculator.calculate(
                    firstNumber,
                    secondNumber,
                    operation
                )

                val calculation =
                    "$firstNumber $operation $secondNumber = $result"

                resultText.text = "Result: $result"

                calculationHistory.addCalculation(calculation)
                historyText.text = calculationHistory.formatHistory()

            } catch (error: IllegalArgumentException) {
                resultText.text = error.message
            }
        }

        addButton.setOnClickListener {
            performCalculation("+")
        }

        subtractButton.setOnClickListener {
            performCalculation("-")
        }

        multiplyButton.setOnClickListener {
            performCalculation("*")
        }

        divideButton.setOnClickListener {
            performCalculation("/")
        }

        // Clears both number fields and the displayed result.
        clearButton.setOnClickListener {
            firstNumberInput.text.clear()
            secondNumberInput.text.clear()
            resultText.text = "Result: "
        }

        // Clears all saved calculations from the history.
        clearHistoryButton.setOnClickListener {
            calculationHistory.clearHistory()
            historyText.text = calculationHistory.formatHistory()
        }
    }

    // Converts density-independent pixels to screen pixels.
    private fun Int.dp(): Int {
        return (this * resources.displayMetrics.density).toInt()
    }
}
