package com.example.jiwaku.FiturJiwapedia

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.jiwaku.R
import com.example.jiwaku.Utils.FunPindah

class HalArtikel: AppCompatActivity() {

    private lateinit var kembali: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jiwapedia_artikel1)

        val KUNCI="Kunci 1234"

        kembali = findViewById(R.id.kembali)
        kembali.setOnClickListener{
            FunPindah.onBackPressed(this)
        }

    }
}