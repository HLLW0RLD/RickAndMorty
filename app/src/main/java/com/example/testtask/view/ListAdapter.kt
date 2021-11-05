package com.example.testtask.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testtask.databinding.ItemBinding
import com.example.testtask.model.character.Character
import kotlin.collections.List

class ListAdapter() : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private var charList : List<Character> = listOf()

    private var onCharacterClickListener : ListFragment.OnCharacterClickListener? = null

    fun setData(newData: List<Character>) {
        charList = newData
        notifyDataSetChanged()
    }

    fun setCharacterListener(onCharacterClickListener: ListFragment.OnCharacterClickListener?) {
        this.onCharacterClickListener = onCharacterClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListAdapter.ViewHolder, position: Int) {
        holder.bind(charList[position])
    }

    override fun getItemCount(): Int {
        return charList.size
    }

    inner class ViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(character: Character){
            binding.name.text = character.name
            binding.species.text = character.species
            binding.status.text = character.status
            binding.gender.text = character.gender
            binding.root.setOnClickListener { onCharacterClickListener?.onCharacterClick(character) }

            Glide
                .with(binding.root)
                .load(character.image)
                .into(binding.iconList)
        }

    }


}