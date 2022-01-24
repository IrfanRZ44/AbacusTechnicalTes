package com.exomatik.abacus.ui.main.splash

import android.os.Handler
import androidx.navigation.fragment.findNavController
import com.exomatik.abacus.R
import com.exomatik.abacus.base.BaseFragment

class SplashFragment : BaseFragment() {
    override fun getLayoutResource(): Int = R.layout.fragment_splash

    @Suppress("DEPRECATION")
    override fun myCodeHere() {
        supportActionBar?.hide()
        Handler().postDelayed({
            findNavController().navigate(R.id.berandaFragment)
        }, 2000L)
    }

}