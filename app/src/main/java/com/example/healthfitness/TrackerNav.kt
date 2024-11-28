package com.example.healthfitness

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast

class TrackerNav : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tracker_nav)

        val bmi: ImageView = findViewById(R.id.BMI)
        bmi.setOnClickListener {
            startActivity(Intent(this@TrackerNav, BMIActivity::class.java))
            Toast.makeText(this, "BMI selected", Toast.LENGTH_SHORT).show()
        }

        val pedo: ImageView = findViewById(R.id.Pedometer)
        pedo.setOnClickListener {
            startActivity(Intent(this@TrackerNav, PedoActivity::class.java))
            Toast.makeText(this, "Pedometer selected", Toast.LENGTH_SHORT).show()
        }

        val run: ImageView = findViewById(R.id.Running)
        run.setOnClickListener {
            startActivity(Intent(this@TrackerNav, RunActivity::class.java))
            Toast.makeText(this, "Running tracker selected", Toast.LENGTH_SHORT).show()
        }

        val yoga1: ImageView = findViewById(R.id.Yoga)
        yoga1.setOnClickListener {
            startActivity(Intent(this@TrackerNav, YogaActivity::class.java))
            Toast.makeText(this, "Yoga selected", Toast.LENGTH_SHORT).show()
        }
    }
}
