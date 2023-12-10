package com.example.jiwaku.FiturCatatanKebaikan.Jurnal

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jiwaku.FiturCatatanKebaikan.CatatanKebaikanActivity
import com.google.firebase.database.*
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.jiwaku.R
import com.example.jiwaku.Utils.FunPindah

class JurnalActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var journalAdapter: JournalAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private lateinit var kembali: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catatan_jurnal)

        // tombol kembali
        kembali = findViewById(R.id.kembali)
        kembali.setOnClickListener {
            FunPindah.onBackPressed(this)
        }

        recyclerView = findViewById(R.id.recyclerViewJournals)
        recyclerView.layoutManager = LinearLayoutManager(this)
        journalAdapter = JournalAdapter()
        recyclerView.adapter = journalAdapter

        // SwipeRefreshLayout
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        swipeRefreshLayout.setOnRefreshListener {
            // Memuat ulang data ketika pengguna menarik ke bawah
            fetchAllJournals()
        }

        // Ambil semua data jurnal dari Firebase saat pertama kali
        fetchAllJournals()
    }

    override fun onResume() {
        super.onResume()
        // Memuat ulang data ketika pengguna kembali ke halaman ini
        fetchAllJournals()
    }

    // fungsi pindah halaman untuk membuat jurnal
    fun fDiary(view: View) {
        val intent = Intent(this, BuatJurnalActivity::class.java)
        startActivity(intent)
    }

    private fun fetchAllJournals() {
        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference("journals")

        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val journals = mutableListOf<Journal>()

                for (journalSnapshot in snapshot.children) {
                    val journal = journalSnapshot.getValue(Journal::class.java)
                    journal?.let {
                        journals.add(it)
                    }
                }

                journalAdapter.setJournals(journals)

                // Selesaikan proses refresh
                swipeRefreshLayout.isRefreshing = false

                // Tambahkan pesan Toast
                if (journals.isNotEmpty()) {
                    showToast("Data berhasil dimuat")
                } else {
                    showToast("Belum ada data jurnal")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
                showToast("Gagal memuat data. Silakan coba lagi.")
                // Selesaikan proses refresh
                swipeRefreshLayout.isRefreshing = false
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}