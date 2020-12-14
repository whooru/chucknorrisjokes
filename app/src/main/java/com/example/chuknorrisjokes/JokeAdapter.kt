package com.example.chuknorrisjokes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chuknorrisjokes.model.Joke
import kotlinx.android.synthetic.main.item_layout.view.*

class JokeAdapter(private val jokesList: ArrayList<Joke>) :
    RecyclerView.Adapter<JokeAdapter.JokeViewHolder>() {
    class JokeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val jokeText: TextView = itemView.jokeText
        fun bind(listItem: Joke) {
            jokeText.text = listItem.joke
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return JokeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        val listItem = jokesList[position]
        holder.bind(listItem)
    }

    override fun getItemCount() = jokesList.size
}