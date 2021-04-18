package com.yjiang45.project2.ui.main

import android.app.Application
import android.graphics.Color
import android.view.View
import androidx.lifecycle.AndroidViewModel


class ResultViewModel(application: Application) : AndroidViewModel(application) {

    fun changeColor(view: View){//change color based on the color modified in settings
        if(Settings.color ==0){
            view.setBackgroundColor(Color.parseColor("#673AB7"))
        }
        else if(Settings.color ==1){
            view.setBackgroundColor(Color.CYAN)
        }
    }

    fun checkScore(opScore:Int,myScore:Int):String{//check the outcome score
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
