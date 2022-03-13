package com.danielriverolosa.rickandmortybeerbuddy.ui.character.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.danielriverolosa.domain.entity.Character
import com.danielriverolosa.rickandmortybeerbuddy.R
import com.danielriverolosa.rickandmortybeerbuddy.databinding.CharacterListItemBinding
import coil.load

class CharacterListAdapter : ListAdapter<Character, CharacterListAdapter.ViewHolder>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(CharacterListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: CharacterListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Character) {
            binding.apply {
                name.text = item.name
                species.text = item.species
                type.text = item.type
                image.apply {
                    transitionName = item.image
                    load(item.image)
                }
            }
        }
    }
}