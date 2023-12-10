package com.example.jiwaku.FiturMeditasi

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.jiwaku.R
import com.example.jiwaku.Utils.FunPindah.onBackPressed

class MusicTidur : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    private var isPlaying = false
    private lateinit var seekBar: SeekBar
    private lateinit var runnable: Runnable
    private lateinit var handler: Handler
    private lateinit var textViewTimeElapsed: TextView
    private lateinit var textViewTotalTime: TextView
    private lateinit var textViewTotal: TextView

    // Membuat variabel ImageButton
    private lateinit var btnBack: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meditasi_music_tidur)

        // Inisialisasi UI
        val btnPlayPause = findViewById<ImageButton>(R.id.btnPlayPause)
        seekBar = findViewById(R.id.seekBar)
        textViewTimeElapsed = findViewById(R.id.textViewTimeElapsed)
        textViewTotalTime = findViewById(R.id.textViewTotalTime)
        textViewTotal = findViewById(R.id.waktuMusik)

        // Inisialisasi ImageButton
        btnBack = findViewById<ImageButton>(R.id.kembali)
        // Fungsi pindah halaman kembali
        btnBack.setOnClickListener {
            stopMusic()
            onBackPressed(this)
        }

        // Konfigurasi Musik
        mediaPlayer = MediaPlayer.create(this, R.raw.musik_tidur)

        // Konfigurasi SeekBar untuk Progress
        seekBar.progress = 0
        seekBar.max = mediaPlayer.duration

        // Set total waktu musik pada TextViewTotalTime
        val totalMinutes = mediaPlayer.duration / 1000 / 60
        val totalSeconds = mediaPlayer.duration / 1000 % 60
        val totalTime = String.format("%d:%02d", totalMinutes, totalSeconds)
        textViewTotalTime.text = totalTime
        textViewTotal.text = totalTime

        // Tombol Klik Hidup dan Matikan Musik
        btnPlayPause.setOnClickListener {
            if (isPlaying) {
                mediaPlayer.pause()
                btnPlayPause.setImageResource(R.drawable.baseline_play)
            } else {
                mediaPlayer.start()
                btnPlayPause.setImageResource(R.drawable.baseline_pause)
            }
            isPlaying = !isPlaying
        }

        // SeekBar
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Do nothing
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Do nothing
            }
        })

        // Atur Posisi dan Pergerakan SeekBar
        handler = Handler()
        runnable = object : Runnable {
            override fun run() {
                val currentPosition = mediaPlayer.currentPosition
                seekBar.progress = currentPosition
                updateTextViewTimeElapsed(currentPosition)
                handler.postDelayed(this, 1000)
            }
        }
        handler.postDelayed(runnable, 1000)

        // Atur seekbar apabila musik sudah selesai diputar
        mediaPlayer.setOnCompletionListener {
            btnPlayPause.setImageResource(R.drawable.baseline_play)
            seekBar.progress = 0
            updateTextViewTimeElapsed(0)
        }
    }

    // Fungsi Update Waktu Musik
    private fun updateTextViewTimeElapsed(currentPosition: Int) {
        val elapsedMinutes = currentPosition / 1000 / 60
        val elapsedSeconds = currentPosition / 1000 % 60
        val elapsedTime = String.format("%d:%02d", elapsedMinutes, elapsedSeconds)
        textViewTimeElapsed.text = elapsedTime
    }

    // Fungsi untuk menghentikan pemutaran musik
    private fun stopMusic() {
        if (isPlaying) {
            mediaPlayer.stop()
        }
    }
}
