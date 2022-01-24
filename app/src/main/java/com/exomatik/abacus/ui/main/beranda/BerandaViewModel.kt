package com.exomatik.abacus.ui.main.beranda

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import com.exomatik.abacus.base.BaseViewModel
import com.exomatik.abacus.utils.Constant

@SuppressLint("StaticFieldLeak")
class BerandaViewModel(
    private val context: Context?
) : BaseViewModel() {
    val etParams = MutableLiveData<String>()
    val textMenu = MutableLiveData<String>()
    var input = ArrayList<String>()
    private var position = 0
    private var oldPosition = 0
    private var chooseMenu = 0

    private fun clearInput(){
        etParams.value = ""
        message.value = ""
        input.clear()
        position = 0
        oldPosition = 0
    }

    private fun nextInput(){
        val params = etParams.value
        if (params.isNullOrEmpty()){
            isShowError.value = true
            message.value = Constant.errorNoValue
        }
        else{
            etParams.value += ", "
            position++
        }
    }

    fun onClickSetText(textInput : Int){
        if (chooseMenu != 0){
            val params = etParams.value
            if (params.isNullOrEmpty()){
                etParams.value = textInput.toString()
                input.add(textInput.toString())
            }
            else{
                if (oldPosition != position){
                    oldPosition = position
                    input.add(textInput.toString())
                }
                else{
                    input[position] = input[position] + textInput.toString()
                }
                etParams.value = params + textInput
            }
        }
        else{
            isShowError.value = true
            message.value = Constant.errorChooseMenu
        }
    }

    fun onClickClearText(){
        clearInput()
    }

    fun onClickError(){
        isShowError.value = false
        message.value = ""
    }

    fun onClickChangeMenu(menu: Int){
        clearInput()
        chooseMenu = menu
        when (chooseMenu) {
            1 -> {
                textMenu.value = Constant.menu_add
            }
            2 -> {
                textMenu.value = Constant.menu_subtract
            }
            3 -> {
                textMenu.value = Constant.menu_multiply
            }
            4 -> {
                textMenu.value = Constant.menu_divide
            }
            5 -> {
                textMenu.value = Constant.menu_split_eq
            }
            6 -> {
                textMenu.value = Constant.menu_split_num
            }
        }
    }

    fun onClickNext(){
        if (chooseMenu != 0) {
            if (chooseMenu == 4 && input.size == 2){
                isShowError.value = true
                message.value = Constant.errorMaxParams
            }
            else if (chooseMenu == 5 && input.size == 2){
                isShowError.value = true
                message.value = Constant.errorMaxParams
            }
            else if (chooseMenu == 6 && input.size == 2){
                var finalResult = input[0].toInt()
                for (i in input.indices){
                    if (i != 0){
                        finalResult -= input[i].toInt()
                    }
                }

                if (finalResult < 0){
                    isShowError.value = true
                    message.value = Constant.errorParamsZero
                    clearInput()
                }
                else{
                    nextInput()
                }
            }
            else{
                nextInput()
            }
        }
        else{
            isShowError.value = true
            message.value = Constant.errorChooseMenu
        }
    }

    @Suppress("DEPRECATION")
    fun onClickResult(){
        isShowLoading.value = true
        if (chooseMenu != 0 && input.size > 1) {
            Handler().postDelayed({
                processResult()
            }, 500L)
        }
        else{
            if (input.size <= 1){
                isShowError.value = true
                message.value = Constant.errorUseCalc
            }
            else{
                isShowError.value = true
                message.value = Constant.errorChooseMenu
            }
        }
    }

    private fun dialogSucces(title: String, messageDialog: String){
        if (context != null){
            val alert = AlertDialog.Builder(context)
            alert.setTitle(title)
            alert.setMessage(messageDialog)
            alert.setPositiveButton(
                Constant.close
            ) { dialog, _ ->
                dialog.dismiss()
            }

            isShowLoading.value = false
            alert.show()
        }
        else{
            isShowError.value = true
            message.value = Constant.errorRetry
        }
    }

    private fun processResult(){
        when (chooseMenu) {
            1 -> {
                var finalResult = 0
                for (i in input.indices){
                    finalResult += input[i].toInt()
                }

                dialogSucces("${Constant.hasil} ${Constant.menu_add}", finalResult.toString())
            }
            2 -> {
                var finalResult = input[0].toInt()
                for (i in input.indices){
                    if (i != 0){
                        finalResult -= input[i].toInt()
                    }
                }

                dialogSucces("${Constant.hasil} ${Constant.menu_subtract}", finalResult.toString())
            }
            3 -> {
                var finalResult = input[0].toInt()
                for (i in input.indices){
                    if (i != 0){
                        finalResult *= input[i].toInt()
                    }
                }

                dialogSucces("${Constant.hasil} ${Constant.menu_multiply}", finalResult.toString())
            }
            4 -> {
                var finalResult = input[0].toInt()
                for (i in input.indices){
                    if (i != 0){
                        finalResult /= input[i].toInt()
                    }
                }

                dialogSucces("${Constant.hasil} ${Constant.menu_divide}", finalResult.toString())
            }
            5 -> {
                val inputA = input[0].toInt()
                val inputB = input[1].toInt()

                val split = inputA / inputB
                var finalResult = "$inputA, $inputB {"
                var i = 1
                do{
                    finalResult += "$split"
                    if(i != inputB){
                        finalResult += ", "
                    }
                    i++
                } while(i <= inputB)

                finalResult += "}"
                dialogSucces("${Constant.hasil} ${Constant.menu_split_eq}", finalResult)
            }
            6 -> {
                var finalResult = input[0].toInt()
                for (i in input.indices){
                    if (i != 0){
                        finalResult -= input[i].toInt()
                    }
                }

                dialogSucces("${Constant.hasil} ${Constant.menu_split_num}", finalResult.toString())
            }
        }

        clearInput()
    }
}