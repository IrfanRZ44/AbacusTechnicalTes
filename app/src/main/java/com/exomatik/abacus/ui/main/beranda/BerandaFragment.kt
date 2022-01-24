package com.exomatik.abacus.ui.main.beranda

import com.exomatik.abacus.R
import com.exomatik.abacus.base.BaseFragmentBind
import com.exomatik.abacus.databinding.FragmentBerandaBinding
import com.exomatik.abacus.utils.Constant

class BerandaFragment : BaseFragmentBind<FragmentBerandaBinding>() {
    private lateinit var viewModel: BerandaViewModel

    override fun getLayoutResource(): Int = R.layout.fragment_beranda

    override fun myCodeHere() {
        supportActionBar?.show()
        supportActionBar?.title = Constant.appName
        bind.lifecycleOwner = this
        viewModel = BerandaViewModel(context)
        bind.viewModel = viewModel
        viewModel.textMenu.value = Constant.menu_choose
    }
}

