package com.example.jiwaku.FiturMeditasi

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.jiwaku.R
import com.example.jiwaku.Utils.FunPindah.onBackPressed

class TahanNapasActivity : AppCompatActivity() {

    // Inisialisasi Variabel
    private lateinit var progressBar: ProgressBar
    private lateinit var btnStart: Button
    private lateinit var btnReset: Button
    private lateinit var tvPhase: TextView
    private lateinit var countDownTimer: CountDownTimer

    // Inisialisasi Waktu Pernapasan
    private var totalTime = 20000L // 20 detik
    private var phase1Time = 5000L // 5 detik
    private var phase2Time = 10000L // 10 detik
    private var phase3Time = 5000L // 5 detik

    // membuat variabel for ImageButton untuk kembali
    private lateinit var btnBack: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meditasi_tahan_napas)

        // Konfigurasi Variabel
        progressBar = findViewById(R.id.progressBar)
        btnStart = findViewById(R.id.btnStart)
        btnReset = findViewById(R.id.btnReset)
        tvPhase = findViewById(R.id.tvPhase)

        // Tombol Mulai Tahan Napas
        btnStart.setOnClickListener {
            startBreathGame()
        }
        // Tombol Reser Tahan Napas
        btnReset.setOnClickListener {
            resetBreathGame()
        }

        // Inisialisasi ImageButton
        btnBack = findViewById<ImageButton>(R.id.kembali)
        // Fungsi pindah halaman kembali
        btnBack = findViewById(R.id.kembali)
        btnBack.setOnClickListener{
            onBackPressed(this)
        }
    }

    // Fungsi Mulai permainan
    private fun startBreathGame() {
        btnStart.isEnabled = false
        btnReset.isEnabled = true

        countDownTimer = object : CountDownTimer(totalTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val progress = ((totalTime - millisUntilFinished).toFloat() / totalTime.toFloat() * 100).toInt()
                progressBar.progress = progress

                when {
                    millisUntilFinished >= totalTime - phase1Time -> updatePhaseText("Tarik Napas")
                    millisUntilFinished >= totalTime - (phase2Time + phase1Time) -> updatePhaseText("Tahan Napas")
                    else -> updatePhaseText("Buang Napas")
                }
            }

            override fun onFinish() {
                updatePhaseText("Permainan Selesai")
                btnStart.isEnabled = true
                btnReset.isEnabled = false
                progressBar.progress = 0
            }
        }

        countDownTimer.start()
    }

    // Fungsi Reset Permainan
    private fun resetBreathGame() {
        countDownTimer.cancel()
        updatePhaseText("Permainan diulang")
        btnStart.isEnabled = true
        btnReset.isEnabled = false
        progressBar.progress = 0
    }

    // Fungsi Update Teks sesuai Fase
    private fun updatePhaseText(phase: String) {
        tvPhase.text = phase
    }
}
