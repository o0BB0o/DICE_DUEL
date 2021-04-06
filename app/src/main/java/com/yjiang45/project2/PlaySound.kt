package com.yjiang45.project2

import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import android.graphics.Color
import android.media.SoundPool
import android.view.View
import com.yjiang45.project2.ui.main.Settings

data class Sound(val path: String,
                 var sndId: Int? = null)

class PlaySound(private val assets: AssetManager) {

    val sounds: List<Sound>
    private val soundPool = SoundPool.Builder().setMaxStreams(6).build()
    init {
        sounds = loadSounds()
    }

    fun release() = soundPool.release()
    fun play(sound: Sound) {
        soundPool.play(sound.sndId!!,
            1f,
            1f,
            1,
            0,
            1f)
    }

    private fun loadSounds(): List<Sound> {
        var files = assets.list("sound_res")
        val sounds = mutableListOf<Sound>()
        files?.forEach { filename ->
            val sound = Sound("sound_res/$filename")
            val afd: AssetFileDescriptor =
                assets.openFd("sound_res/$filename")
            sound.sndId =
                soundPool.load(afd, 1)
            sounds.add(sound)
        }
        return sounds
    }


}