package com.example.jiwaku.HomePage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.jiwaku.HomePage.MenuNavigation.HomeFragment
import com.example.jiwaku.HomePage.MenuNavigation.JadwalFragment.JadwalFragment
import com.example.jiwaku.HomePage.MenuNavigation.SettingFragment
import com.example.jiwaku.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MenuActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val jadwalFragment = JadwalFragment()
    private val settingFragment = SettingFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNavigation)
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> replaceFragment(homeFragment)
                R.id.menu_jadwal -> replaceFragment(jadwalFragment)
                R.id.menu_setting -> replaceFragment(settingFragment)
            }
            true
        }

        // Set the default fragment
        replaceFragment(homeFragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}
