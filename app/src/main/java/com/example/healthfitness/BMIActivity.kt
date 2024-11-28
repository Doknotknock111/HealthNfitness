package com.example.healthfitness

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class BMIActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmiactivity)
        val editTextHeight = findViewById<EditText>(R.id.editTextHeight)
        val editTextWeight = findViewById<EditText>(R.id.editTextWeight)
        val buttonCalculate = findViewById<Button>(R.id.buttonCalculate)
        val textViewResult = findViewById<TextView>(R.id.textViewResult)

        buttonCalculate.setOnClickListener {
            val height = editTextHeight.text.toString().toDoubleOrNull()
            val weight = editTextWeight.text.toString().toDoubleOrNull()

            if (height != null && weight != null) {
                val bmi = calculateBMI(height, weight)
                val bmiStatus = getBMIStatus(bmi)
                textViewResult.text = "Your BMI is: $bmi. $bmiStatus"
                Toast.makeText(this, "BMI Calculated", Toast.LENGTH_SHORT).show()
            } else {
                textViewResult.text = "Please enter valid height and weight"
                Toast.makeText(this, "BMI not calculated", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun calculateBMI(height: Double, weight: Double): Double {
        // Formula for BMI: weight (kg) / (height (m) * height (m))
        return weight / ((height / 100) * (height / 100))

    }

    private fun getBMIStatus(bmi: Double): String {
        return if (bmi < 18.5) {
            "Underweight"
        } else if (bmi >= 18.5 && bmi < 25) {
            "Normal weight"
        } else if (bmi >= 25 && bmi < 30) {
            "Overweight"
        } else {
            "Obese"
        }
    }
}
