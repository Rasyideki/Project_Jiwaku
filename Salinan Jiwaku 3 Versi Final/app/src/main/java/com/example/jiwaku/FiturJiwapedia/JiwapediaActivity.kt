package com.example.jiwaku.FiturJiwapedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import com.example.jiwaku.R
import com.example.jiwaku.Utils.FunPindah

class JiwapediaActivity : AppCompatActivity() {
    private lateinit var kembali: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jiwapedia)

        kembali = findViewById(R.id.kembali)
        kembali.setOnClickListener{
            FunPindah.onBackPressed(this)
        }
    }

    fun fKeHalVid(view: View){
        FunPindah.pindahScene(this, HalamanVideo::class.java)
    }

    fun fKeHalArt(view: View){
        FunPindah.pindahScene(this, HalamanArtikel::class.java)
    }
}