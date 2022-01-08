package com.example.myenglishapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myenglishapp.databinding.ItemListBinding
import com.example.myenglishapp.entities.ListWords
import kotlinx.android.synthetic.main.item_list.view.*
import java.util.*

interface ListWordActionListener {
    fun deleteItem(wordlist: ListWords) {
    }
}

class ListWordsDiffCallback(private val oldList: List<ListWords>, private val  newList:  List<ListWords>) :
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
        if (oldList[oldItemPosition].date!=newList[newItemPosition].date)
            return false
        return true
    }
}

class ListWordAdapter(private val actionListener: ListWordActionListener) :
    RecyclerView.Adapter<ListWordAdapter.MyViewHolder>(), View.OnClickListener {
    private var wordlist = emptyList<ListWords>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(inflater, parent, false)
//        binding.root.setOnClickListener(this)
        binding.crossList.setOnClickListener(this)

        return MyViewHolder(binding)
    }

    class MyViewHolder(
        private val binding: ItemListBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onClick(v: View) {
        val wordlist: ListWords = v.tag as ListWords
        when (v.id) {
            R.id.crossList -> {
                actionListener.deleteItem(wordlist)
            }

            else -> {
//                actionListener.deleteItem(word)
            }
        }
    }

    override fun getItemCount(): Int {
        return wordlist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = wordlist[position]

        holder.itemView.titleList.text = currentItem.title.toString()
        holder.itemView.date.text = currentItem.date.toString()
        holder.itemView.numWordsOnLists.text = 1.toString()
        holder.itemView.tag = currentItem
        holder.itemView.crossList.tag = currentItem
    }

    fun setData(wordlist_: List<ListWords>) {
        val difUpdate =  DiffUtil.calculateDiff(ListWordsDiffCallback(wordlist,wordlist_))
        wordlist = wordlist_
        difUpdate.dispatchUpdatesTo(this)
    }
}