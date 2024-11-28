package com.example.healthfitness

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class YogaActivity : AppCompatActivity() {

    private lateinit var meditation: ImageView
    private lateinit var stretch: ImageView
    private lateinit var asana: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yoga)

        meditation = findViewById(R.id.meditation)
        stretch = findViewById(R.id.stretch)
        asana = findViewById(R.id.asana)

        meditation.setOnClickListener {
            val intent = Intent(this, MeditationActivity::class.java)
            startActivity(intent)
        }

        stretch.setOnClickListener {
            openUrl("https://www.youtube.com/watch?v=UbyhT_ytlQU")
        }

        asana.setOnClickListener {
            openUrl("https://www.youtube.com/watch?v=brjAjq4zEIE")
        }
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}
