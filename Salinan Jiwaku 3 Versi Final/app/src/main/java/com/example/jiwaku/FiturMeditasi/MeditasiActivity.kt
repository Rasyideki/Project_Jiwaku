package com.example.jiwaku.FiturMeditasi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import com.example.jiwaku.R
import com.example.jiwaku.Utils.FunPindah

class MeditasiActivity : AppCompatActivity() {
    // membuat variabel menu meditasi
    private lateinit var btTahan: ImageView
    private lateinit var btTidur: ImageView
    private lateinit var btProduktivitas: ImageView
    private lateinit var btCemas: ImageView
    private lateinit var kembali: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meditasi)

        // Tahan Napas
        btTahan = findViewById(R.id.keTahanNapas)
        btTahan.setOnClickListener{
            FunPindah.pindahScene(this, TahanNapasActivity::class.java)
        }
        // Musik Tidur
        btTidur = findViewById(R.id.keMusikTidur)
        btTidur.setOnClickListener{
            FunPindah.pindahScene(this, MusicTidur::class.java)
        }
        // Musik Produktivitas
        btProduktivitas = findViewById(R.id.keMusikProduktivitas)
        btProduktivitas.setOnClickListener{
            FunPindah.pindahScene(this, MusicProduktivitas::class.java)
        }
        // Musik Cemas
        btCemas = findViewById(R.id.keMusikCemas)
        btCemas.setOnClickListener{
            FunPindah.pindahScene(this, MusicCemas::class.java)
        }

        // Ke Homepage
        kembali = findViewById(R.id.kembali)
        kembali.setOnClickListener{
            FunPindah.onBackPressed(this)
        }
    }

}