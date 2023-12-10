package com.example.jiwaku.OnBoarding

// Membuat data class ntuk merepresentasikan informasi terkait dengan setiap slide
// pada tampilan onboarding di Aplikasi.
data class IntroSlide(
    // membuat Variabel Representasi
    val title: String,
    val description: String,
    val icon: Int
)
