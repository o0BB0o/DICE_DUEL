package com.yjiang45.project2.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

import com.yjiang45.project2.R
import kotlinx.android.synthetic.main.result_fragment.*

class Result : Fragment() {

    companion object {
        fun newInstance() = Result()
    }

    private lateinit var viewModel: ResultViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.result_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var myScore=0
        var opScore=0
        viewModel = ViewModelProvider(this).get(ResultViewModel::class.java)
        viewModel.changeColor(view)

        arguments?.let {
            val safeArgs = ResultArgs.fromBundle(it)
            myScore = safeArgs.userScore
            opScore = safeArgs.aiScore
            //get the scores and then check whether the users wins or loses
            myscore.setText(myScore.toString())
            opscore.setText(opScore.toString())
            winLose.setText(viewModel.checkScore(opScore,myScore))
        }

    }

}
