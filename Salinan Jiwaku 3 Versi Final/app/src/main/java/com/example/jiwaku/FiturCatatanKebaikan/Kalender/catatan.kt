package com.example.jiwaku.FiturCatatanKebaikan.Kalender

data class Catatan(
    val tanggal: String? = null,
    val kegiatan: String? = null
) {
    // Konstruktor tanpa argumen diperlukan oleh Firebase
    @Suppress("unused")
    constructor() : this("", "")
}

