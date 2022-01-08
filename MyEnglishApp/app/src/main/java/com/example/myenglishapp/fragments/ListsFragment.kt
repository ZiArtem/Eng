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
import com.example.myenglishapp.ListWordActionListener
import com.example.myenglishapp.ListWordAdapter
import com.example.myenglishapp.databinding.Fragment3Binding
import com.example.myenglishapp.entities.ListWords
import com.example.myenglishapp.entities.Word
import com.example.myenglishapp.viewModel.PrViewModel
import kotlinx.coroutines.launch

class ListsFragment : Fragment() {
    private lateinit var binding: Fragment3Binding
    private lateinit var adapter: ListWordAdapter
    private val mUserViewModel: PrViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = Fragment3Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = ListWordAdapter(object : ListWordActionListener {
            override fun deleteItem(wordlist: ListWords) {
                lifecycleScope.launch {
                    mUserViewModel.deleteListWords(wordlist)
                }
            }
        })

        binding.recyclerViewLists.adapter= adapter

        binding.recyclerViewLists.layoutManager = LinearLayoutManager(binding.root.context)
        (binding.recyclerViewLists.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false

        mUserViewModel.readAllListWords().observe(activity as LifecycleOwner, { wordlist -> adapter.setData( wordlist) })
    }

    companion object {
        @JvmStatic
        fun newInstance() = ListsFragment()
    }
}