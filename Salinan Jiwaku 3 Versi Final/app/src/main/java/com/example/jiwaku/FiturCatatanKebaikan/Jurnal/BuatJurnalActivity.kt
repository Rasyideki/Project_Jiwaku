package com.example.jiwaku.FiturCatatanKebaikan.Jurnal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.jiwaku.R
import com.example.jiwaku.Utils.FunPindah
import android.app.DatePickerDialog
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.os.Handler
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*

class BuatJurnalActivity : AppCompatActivity() {

    private lateinit var editTextTitle: EditText
    private lateinit var editTextDate: EditText
    private lateinit var editTextContent: EditText
    private lateinit var imageButtonCalendar: ImageButton
    private lateinit var buttonSave: Button

    private val calendar: Calendar = Calendar.getInstance()
    private lateinit var kembali: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catatan_buat_jurnal)

        // tombol kembali
        kembali = findViewById(R.id.kembali)
        kembali.setOnClickListener{
            FunPindah.onBackPressed(this)
        }

        editTextTitle = findViewById(R.id.editTextTitle)
        editTextDate = findViewById(R.id.editTextDate)
        editTextContent = findViewById(R.id.editTextContent)
        imageButtonCalendar = findViewById(R.id.imageButtonCalendar)
        buttonSave = findViewById(R.id.buttonSave)

        // Menampilkan DatePickerDialog saat ikon kalender ditekan
        imageButtonCalendar.setOnClickListener {
            showDatePickerDialog()
        }

        buttonSave.setOnClickListener {
            saveJournalToFirebase()
        }
    }

    private fun showDatePickerDialog() {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, monthOfYear, dayOfMonth ->
                // Update EditText dengan tanggal yang dipilih
                val selectedDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                    .format(Calendar.getInstance().apply {
                        set(Calendar.YEAR, year)
                        set(Calendar.MONTH, monthOfYear)
                        set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    }.time)
                editTextDate.setText(selectedDate)
            },
            year, month, day
        )

        datePickerDialog.show()
    }

    private fun saveJournalToFirebase() {
        val title = editTextTitle.text.toString()
        val date = editTextDate.text.toString()
        val content = editTextContent.text.toString()

        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference("journals")

        val journalId = ref.push().key
        val journal = Journal(journalId, title, date, content)

        // Menyimpan data ke Firebase
        ref.child(journalId!!)
            .setValue(journal)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    showToast("Data berhasil disimpan")

                    // Tambahkan jeda sebelum pindah ke halaman Baca Jurnal Activity
                    Handler().postDelayed({
                        openJurnalActivity(journalId!!)
                    }, 3000) // Jeda 3 detik
                } else {
                    showToast("Gagal menyimpan data. Silakan coba lagi.")
                }
            }
    }

    // pindah halaman ke jurnal kebaikan
    private fun openJurnalActivity(journalId: String) {
        val intent = Intent(this, JurnalActivity::class.java)
        intent.putExtra("journalId", journalId)
        startActivity(intent)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}