package com.bignerdranch.android.lab2_holders.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.lab2_holders.R
import com.bignerdranch.android.lab2_holders.data.Character
import com.bignerdranch.android.lab2_holders.data.Results
import com.squareup.picasso.Picasso

class CharacterAdapter(private val context: Context, private val characters: Results) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemViewType(position: Int): Int {
        if (characters.results[position].getType() == 1) {
            return R.layout.retrofit_image
        } else if (characters.results[position].getType() == 2) {
            return R.layout.retrofit_name
        }

        return R.layout.retrofit_species
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)

        if (viewType == R.layout.retrofit_image) {
            return ImageViewHolder(view)
        } else if (viewType == R.layout.retrofit_name) {
            return NameViewHolder(view)
        }

        return SpeciesViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ImageViewHolder) {
            holder.bind(characters.results[position])
        } else if (holder is NameViewHolder) {
            holder.bind(characters.results[position])
        } else {
            (holder as SpeciesViewHolder).bind(characters.results[position])
        }
    }

    override fun getItemCount(): Int {
        return characters.results.size
    }

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var imageView: ImageView = view.findViewById(R.id.imageView)

        fun bind(character: Character) {
            Picasso.get()
                .load(character.image)
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView)
        }
    }

    class NameViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView: TextView = view.findViewById(R.id.textViewName)

        fun bind(character: Character) {
            textView.text = character.name
        }
    }

    class SpeciesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView: TextView = view.findViewById(R.id.textViewSpecies)

        fun bind(character: Character) {
            textView.text = character.species
        }
    }
}