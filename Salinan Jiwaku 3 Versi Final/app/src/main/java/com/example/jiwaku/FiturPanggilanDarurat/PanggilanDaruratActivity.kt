package com.example.jiwaku.FiturPanggilanDarurat

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.jiwaku.R
import com.example.jiwaku.Utils.FunPindah

class PanggilanDaruratActivity : AppCompatActivity() {

    // Membuat variabel ImageButton
    private lateinit var btnBack: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_panggilan_darurat)

        val btnCall: TextView = findViewById(R.id.btnCall)
        btnCall.setOnClickListener {
            // Panggil method untuk melakukan panggilan
            makePhoneCall()
        }

        // Inisialisasi ImageButton
        btnBack = findViewById<ImageButton>(R.id.kembali)
        // Fungsi pindah halaman kembali
        btnBack.setOnClickListener {
            FunPindah.onBackPressed(this)
        }
    }

    private fun makePhoneCall() {
        // Nomor telepon untuk panggilan darurat
        val phoneNumber = "tel:999"

        // Buat intent untuk melakukan panggilan
        val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumber))

        // Jalankan intent
        startActivity(dialIntent)
    }
}
