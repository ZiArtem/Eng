package com.example.myenglishapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myenglishapp.databinding.ItemBinding
import com.example.myenglishapp.entities.Word
import kotlinx.android.synthetic.main.item.view.*

interface WordActionListener {
    fun deleteItem(word: Word) {
    }
}

class WordDiffCallback(private val oldList: List<Word>, private val  newList:  List<Word>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        if (oldList[oldItemPosition].id!=newList[newItemPosition].id)
            return false
        if (oldList[oldItemPosition].title!=newList[newItemPosition].title)
            return false
        if (oldList[oldItemPosition].description!=newList[newItemPosition].description)
            return false
        return true
    }
}

class WordAdapter(private val actionListener: WordActionListener) :
    RecyclerView.Adapter<WordAdapter.MyViewHolder>(), View.OnClickListener {
    private var wordList = emptyList<Word>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(inflater, parent, false)
//        binding.root.setOnClickListener(this)
        binding.cross.setOnClickListener(this)

        return MyViewHolder(binding)
    }

    class MyViewHolder(
        private val binding: ItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onClick(v: View) {
        val word: Word = v.tag as Word
        when (v.id) {
            R.id.cross -> {
                actionListener.deleteItem(word)
            }

            else -> {
//                actionListener.deleteItem(word)
            }
        }
    }

    override fun getItemCount(): Int {
        return wordList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = wordList[position]

        holder.itemView.word.text = currentItem.title.toString()
        holder.itemView.translation.text = currentItem.description.toString()
        holder.itemView.tag = currentItem
        holder.itemView.cross.tag = currentItem
    }

    fun setData(words: List<Word>) {
        val difUpdate =  DiffUtil.calculateDiff(WordDiffCallback(wordList,words))
        wordList = words
        difUpdate.dispatchUpdatesTo(this)
    }
}