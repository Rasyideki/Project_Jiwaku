package com.example.jiwaku.Utils

import android.app.Activity
import android.content.Context
import android.content.Intent

object FunPindah {
    // Fungsi Pindah Halaman
    fun pindahScene(context: Context, tujuan: Class<out Activity>) {
        val intent = Intent(context, tujuan)
        context.startActivity(intent)
    }

    // Fungsi Kembali
    fun onBackPressed(context: Context) {
        if (context is Activity) {
            (context as Activity).onBackPressed()
            // Kembali ke halaman sebelumnya
        }
    }
}
