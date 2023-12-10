package com.example.jiwaku.HomePage.MenuNavigation.JadwalFragment
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jiwaku.R
import com.example.jiwaku.databinding.FragmentJadwalBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class JadwalFragment : Fragment(R.layout.fragment_jadwal) {

    private lateinit var binding: FragmentJadwalBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentJadwalBinding.bind(view)

        readAllData()
    }

    private fun readAllData() {
        val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("Jadwal Konsultasi")
        database.get().addOnSuccessListener { dataSnapshot ->
            if (dataSnapshot.exists()) {
                val dataList = mutableListOf<Konsultasi>()

                for (childSnapshot in dataSnapshot.children) {
                    val userId = childSnapshot.key
                    val namaDokter = childSnapshot.child("namaDokter").value as? String ?: ""
                    val sesiTanggal = childSnapshot.child("sesiTanggal").value as? String ?: ""
                    val sesiWaktu = childSnapshot.child("sesiWaktu").value as? String ?: ""
                    val deskMasalah = childSnapshot.child("deskMasalah").value as? String ?: ""

                    val data = Konsultasi(userId, namaDokter, sesiTanggal, sesiWaktu, deskMasalah)
                    dataList.add(data)
                }

                // Inisialisasi RecyclerView
                binding.recyclerView.adapter = KonsultasiAdapter(dataList)
                // Set layout manager
                binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
                // Pesan berhasil
                Toast.makeText(requireContext(), "Anda Memiliki Sesi Konsultasi", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(requireContext(), "Anda Belum Memiliki Sesi Konsultasi", Toast.LENGTH_LONG).show()
            }
        }.addOnFailureListener {
            Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
        }
    }
}
