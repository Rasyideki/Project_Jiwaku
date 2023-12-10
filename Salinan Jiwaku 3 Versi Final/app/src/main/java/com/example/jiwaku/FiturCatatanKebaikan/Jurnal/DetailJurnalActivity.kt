package com.example.jiwaku.FiturCatatanKebaikan.Jurnal

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import java.util.*
import com.example.jiwaku.R
import com.example.jiwaku.Utils.FunPindah

class DetailJurnalActivity : AppCompatActivity() {

    private lateinit var textViewTitle: TextView
    private lateinit var textViewDate: TextView
    private lateinit var textViewContent: TextView

    private lateinit var editTextTitle: EditText
    private lateinit var editTextDate: EditText
    private lateinit var editTextContent: EditText

    private lateinit var kembali: ImageButton

    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catatan_detail_jurnal)

        // tombol kembali
        kembali = findViewById(R.id.kembali)
        kembali.setOnClickListener{
            FunPindah.onBackPressed(this)
        }

        textViewTitle = findViewById(R.id.textViewTitle)
        textViewDate = findViewById(R.id.textViewDate)
        textViewContent = findViewById(R.id.textViewContent)

        // Mendapatkan data jurnalId dari intent
        val journalId = intent.getStringExtra("journalId") ?: ""

        // Ambil data jurnal dari Firebase
        fetchJournalData(journalId)

        // Tambahkan listener untuk tombol atau ikon edit
        findViewById<Button>(R.id.editButton).setOnClickListener {
            showEditDialog(journalId)
        }
    }

    private fun fetchJournalData(journalId: String) {
        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference("journals").child(journalId)

        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val journal = snapshot.getValue(Journal::class.java)

                    // Tampilkan data jurnal pada UI
                    journal?.let {
                        textViewTitle.text = it.title
                        textViewDate.text = it.date
                        textViewContent.text = it.content
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }

    private fun showEditDialog(journalId: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Edit Jurnal")

        val view = layoutInflater.inflate(R.layout.dialog_edit_jurnal, null)
        builder.setView(view)

        editTextTitle = view.findViewById(R.id.editTextTitle)
        editTextDate = view.findViewById(R.id.editTextDate)
        editTextContent = view.findViewById(R.id.editTextContent)

        // Set nilai awal dari jurnal ke elemen UI
        editTextTitle.setText(textViewTitle.text)
        editTextDate.setText(textViewDate.text)
        editTextContent.setText(textViewContent.text)

        editTextDate.setOnClickListener {
            showDatePicker()
        }

        builder.setPositiveButton("Simpan") { _, _ ->
            val updatedTitle = editTextTitle.text.toString()
            val updatedDate = editTextDate.text.toString()
            val updatedContent = editTextContent.text.toString()

            updateJournalData(journalId, updatedTitle, updatedDate, updatedContent)
        }

        builder.setNegativeButton("Batal") { _, _ ->
            // Batal edit, tidak perlu melakukan apa-apa
        }

        builder.show()
    }

    private fun updateJournalData(journalId: String, title: String, date: String, content: String) {
        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference("journals").child(journalId)

        // Update data jurnal ke Firebase
        val updatedJournal = Journal(journalId, title, date, content)
        ref.setValue(updatedJournal).addOnCompleteListener {
            if (it.isSuccessful) {
                // Update berhasil
                textViewTitle.text = title
                textViewDate.text = date
                textViewContent.text = content
                showToast("Data berhasil diupdate")
            } else {
                // Update gagal, tambahkan logika penanganan kesalahan jika diperlukan
                showToast("Gagal mengupdate data")
            }
        }
    }

    private fun showDatePicker() {
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                // Update nilai EditText tanggal setelah pemilihan tanggal
                val selectedDate = "$year-${month + 1}-$dayOfMonth"
                editTextDate.setText(selectedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
