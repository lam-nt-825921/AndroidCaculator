package viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CalculateViewModel : ViewModel() {
    val resultText = mutableStateOf("0")
    val expressionText = mutableStateOf("")
    val historyText = mutableStateOf("")
    val isCalculated = mutableStateOf(true)
    val isSetOperation = mutableStateOf(false)
    val lastNumHasPoint = mutableStateOf(false)
    fun calculate() {

        val raw = expressionText.value
        if (raw.isEmpty()) {
            resultText.value = "0"
            return
        }

        val pattern = Regex("""(\d+\.?\d*|[+\-×÷])""")
        val tokens = pattern.findAll(raw).map { it.value }.toMutableList()
        val operators = listOf("+", "-", "×", "÷")
        if (tokens.last() in operators) {
            tokens.removeAt(tokens.size - 1)
        }

        if (tokens.isEmpty()) return

        try {
            val pass1 = mutableListOf<String>()
            var i = 0
            while (i < tokens.size) {
                val current = tokens[i]
                if (current == "×" || current == "÷") {
                    val numLeft = pass1.removeAt(pass1.size - 1).toDouble()
                    val numRight = tokens[i + 1].toDouble()
                    val res = if (current == "×") numLeft * numRight else numLeft / numRight
                    pass1.add(res.toString())
                    i += 2
                } else {
                    pass1.add(current)
                    i++
                }
            }

            var total = pass1[0].toDouble()
            var j = 1
            while (j < pass1.size) {
                val op = pass1[j]
                val nextNum = pass1[j + 1].toDouble()
                if (op == "+") total += nextNum else total -= nextNum
                j += 2
            }

            resultText.value = if (total % 1 == 0.0) {
                total.toLong().toString()
            } else {
                total.toString()
            }
        } catch (e: Exception) {
            return
        }
    }

    fun onDigitClick(digit: String) {

        if (digit == ".") {
            if (lastNumHasPoint.value) return
            lastNumHasPoint.value = true
        }

        if (isCalculated.value) {
            historyText.value += "\n${expressionText.value} \n= ${resultText.value}\n"
            if (digit == ".") expressionText.value = "0."
            else expressionText.value = digit
            resultText.value = "0"
            isCalculated.value = false
            calculate()
            return
        }
        if (isSetOperation.value) {
            if (digit == ".") expressionText.value += " 0."
            else expressionText.value += " $digit"
            isSetOperation.value = false
            calculate()
            return
        }
        if (digit == "0" && expressionText.value == "0") return
        expressionText.value += digit
        calculate()
    }

    fun onOperatorClick(operator: String) {
        if (isSetOperation.value) {
            expressionText.value = expressionText.value.dropLast(1) + operator
            return
        }
        isSetOperation.value = true
        if (isCalculated.value) {
            historyText.value += "\n${expressionText.value} \n= ${resultText.value}"
            expressionText.value = resultText.value + " " + operator

            lastNumHasPoint.value = resultText.value.contains('.')
            isCalculated.value = false
            return
        }
        expressionText.value = expressionText.value + " " + operator
    }

    fun onClearClick() {
        resultText.value = "0"
        expressionText.value = ""
        historyText.value = ""
    }

    fun onEqualsClick() {
        if (isCalculated.value) {
            return
        }
        calculate()

        isCalculated.value = true
    }
}