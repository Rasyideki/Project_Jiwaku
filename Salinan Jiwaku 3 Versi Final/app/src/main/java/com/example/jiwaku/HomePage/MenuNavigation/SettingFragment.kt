package com.example.jiwaku.HomePage.MenuNavigation

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.jiwaku.LoginSignup.LoginActivity
import com.example.jiwaku.LoginSignup.SessionManager
import com.example.jiwaku.R
import com.example.jiwaku.Utils.FunPindah
import androidx.appcompat.app.AlertDialog
import com.example.jiwaku.Setting.BeriMasukan
import com.example.jiwaku.Setting.TentangKami

class SettingFragment : Fragment(R.layout.fragment_setting) {

    private lateinit var btAbout: TextView
    private lateinit var btMasukan: TextView
    private lateinit var btLogOut: TextView

    private lateinit var sessionManager: SessionManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sessionManager = SessionManager(requireContext())

        btAbout = view.findViewById(R.id.btAbout)
        btMasukan = view.findViewById(R.id.btMasukan)
        btLogOut = view.findViewById(R.id.btLogOut)

        btAbout.setOnClickListener {
            FunPindah.pindahScene(requireContext(), TentangKami::class.java)
        }

        btMasukan.setOnClickListener {
            FunPindah.pindahScene(requireContext(), BeriMasukan::class.java)
        }

        btLogOut.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            val dialogView = layoutInflater.inflate(R.layout.activity_loginsignup_logout, null)

            builder.setView(dialogView)
            val dialog = builder.create()

            dialogView.findViewById<Button>(R.id.btnReset).setOnClickListener {
                logout()
                dialog.dismiss()
            }
            dialogView.findViewById<Button>(R.id.btnCancel).setOnClickListener {
                dialog.dismiss()
            }
            if (dialog.window != null) {
                dialog.window!!.setBackgroundDrawable(ColorDrawable(0))
            }
            dialog.show()
        }
    }

    private fun logout() {
        sessionManager.logout()
        val intent = Intent(requireContext(), LoginActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }
}
