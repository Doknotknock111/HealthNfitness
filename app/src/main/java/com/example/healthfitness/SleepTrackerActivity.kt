package com.example.healthfitness

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class SleepTrackerActivity : AppCompatActivity() {

    private lateinit var textViewStartTime: TextView
    private lateinit var buttonSetStartTime: Button
    private lateinit var textViewEndTime: TextView
    private lateinit var buttonSetEndTime: Button
    private lateinit var buttonSave: Button
    private lateinit var buttonReset: Button

    private var startTime: Date? = null
    private var endTime: Date? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sleep_tracker)

        // Initialize views
        textViewStartTime = findViewById(R.id.textViewStartTime)
        buttonSetStartTime = findViewById(R.id.buttonSetStartTime)
        textViewEndTime = findViewById(R.id.textViewEndTime)
        buttonSetEndTime = findViewById(R.id.buttonSetEndTime)
        buttonSave = findViewById(R.id.buttonSave)
        buttonReset = findViewById(R.id.buttonReset)

        // Set click listeners
        buttonSetStartTime.setOnClickListener {
            startTime = Calendar.getInstance().time
            updateStartTimeTextView()
        }

        buttonSetEndTime.setOnClickListener {
            endTime = Calendar.getInstance().time
            updateEndTimeTextView()
        }

        buttonSave.setOnClickListener {
            // Save sleep data
            saveSleepData()
        }

        buttonReset.setOnClickListener {
            resetSleepData()
        }
    }

    private fun updateStartTimeTextView() {
        startTime?.let {
            val formattedTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(it)
            textViewStartTime.text = "Start Time: $formattedTime"
        }
    }

    private fun updateEndTimeTextView() {
        endTime?.let {
            val formattedTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(it)
            textViewEndTime.text = "End Time: $formattedTime"
        }
    }

    private fun saveSleepData() {
        // Implement saving sleep data to a database or file
        // For now, just print the start and end time
        startTime?.let { start ->
            endTime?.let { end ->
                println("Sleep duration: ${calculateSleepDuration(start, end)}")
                Toast.makeText(this, "Sleep duration: ${calculateSleepDuration(start, end)}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun calculateSleepDuration(startTime: Date, endTime: Date): String {
        val durationInMillis = endTime.time - startTime.time
        val hours = durationInMillis / (1000 * 60 * 60)
        val minutes = (durationInMillis / (1000 * 60)) % 60
        return String.format(Locale.getDefault(), "%02d:%02d", hours, minutes)
    }

    private fun resetSleepData() {
        startTime = null
        endTime = null
        textViewStartTime.text = "Start Time:"
        textViewEndTime.text = "End Time:"
    }
}
