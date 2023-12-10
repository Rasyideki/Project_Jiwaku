package com.example.jiwaku.HomePage.MenuNavigation.JadwalFragment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import com.example.jiwaku.HomePage.MenuActivity
import com.example.jiwaku.HomePage.MenuNavigation.JadwalFragment.JadwalFragment
import com.example.jiwaku.R
import com.example.jiwaku.Utils.FunPindah

class SuccesActivity : AppCompatActivity() {
    private val delayMillis: Long = 3000 // 3 detik

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_succes)

        // Handler untuk menunda eksekusi kode selama delayMillis
        Handler().postDelayed({
            // Set the default fragment
            FunPindah.pindahScene(this, MenuActivity::class.java)
        }, delayMillis)
    }

}