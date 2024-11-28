package com.example.healthfitness

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class Watertracker : AppCompatActivity() {
    private lateinit var glassImage: ImageView
    private lateinit var counterText: TextView
    private lateinit var addButton: Button
    private lateinit var removeButton: Button
    private lateinit var userInputEditText: EditText

    private var counter = 0
    private var userInputLitres: Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watertracker)

        glassImage = findViewById(R.id.glassImage)
        counterText = findViewById(R.id.counterText)
        addButton = findViewById(R.id.addButton)
        removeButton = findViewById(R.id.removeButton)
        userInputEditText = findViewById(R.id.userInputEditText)

        addButton.setOnClickListener {
            incrementCounter()
            checkCompletion()
        }

        removeButton.setOnClickListener {
            decrementCounter()
            checkCompletion()
        }
    }

    private fun incrementCounter() {
        counter++
        updateCounterText()
    }

    private fun decrementCounter() {
        if (counter > 0) {
            counter--
            updateCounterText()
        }
    }

    private fun updateCounterText() {
        counterText.text = counter.toString()
    }

    private fun checkCompletion() {
        userInputLitres = userInputEditText.text.toString().toFloatOrNull() ?: 0f
        val glassesConsumed = (userInputLitres * 1000 / 250).toInt()
        if (counter == glassesConsumed) {
            Toast.makeText(this, "You have completed your water intake for the day", Toast.LENGTH_SHORT).show()
        }
    }
}
