package com.example.jiwaku.OnBoarding

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.example.jiwaku.LoginSignup.LoginActivity
import com.example.jiwaku.R

class OnBoardingActivity : AppCompatActivity() {

    // Membuat List Isian Gambar dan Deskripsi Untuk Intro Onboarding
    private val introSliderAdapter = IntroSliderAdapter(
        listOf(
            IntroSlide(
                "Kami Ada Untuk Membantumu",
                "Jangan risau untuk berterus terang Mencari bantuan bukanlah tanda bahwa kamu seseorang yang lemah, dapat memahami apa yang terjadi dalam diri kita adalah sesuatu yang keren!",
                R.drawable.intro1
            ), IntroSlide(
                "Jadwal Konseling Suka Suka Kamu",
                "Kamu dapat memilih jadwal konsultasi dengan tenaga profesional JiwaKu kapan pun dan dimana pun kamu berada. Jangan takut untuk mencari bantuan, ya!",
                R.drawable.intro2
            ), IntroSlide(
                "Layanan Edukasi Super Kece",
                "Nikmati layanan edukasi seputar kesehatan jiwa yang tersedia. Materi yang lengkap dan up to date dijamin gak akan bikin kamu bosan.",
                R.drawable.intro3
            )
        )
    )

    // Fungsi Utama
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        // Inisialisasi ViewPager
        val viewPager = findViewById<ViewPager2>(R.id.introSliderPager)

        // Set the adapter to the ViewPager2
        viewPager.adapter = introSliderAdapter

        // Fungsi menampilkan Indikator
        setupIndicator()

        // Fungsi menampilkan indikator saat berpindah slide
        setIndikatorSaarIni(0)
        viewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setIndikatorSaarIni(position)
            }
        })

        // Inisialisasi Button Next
        val buttonNext: Button = findViewById<Button>(R.id.buttonNext)

        // Inisialisai Textview Skip Intro
        val textSkip: TextView = findViewById<TextView>(R.id.textSkipIntro)

        // Atur Fungsi Tombol
        buttonNext.setOnClickListener {
            if (viewPager.currentItem + 1 < introSliderAdapter.itemCount) {
                viewPager.currentItem += 1
            } else {
                // Buat Perpindahan Halaman saat List sudah Penuh
                Intent(this, LoginActivity::class.java).also {
                    startActivity(it)
                }
            }
        }

        // Atur Fungsi Text OnClick Skip Intro
        textSkip.setOnClickListener {
            // Perpindahan halaman
            Intent(this, LoginActivity::class.java).also {
                startActivity(it)
            }
        }

    }

    // Fungsi Menampilkan Indikator dalam Berpindah Slide
    private fun setupIndicator(){
        val indikator = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for (i in indikator.indices){
            indikator[i] = ImageView(applicationContext)
            indikator[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.intro_indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            // Find the Linear Layout by Id
            val indicatorsContainer: LinearLayout = findViewById(R.id.indicatorsContainer)

            // Konfigurasi Linear Layout
            indicatorsContainer.addView(indikator[i])
        }
    }

    // Fungsi Merubah Indikator saat berpindah Halaman
    private fun setIndikatorSaarIni(index: Int){
        // Find the Linear Layout by Id
        val indicatorsContainer: LinearLayout = findViewById(R.id.indicatorsContainer)

        val childCount =  indicatorsContainer.childCount
        for (i in 0 until childCount){
            val imageView = indicatorsContainer[i] as ImageView
            if (i == index){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.intro_indicator_active
                    )
                )
            } else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.intro_indicator_inactive
                    )
                )
            }
        }
    }

}
