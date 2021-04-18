package com.yjiang45.project2.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_item.view.*
import androidx.navigation.findNavController

import com.yjiang45.project2.R

class Config : Fragment() {//the configuration page
    private lateinit var Recycler: RecyclerView

    companion object {
        fun newInstance() = Config()
    }

    private lateinit var viewModel: ConfigViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.config_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ConfigViewModel::class.java)
        viewModel.changeColor(view)

        Recycler = view.findViewById(R.id.recycler_view)
        Recycler.layoutManager = LinearLayoutManager(context)
        Recycler.adapter = Adapter(viewModel.items)

    }


    private inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private lateinit var item: Item
        private val titleTextView: TextView = itemView.title

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val numSelected = this.item.num
            val actionEval = ConfigDirections.configToGame(numSelected)
            view?.findNavController()?.navigate(actionEval)
        }

        fun bind(item: Item) {
            this.item = item
            titleTextView.text = item.output
        }
    }

    private inner class Adapter(private val items: List<Item>) :
        RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = layoutInflater.inflate(R.layout.card_item, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount() = items.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(items[position])
        }
    }

}
