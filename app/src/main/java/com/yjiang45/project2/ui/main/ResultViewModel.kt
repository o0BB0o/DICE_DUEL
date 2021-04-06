package com.yjiang45.project2.ui.main

import android.app.Application
import android.graphics.Color
import android.view.View
import androidx.lifecycle.AndroidViewModel


class ResultViewModel(application: Application) : AndroidViewModel(application) {

    fun changeColor(view: View){
        if(Settings.color ==0){
            view.setBackgroundColor(Color.parseColor("#673AB7"))
        }
        else if(Settings.color ==1){
            view.setBackgroundColor(Color.CYAN)
        }
    }

    fun checkScore(opScore:Int,myScore:Int):String{
        if(opScore>myScore){
            return "YOULOSE"
        }
        else if(opScore==myScore){
            return "DRAW"
        }
        else{
            return "YOUWIN!!"
        }
    }
}
