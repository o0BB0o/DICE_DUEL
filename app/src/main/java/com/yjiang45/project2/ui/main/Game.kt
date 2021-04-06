package com.yjiang45.project2.ui.main

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.drawable.Animatable
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.animation.doOnEnd
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.yjiang45.project2.R
import kotlinx.android.synthetic.main.game_fragment.*


class Game : Fragment() {
    companion object {
        fun newInstance() = Game()
    }

    private lateinit var viewModel: GameViewModel
    var diceUsed=1
    var rolled=0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.game_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        viewModel.changeColor(view)



        arguments?.let {
            val safeArgs = GameArgs.fromBundle(it)
            diceUsed = safeArgs.numSelected
            startDice(diceUsed)
        }
        rRDuel()

        reRollBtn.setOnClickListener{
            rolled=0
            startDice(diceUsed)
            myDice1.setImageResource(R.drawable.dicequestion)
            myDice2.setImageResource(R.drawable.dicequestion)
            myDice3.setImageResource(R.drawable.dicequestion)
            viewModel.score=0
            viewModel.aiScore=0
            rRDuel()
        }

        duelBtn.setOnClickListener{
            aiShow()
            if(viewModel.aiScore<=viewModel.score){
                viewModel.play(viewModel.sounds[5])
            }
            else viewModel.play(viewModel.sounds[6])
            Handler().postDelayed({
                val actionEval = GameDirections.gameToResult(viewModel.score,viewModel.aiScore)
                view.findNavController().navigate(actionEval)
            },1500.toLong())

        }
    }

    private fun startDice(num:Int){
        when(num){
            1->{
                myDice1.alpha=0f
                myDice3.alpha=0f
                aiDice1.alpha=0f
                aiDice3.alpha=0f
                fadeIn(myDice2)
                myDice2.setOnClickListener{
                    rolled++
                    rollDice(myDice2)
                    viewModel.play(viewModel.sounds[3])
                    myDice2.isEnabled=false
                }
            }
            2->{
                myDice2.alpha=0f
                aiDice2.alpha=0f
                fadeIn(myDice1)
                fadeIn(myDice3)
                myDice1.setOnClickListener{
                    rolled++
                    viewModel.play(viewModel.sounds[2])
                    rollDice(myDice1)
                    myDice1.isEnabled=false
                }
                myDice3.setOnClickListener{
                    rolled++
                    viewModel.play(viewModel.sounds[4])
                    rollDice(myDice3)
                    myDice3.isEnabled=false
                }
            }
            3->{
                fadeIn(myDice1)
                fadeIn(myDice2)
                fadeIn(myDice3)
                myDice1.setOnClickListener{
                    rolled++
                    viewModel.play(viewModel.sounds[2])
                    rollDice(myDice1)
                    myDice1.isEnabled=false
                    //rRDuel()
                }
                myDice2.setOnClickListener{
                    rolled++
                    viewModel.play(viewModel.sounds[3])
                    rollDice(myDice2)
                    myDice2.isEnabled=false
                   // rRDuel()
                }
                myDice3.setOnClickListener{
                    rolled++
                    viewModel.play(viewModel.sounds[4])
                    rollDice(myDice3)
                    myDice3.isEnabled=false
                    //rRDuel()
                }

            }
        }
    }

    private fun fadeIn(imageView: ImageView){
        imageView.alpha=0f
        imageView.visibility = View.VISIBLE
        imageView.animate()
            .alpha(1f)
            .setDuration(2000.toLong())
            .setListener(null)
        imageView.isEnabled=true
    }

    private fun rollDice(imageView: ImageView){
        var currentLocation = imageView.x
        for(i in 0 until 6){
            Handler().postDelayed({
                val set = AnimatorSet()
                val move1=ObjectAnimator.ofFloat(imageView,"x",currentLocation-25)
                val move2=ObjectAnimator.ofFloat(imageView,"x",currentLocation+25)
                val back=ObjectAnimator.ofFloat(imageView,"x",currentLocation)
                if(i==5){
                    set.play(back)
                    imageView.setImageResource(getRandomDice((1..6).random(),1,true))
                }
                else {
                    set.play(move1).before(move2)
                    imageView.setImageResource(getRandomDice((1..6).random(),1,false))
                }
                set.duration=(100.toLong())
                set.start()
            },200*i.toLong())
        }
        Handler().postDelayed({
            rRDuel()
        },1200.toLong())
    }

    private fun getRandomDice(rand:Int,ai:Int,add:Boolean):Int{
        if(ai==0){
            viewModel.score+=rand
        }
        else{
            if(add) {
                viewModel.aiScore += rand
            }
        }
        when(rand){
            1->return R.drawable.dice1
            2->return R.drawable.dice2
            3->return R.drawable.dice3
            4->return R.drawable.dice4
            5->return R.drawable.dice5
            6->return R.drawable.dice6
        }
        return 0
    }

    private fun rRDuel(){
        if(rolled==diceUsed){
            reRollBtn.isEnabled=true
            duelBtn.isEnabled=true
        }
        else{
            reRollBtn.isEnabled=false
            duelBtn.isEnabled=false
        }
    }

    private fun aiShow(){
        when(diceUsed){
            1->aiDice2.setImageResource(getRandomDice((1..6).random(),0,true))

            2->{
                aiDice1.setImageResource(getRandomDice((1..6).random(),0,true))
                aiDice3.setImageResource(getRandomDice((1..6).random(),0,true))
            }
            3->{
                aiDice1.setImageResource(getRandomDice((1..6).random(),0,true))
                aiDice2.setImageResource(getRandomDice((1..6).random(),0,true))
                aiDice3.setImageResource(getRandomDice((1..6).random(),0,true))
            }
        }
    }

}
