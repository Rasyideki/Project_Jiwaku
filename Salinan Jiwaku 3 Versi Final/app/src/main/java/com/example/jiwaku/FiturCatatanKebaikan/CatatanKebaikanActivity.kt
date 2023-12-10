package com.example.jiwaku.FiturCatatanKebaikan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import com.example.jiwaku.FiturCatatanKebaikan.Jurnal.JurnalActivity
import com.example.jiwaku.FiturCatatanKebaikan.Kalender.KalenderActivity
import com.example.jiwaku.R
import com.example.jiwaku.Utils.FunPindah

class CatatanKebaikanActivity : AppCompatActivity() {
    private lateinit var kembali: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catatan_kebaikan)

        kembali = findViewById(R.id.kembali)

        kembali.setOnClickListener{
            FunPindah.onBackPressed(this)
            finish()
        }
    }

    fun fKalender(view: View){
        FunPindah.pindahScene(this, KalenderActivity::class.java)
    }

    fun fJurnal(view: View){
        FunPindah.pindahScene(this, JurnalActivity::class.java)
    }
}