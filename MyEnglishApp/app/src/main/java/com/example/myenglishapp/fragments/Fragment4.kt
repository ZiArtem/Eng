package com.example.myenglishapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.myenglishapp.R
import com.example.myenglishapp.WordActionListener
import com.example.myenglishapp.WordAdapter
import com.example.myenglishapp.databinding.ActivityMain2Binding
import com.example.myenglishapp.databinding.Fragment4Binding
import com.example.myenglishapp.entities.Word
import com.example.myenglishapp.viewModel.PrViewModel
import kotlinx.coroutines.launch

class Fragment4 : Fragment() {
    private lateinit var binding: Fragment4Binding
    private lateinit var adapter: WordAdapter
    private val mUserViewModel: PrViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = Fragment4Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = WordAdapter(object : WordActionListener {
            override fun deleteItem(word: Word) {
                lifecycleScope.launch {
                    mUserViewModel.deleteWord(word)
                }
            }
        })

        binding.recyclerView4.adapter= adapter

        binding.recyclerView4.layoutManager = LinearLayoutManager(binding.root.context)
        (binding.recyclerView4.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        mUserViewModel.getWordByIdList(mUserViewModel.currentItem).observe(activity as LifecycleOwner, { words -> adapter.setData(words) })
    }

    companion object {
        @JvmStatic
        fun newInstance() = Fragment4()
    }
}