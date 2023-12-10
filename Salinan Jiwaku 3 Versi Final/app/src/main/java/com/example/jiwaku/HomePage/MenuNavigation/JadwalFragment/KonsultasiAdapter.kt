package com.example.jiwaku.HomePage.MenuNavigation.JadwalFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jiwaku.databinding.ContainerTampilKonsultasiBinding

class KonsultasiAdapter(private val dataList: List<Konsultasi>) :
    RecyclerView.Adapter<KonsultasiAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ContainerTampilKonsultasiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Konsultasi) {
            binding.textViewNama.text = "${data.namaDokter}"
            binding.textViewSesiTanggal.text = "${data.sesiTanggal}"
            binding.textViewSesiWaktu.text = "${data.sesiWaktu}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ContainerTampilKonsultasiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}