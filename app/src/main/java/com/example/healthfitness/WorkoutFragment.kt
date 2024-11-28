package com.example.healthfitness

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

class WorkoutFragment : Fragment() {

    // Declare variables for all ImageViews
    private lateinit var imageView2: ImageView
    private lateinit var imageView3: ImageView
    private lateinit var imageView4: ImageView
    private lateinit var imageView5: ImageView
    private lateinit var imageView6: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize ImageViews
        imageView2 = view.findViewById(R.id.imageView2)
        imageView3 = view.findViewById(R.id.imageView3)
        imageView4 = view.findViewById(R.id.imageView4)
        imageView5 = view.findViewById(R.id.imageView5)
        imageView6 = view.findViewById(R.id.imageView6)

        // Set click listeners for each ImageView
        imageView2.setOnClickListener { openUrl("https://www.youtube.com/watch?v=FbWfA_s0XL8&list=PLp4G6oBUcv8zeDXbOiCu4AKcs9nlRSjYY&index=6") }
        imageView3.setOnClickListener { openUrl("https://www.youtube.com/watch?v=7USunyFGITk&list=PLp4G6oBUcv8zeDXbOiCu4AKcs9nlRSjYY&index=2") }
        imageView4.setOnClickListener { openUrl("https://www.youtube.com/watch?v=didU4ZwAkPI&list=PLp4G6oBUcv8zeDXbOiCu4AKcs9nlRSjYY&index=3") }
        imageView5.setOnClickListener { openUrl("https://www.youtube.com/watch?v=tQ2LSSP_0GQ&list=PLp4G6oBUcv8zeDXbOiCu4AKcs9nlRSjYY&index=4") }

        // Set click listener to start Calisthenics Activity
        imageView6.setOnClickListener {
            val i = Intent(requireContext(), Calisthenics::class.java)
            startActivity(i)
        }
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}
