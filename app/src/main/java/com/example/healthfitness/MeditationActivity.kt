package com.example.healthfitness

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class MeditationActivity : AppCompatActivity() {
    private lateinit var textViewTime: TextView
    private lateinit var buttonStart: Button
    private lateinit var buttonStop: Button
    private lateinit var buttonReset: Button
    private lateinit var editTextDuration: EditText
    private var timer: CountDownTimer? = null
    private var seconds = 0L
    private var isTimerRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meditation)

        textViewTime = findViewById(R.id.textViewTime)
        buttonStart = findViewById(R.id.buttonStart)
        buttonStop = findViewById(R.id.buttonStop)
        buttonReset = findViewById(R.id.buttonReset)
        editTextDuration = findViewById(R.id.editTextDuration)

        buttonStart.setOnClickListener {
            startTimer()
        }

        buttonStop.setOnClickListener {
            stopTimer()
        }

        buttonReset.setOnClickListener {
            resetTimer()
        }
    }

    private fun startTimer() {
        val input = editTextDuration.text.toString()
        val minutes = if (input.isNotEmpty()) input.toLong() else 0L
        val millisInFuture = minutes * 60 * 1000 // Convert minutes to milliseconds
        if (millisInFuture > 0) {
            timer = object : CountDownTimer(millisInFuture, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    seconds = millisUntilFinished / 1000
                    updateTimerText()
                }

                override fun onFinish() {
                    seconds = 0
                    updateTimerText()
                }
            }
            timer?.start()
            isTimerRunning = true
        }
    }

    private fun stopTimer() {
        timer?.cancel()
        isTimerRunning = false
    }

    private fun resetTimer() {
        stopTimer()
        editTextDuration.setText("")
        seconds = 0
        updateTimerText()
    }

    private fun updateTimerText() {
        val minutes = seconds / 60
        val secs = seconds % 60
        textViewTime.text = String.format(Locale.getDefault(), "%02d:%02d", minutes, secs)
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
    }
}