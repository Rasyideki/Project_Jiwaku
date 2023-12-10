package com.example.jiwaku.HomePage.MenuNavigation

import android.os.Bundle
import android.view.View
import android.content.Context
import android.content.Intent
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.jiwaku.FiturCatatanKebaikan.CatatanKebaikanActivity
import com.example.jiwaku.FiturJiwapedia.HalVideo
import com.example.jiwaku.FiturJiwapedia.HalVideo2
import com.example.jiwaku.FiturJiwapedia.HalVideo3
import com.example.jiwaku.FiturJiwapedia.HalVideo4
import com.example.jiwaku.FiturJiwapedia.JiwapediaActivity
import com.example.jiwaku.FiturKonsultasi.KonsultasiActivity
import com.example.jiwaku.FiturMapLokasi.MapsActivity
import com.example.jiwaku.FiturMeditasi.MeditasiActivity
import com.example.jiwaku.FiturPanggilanDarurat.PanggilanDaruratActivity
import com.example.jiwaku.HomePage.MenuActivity
import com.example.jiwaku.LoginSignup.LoginActivity
import com.example.jiwaku.LoginSignup.SessionManager
import com.example.jiwaku.R
import com.example.jiwaku.Utils.FunPindah
import com.example.jiwaku.Utils.FunPindah.pindahScene
import com.example.jiwaku.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        // Inisialisasi Button Buat Menu
        binding.btMeditasi.setOnClickListener {
            pindahScene(requireContext(), MeditasiActivity::class.java)
        }

        binding.btCall.setOnClickListener {
            pindahScene(requireContext(), PanggilanDaruratActivity::class.java)
        }

        binding.btGoogleMaps.setOnClickListener {
            pindahScene(requireContext(), MapsActivity::class.java)
        }

        binding.btKonseling.setOnClickListener {
            pindahScene(requireContext(), KonsultasiActivity::class.java)
        }

        binding.btJiwapedia.setOnClickListener {
            pindahScene(requireContext(), JiwapediaActivity::class.java)
        }

        binding.btCatatan.setOnClickListener {
            pindahScene(requireContext(), CatatanKebaikanActivity::class.java)
        }

        // Konten Banner
        binding.textBanner1.setOnClickListener{
            FunPindah.pindahScene(requireContext(), HalVideo::class.java)
        }

        binding.textBanner2.setOnClickListener{
            FunPindah.pindahScene(requireContext(), KonsultasiActivity::class.java)
        }

        // Konten Top Video
        binding.video1.setOnClickListener{
            FunPindah.pindahScene(requireContext(), HalVideo::class.java)
        }

        binding.video2.setOnClickListener{
            FunPindah.pindahScene(requireContext(), HalVideo3::class.java)
        }

        binding.video3.setOnClickListener{
            FunPindah.pindahScene(requireContext(), HalVideo2::class.java)
        }

        binding.video4.setOnClickListener{
            FunPindah.pindahScene(requireContext(), HalVideo4::class.java)
        }
    }
}
