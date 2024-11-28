package com.example.healthfitness

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.os.Vibrator
import android.view.View
import android.widget.TimePicker
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class Alarm : AppCompatActivity() {
    private lateinit var alarmTimePicker: TimePicker
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)

        alarmTimePicker = findViewById(R.id.timePicker)
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }

    // OnToggleClicked() method is implemented the time functionality
    fun onToggleClicked(view: View) {
        var time: Long
        if ((view as ToggleButton).isChecked) {
            Toast.makeText(this, "ALARM ON", Toast.LENGTH_SHORT).show()
            val calendar = Calendar.getInstance()

            // calendar is called to get current time in hour and minute
            calendar[Calendar.HOUR_OF_DAY] = alarmTimePicker.currentHour
            calendar[Calendar.MINUTE] = alarmTimePicker.currentMinute

            // using intent i have class AlarmReceiver class which inherits
            // BroadcastReceiver
            val intent = Intent(this, AlarmReceiver::class.java)

            // we call broadcast using pendingIntent
            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)

            time = calendar.timeInMillis - (calendar.timeInMillis % 60000)
            if (System.currentTimeMillis() > time) {
                // setting time as AM and PM
                if (calendar[Calendar.AM_PM] == 0)
                    time += 1000 * 60 * 60 * 12
                else
                    time += 1000 * 60 * 60 * 24
            }
            // Alarm rings continuously until toggle button is turned off
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                time,
                10000,
                pendingIntent
            )
        } else {
            alarmManager.cancel(pendingIntent)
            Toast.makeText(this, "ALARM OFF", Toast.LENGTH_SHORT).show()
        }
    }
}