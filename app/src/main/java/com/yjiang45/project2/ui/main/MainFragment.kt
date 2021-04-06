package com.yjiang45.project2.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.yjiang45.project2.PlaySound
import com.yjiang45.project2.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.changeColor(view)

        start_btn.setOnClickListener {
            viewModel.play(viewModel.sounds[0])
            view.findNavController().navigate(R.id.mainFragment_to_config)
        }
        setting_btn.setOnClickListener {
            viewModel.play(viewModel.sounds[1])
            view.findNavController().navigate(R.id.mainFragment_to_settings)
        }
    }

}
