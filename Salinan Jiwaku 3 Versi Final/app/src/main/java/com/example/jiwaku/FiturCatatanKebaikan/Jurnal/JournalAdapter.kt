package com.example.jiwaku.FiturCatatanKebaikan.Jurnal

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jiwaku.R

class JournalAdapter : RecyclerView.Adapter<JournalAdapter.JournalViewHolder>() {

    private var journals: List<Journal> = mutableListOf()

    fun setJournals(journals: List<Journal>) {
        this.journals = journals
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JournalViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_journal, parent, false)
        return JournalViewHolder(view)
    }

    override fun onBindViewHolder(holder: JournalViewHolder, position: Int) {
        val journal = journals[position]
        holder.bind(journal)
    }

    override fun getItemCount(): Int {
        return journals.size
    }

    inner class JournalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        //        private val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)
        private val arrowImageView: ImageView = itemView.findViewById(R.id.arrowImageView)

        fun bind(journal: Journal) {
//            titleTextView.text = "Title: ${journal.title}"
            dateTextView.text = "Tanggal: ${journal.date}"
//            contentTextView.text = "Content: ${journal.content}"

            arrowImageView.setOnClickListener {
                // Handle click event, start DetailJurnalActivity with selected journalId
                val context = itemView.context
                val intent = Intent(context, DetailJurnalActivity::class.java)
                intent.putExtra("journalId", journal.id)
                context.startActivity(intent)
            }
        }
    }
}
