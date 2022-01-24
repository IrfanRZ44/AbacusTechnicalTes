package com.exomatik.abacus.base

import android.view.View
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

abstract class BaseFragment : Fragment() {
    protected lateinit var v : View
    protected abstract fun getLayoutResource(): Int
    protected abstract fun myCodeHere()
    protected var supportActionBar : ActionBar? = null

    override fun onCreateView(paramLayoutInflater: LayoutInflater, paramViewGroup: ViewGroup?, paramBundle: Bundle?): View? {
        supportActionBar = (activity as AppCompatActivity).supportActionBar
        v = paramLayoutInflater.inflate(getLayoutResource(), paramViewGroup, false)

        myCodeHere()
        return v
    }
}