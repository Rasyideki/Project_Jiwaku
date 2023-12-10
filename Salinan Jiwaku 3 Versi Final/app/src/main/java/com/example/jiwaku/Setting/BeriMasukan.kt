package com.example.jiwaku.Setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jiwaku.R
import android.app.AlertDialog
import android.text.Html
import android.widget.Button
import android.widget.ImageButton
import com.example.jiwaku.Utils.FunPindah
import android.content.Intent
import android.widget.Toast
import com.example.jiwaku.HomePage.MenuNavigation.SettingFragment
import com.example.jiwaku.databinding.ActivityBeriMasukanBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.checkerframework.common.returnsreceiver.qual.This
import java.util.UUID

class BeriMasukan : AppCompatActivity() {
    private lateinit var btnSubmit: Button
    private lateinit var btKembali: ImageButton
    private lateinit var binding: ActivityBeriMasukanBinding
    private lateinit var database: DatabaseReference
//    private var editText: TextInputEditText = findViewById(R.id.editText)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Proses Inisialisasi xml
        binding = ActivityBeriMasukanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnSubmit = findViewById<Button>(R.id.btnSubmit)
        btKembali = findViewById<ImageButton>(R.id.kembali)

        btKembali.setOnClickListener {
            FunPindah.onBackPressed(this)
        }

        btnSubmit.setOnClickListener {
            // Tampilkan popup pesan
            showPopup()
        }

        // Inisialisasi nilai awal pada teks
        var nilai: String = ""

        // Memberikan listener pada RadioGroup
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            nilai = when (checkedId) {
                R.id.radioPuas -> "Kurang Puas"
                R.id.radioKurangPuas -> "Cukup Puas"
                R.id.radioSangatPuas -> "Sangat Puas"
                else -> ""
            }
        }

        // Tombol Simpan dan Proses Simpan ke dalam database Firebase Realtime
        binding.btnSubmit.setOnClickListener {

            // Proses Konfigurasi Ui dan Controller
            val id = UUID.randomUUID().toString()
            val feedBack = binding.editText.text.toString()

            // Tentukan Path Nama database
            database = FirebaseDatabase.getInstance().getReference("Masukan Dari User")
            // Konfigurasi penyimpanan data ke dalam database menggunakan data jadwal
            val users = user(id, feedBack, nilai)
            database.child(id).setValue(users).addOnSuccessListener {
                showPopup()
                // Proses Clear setelah mengisi form
                binding.editText.text?.clear()

                // Toast berhasil
                Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show()

            }.addOnFailureListener{
                //toast gagal
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun showPopup() {
        val builder = AlertDialog.Builder(this)
        val title = "<font color='#FFFFFF'>Terima kasih</font>"
        builder.setTitle(Html.fromHtml(title))
        // Menggunakan HTML untuk menentukan warna teks
        val message = "<font color='#FFFFFF'>Terima kasih atas feedback Anda! Kami sangat menghargai kontribusi Anda dan pesan Anda telah kami terima dengan baik." +
                " Dukungan Anda sangat berarti bagi kami.</font>"
        builder.setMessage(Html.fromHtml(message))

        // Tambahkan ikon jika diinginkan
        builder.setIcon(android.R.drawable.ic_dialog_email)

        // Tambahkan tombol OK dengan listener
        builder.setPositiveButton("OK") { dialog, _ ->
            // Alihkan dismiss ke dalam blok onClick
            FunPindah.onBackPressed(this)
        }

        // Buat dan tampilkan dialog
        val dialog = builder.create()

        // Atur tata letak dan propertinya
        dialog.setOnShowListener {
            // Tambahkan radius
            dialog.window?.setBackgroundDrawableResource(R.drawable.dialog_background)
            // Sesuaikan dengan warna yang diinginkan
            dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                .setTextColor(resources.getColor(R.color.white))
        }

        dialog.show()
    }
}


