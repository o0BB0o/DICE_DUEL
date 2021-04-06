package com.yjiang45.project2.ui.main

import android.graphics.Color
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.settings_fragment.*

import com.yjiang45.project2.R

class Settings : Fragment() {

    companion object {
        fun newInstance() = Settings()
        var color = 0
    }

    private lateinit var viewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.settings_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SettingsViewModel::class.java)

        if(color==0){
            isPurple.isChecked=true
            isCyan.isChecked=false
            view?.setBackgroundColor(Color.parseColor("#673AB7"))
        }
        else if(color==1){
            isPurple.isChecked=false
            isCyan.isChecked=true
            view?.setBackgroundColor(Color.CYAN)
        }


        // TODO: Use the ViewModel
        radioGroup.setOnCheckedChangeListener { _, id ->
             when (id) {
                R.id.isPurple -> {
                    color=0
                    view?.setBackgroundColor(Color.parseColor("#673AB7"))
                }
                R.id.isCyan -> {
                    color=1
                    view?.setBackgroundColor(Color.CYAN)
                }
            }
        }
    }

}
