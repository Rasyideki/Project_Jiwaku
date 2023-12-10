package com.example.jiwaku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.content.Intent
import com.example.jiwaku.OnBoarding.OnBoardingActivity

class MainActivity : AppCompatActivity() {

    private val delayMillis: Long = 3000 // 3 detik

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Handler untuk menunda eksekusi kode selama delayMillis
        Handler().postDelayed({
            // Intent untuk berpindah ke halaman baru
            val intent = Intent(this@MainActivity, OnBoardingActivity::class.java)
            startActivity(intent)
            finish() // Optional: Menutup aktivitas ini agar tidak bisa kembali ke halaman ini
        }, delayMillis)
    }
}
