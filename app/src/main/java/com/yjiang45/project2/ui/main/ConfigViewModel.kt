package com.yjiang45.project2.ui.main

import android.graphics.Color
import android.view.View
import androidx.lifecycle.ViewModel

data class Item(val num: Int, val output: String)

class ConfigViewModel : ViewModel() {
    val items: List<Item> = listOf(
        Item(
            num = 1,
            output = "Use 1 dice to DUEL"
        ),
        Item(
            num = 2,
            output = "Use 2 dices to DUEL"
        ),
        Item(
            num = 3,
            output = "Use 3 dices to DUEL"
        )
    )
    fun changeColor(view: View){
        if(Settings.color ==0){
            view.setBackgroundColor(Color.parseColor("#673AB7"))
        }
        else if(Settings.color ==1){
            view.setBackgroundColor(Color.CYAN)
        }
    }
}
