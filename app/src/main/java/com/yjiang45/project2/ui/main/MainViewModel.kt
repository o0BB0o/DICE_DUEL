package com.yjiang45.project2.ui.main

import android.app.Application
import android.graphics.Color
import android.view.View
import androidx.lifecycle.AndroidViewModel
import com.yjiang45.project2.PlaySound
import com.yjiang45.project2.Sound

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val playSound = PlaySound(getApplication<Application>().assets)
    val sounds = playSound.sounds

    override fun onCleared() {
        super.onCleared()
        playSound.release()
    }

    fun play(sound: Sound) {
        playSound.play(sound)
    }

    fun changeColor(view: View){
        if(Settings.color ==0){
            view.setBackgroundColor(Color.parseColor("#673AB7"))
        }
        else if(Settings.color ==1){
            view.setBackgroundColor(Color.CYAN)
        }
    }
}
