package com.example.jiwaku.FiturCatatanKebaikan.Kalender

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jiwaku.R

class CatatanAdapter(private val catatanList: List<Catatan>) :
    RecyclerView.Adapter<CatatanAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textTanggal: TextView = itemView.findViewById(R.id.textTanggal)
        val textKegiatan: TextView = itemView.findViewById(R.id.textKegiatan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.kontainer_catatan_kalender, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val catatan = catatanList[position]
        holder.textTanggal.text = catatan.tanggal
        holder.textKegiatan.text = catatan.kegiatan
    }

    override fun getItemCount(): Int {
        return catatanList.size
    }
}
