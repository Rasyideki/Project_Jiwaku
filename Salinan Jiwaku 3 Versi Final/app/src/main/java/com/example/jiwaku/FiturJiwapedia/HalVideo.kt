package com.example.jiwaku.FiturJiwapedia

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.jiwaku.R
import com.example.jiwaku.Utils.FunPindah
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class HalVideo : AppCompatActivity() {

    private lateinit var kembali: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jiwapedia_video1)

        val youtubePlayerView: YouTubePlayerView = findViewById(R.id.exoPlayer)

        lifecycle.addObserver(youtubePlayerView)

        youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo("dNFXy4ELCLQ", 0f)
            }
        })
        val KUNCI="Kunci 1234"

        kembali = findViewById(R.id.kembali)
        kembali.setOnClickListener{
            FunPindah.onBackPressed(this)
        }
    }
}