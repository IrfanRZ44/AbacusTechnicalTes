package com.exomatik.abacus.ui.main

import android.view.View
import androidx.navigation.fragment.NavHostFragment
import com.exomatik.abacus.R
import com.exomatik.abacus.base.BaseActivity
import kotlinx.android.synthetic.main.activity_auth.*

class MainActivity : BaseActivity() {
    override fun getLayoutResource(): Int = R.layout.activity_auth

    override fun myCodeHere() {
        NavHostFragment.create(R.navigation.main_nav)
        viewParent.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }
}
