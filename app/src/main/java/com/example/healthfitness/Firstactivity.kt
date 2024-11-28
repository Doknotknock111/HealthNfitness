package com.example.healthfitness

import TrackerFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView

class Firstactivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager
    private lateinit var bottomNav: BottomNavigationView

    private val homeFragment = HomeFragment()
    private val profileFragment = UserFragment()
    private val trackersFragment = TrackerFragment()
    private val workoutFragment = WorkoutFragment()

    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firstactivity)

        viewPager = findViewById(R.id.viewPager)
        bottomNav = findViewById(R.id.bottomNav)

        // Setup ViewPager with adapter
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(homeFragment)
        adapter.addFragment(trackersFragment)
        adapter.addFragment(workoutFragment)
        adapter.addFragment(profileFragment)
        viewPager.adapter = adapter

        // Set listener for bottom navigation
        bottomNav.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_home -> viewPager.currentItem = 0
                R.id.menu_tracker -> viewPager.currentItem = 1
                R.id.workout_tracker -> viewPager.currentItem = 2
                R.id.menu_user -> viewPager.currentItem = 3

            }
            true
        }

        // Set listener for ViewPager to update bottom navigation
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}

            override fun onPageSelected(position: Int) {
                bottomNav.menu.getItem(position).isChecked = true
            }
        })
    }
}
