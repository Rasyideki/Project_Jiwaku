package com.example.jiwaku.OnBoarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jiwaku.R

// Kelas adapter untuk Recycler View pada tampilan slide pengenalan
class IntroSliderAdapter(private val introSlide: List<IntroSlide>):
    RecyclerView.Adapter<IntroSliderAdapter.IntroSlideViewHolder>() {

    // Fungsi untuk membuat tampilan baru (ViewHolder) ketika dibutuhkan
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroSlideViewHolder {
        return IntroSlideViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.activity_on_boarding_slide_container,
                parent,
                false
            )
        )
    }

    // Fungsi untuk mengatur tampilan item pada posisi tertentu
    override fun onBindViewHolder(holder: IntroSlideViewHolder, position: Int) {
        holder.bind(introSlide[position])
    }

    // Fungsi untuk mengembalikan jumlah item dalam daftar
    override fun getItemCount(): Int {
        return introSlide.size
    }

    // Kelas ViewHolder yang merepresentasikan tampilan setiap item dalam Recycler View
    inner class IntroSlideViewHolder(view: View) : RecyclerView.ViewHolder(view){

        // Inisialisasi Widget UI
        private val textTitle = view.findViewById<TextView>(R.id.textTitle)
        private val textDescription = view.findViewById<TextView>(R.id.textDescription)
        private val imageIcon = view.findViewById<ImageView>(R.id.imageSlideIcon)

        // Mengikat data dari objek IntroSlide ke tampilan UI
        fun bind(introSlide: IntroSlide) {
            textTitle.text = introSlide.title
            textDescription.text = introSlide.description
            imageIcon.setImageResource(introSlide.icon)
        }
    }
}
