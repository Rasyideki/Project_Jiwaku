package com.example.jiwaku.FiturCatatanKebaikan.Kalender

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jiwaku.R
import android.app.AlertDialog
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jiwaku.Utils.FunPindah
import com.google.firebase.database.*

class KalenderActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    private lateinit var recyclerView: RecyclerView
    private lateinit var catatanList: MutableList<Catatan>
    private lateinit var catatanAdapter: CatatanAdapter
    private lateinit var kembali: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catatan_kalender)

        // tombol kembali
        kembali = findViewById(R.id.kembali)
        kembali.setOnClickListener{
            FunPindah.onBackPressed(this)
        }

        databaseReference = FirebaseDatabase.getInstance().reference.child("DataCatatan")

        val calendarView: CalendarView = findViewById(R.id.calendarView)
        recyclerView = findViewById(R.id.recyclerView)

        catatanList = mutableListOf()
        catatanAdapter = CatatanAdapter(catatanList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = catatanAdapter

        // Set listener untuk tanggal yang dipilih
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            // Tanggapan ketika tanggal dipilih
            val selectedDate = "$dayOfMonth/${month + 1}/$year"

            // Tampilkan dialog untuk mengisi data
            tampilkanFormIsian(selectedDate)
        }

        // Membaca data dari Firebase dan menampilkannya di RecyclerView
        bacaDataDariFirebase()
    }

    private fun tampilkanFormIsian(selectedDate: String) {
        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.dialog_kalender, null)

        builder.setView(dialogView)
        val dialog = builder.create()

        val inputEvent = dialogView.findViewById<EditText>(R.id.inputEvent)
        val textSelectedDate = dialogView.findViewById<TextView>(R.id.textSelectedDate)

        textSelectedDate.text = selectedDate

        dialogView.findViewById<Button>(R.id.btnReset).setOnClickListener {
            val catatan = inputEvent.text.toString()

            // Simpan catatan ke Firebase Realtime Database
            val newCatatanRef = databaseReference.push()
            newCatatanRef.child("tanggal").setValue(selectedDate)
            newCatatanRef.child("kegiatan").setValue(catatan)

            dialog.dismiss()

            // Tampilkan toast berhasil
            toast("Data berhasil disimpan")
        }
        dialogView.findViewById<Button>(R.id.btnCancel).setOnClickListener {
            dialog.cancel()
        }
        if (dialog.window != null) {
            dialog.window!!.setBackgroundDrawable(ColorDrawable(0))
        }
        dialog.show()
    }

    fun toast(pesan: String){
        Toast.makeText(this,pesan, Toast.LENGTH_SHORT).show()
    }

    private fun bacaDataDariFirebase() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                catatanList.clear()

                for (dataSnapshot in snapshot.children) {
                    val catatan = dataSnapshot.getValue(Catatan::class.java)
                    catatan?.let { catatanList.add(it) }
                }

                catatanAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
                toast("Terjadi kesalahan: ${error.message}")
            }
        })
    }
}
