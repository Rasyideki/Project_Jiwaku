package com.example.jiwaku.FiturJiwapedia


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.jiwaku.R
import com.example.jiwaku.Utils.FunPindah

class HalamanVideo : AppCompatActivity() {

    private lateinit var kembali: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jiwapedia_menu_video)

        val KUNCI="Kunci 1234"

        kembali = findViewById(R.id.kembali)
        kembali.setOnClickListener{
            FunPindah.onBackPressed(this)
        }
    }

    fun fKeVideo1(view: View){
        FunPindah.pindahScene(this, HalVideo::class.java)
    }

    fun fKeVideo2(view: View){
        FunPindah.pindahScene(this, HalVideo2::class.java)
    }

    fun fKeVideo3(view: View){
        FunPindah.pindahScene(this, HalVideo3::class.java)
    }

    fun fKeVideo4(view: View){
        FunPindah.pindahScene(this, HalVideo4::class.java)
    }
}