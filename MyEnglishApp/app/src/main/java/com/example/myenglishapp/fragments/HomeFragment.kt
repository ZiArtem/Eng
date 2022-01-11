package com.example.myenglishapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.myenglishapp.SwipeToDelete
import com.example.myenglishapp.viewModel.PrViewModel
import com.example.myenglishapp.entities.Word
import com.example.myenglishapp.WordActionListener
import com.example.myenglishapp.WordAdapter
import com.example.myenglishapp.databinding.HomeFragmentBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var binding: HomeFragmentBinding
    private lateinit var adapter: WordAdapter
    private val mUserViewModel: PrViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(inflater)
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

        binding.recyclerViewWord.adapter = adapter

        binding.recyclerViewWord.layoutManager = LinearLayoutManager(binding.root.context)
        (binding.recyclerViewWord.itemAnimator as SimpleItemAnimator).supportsChangeAnimations =
            false
        mUserViewModel.readAllWord()
            .observe(activity as LifecycleOwner, { words -> adapter.setData(words) })

        binding.button.setOnClickListener {
            lifecycleScope.launch {
                mUserViewModel.addWord(Word(0, "almost", "почти", -1))
                mUserViewModel.addWord(Word(0, "aware", "осведомленный", -1))
                mUserViewModel.addWord(Word(0, "certain", "определенный", -1))
                mUserViewModel.addWord(Word(0, "complain", "жаловаться", -1))
                mUserViewModel.addWord(Word(0, "demand", "требовать", -1))
                mUserViewModel.addWord(Word(0, "matter", "иметь значение", -1))
                mUserViewModel.addWord(Word(0, "terrible", "ужасный, страшный", -1))
                mUserViewModel.addWord(Word(0, "useless", "бесполезный", -1))
                mUserViewModel.addWord(Word(0, "annoying", "раздражающий", -1))
                mUserViewModel.addWord(Word(0, "believe", "верить полагать", -1))
                mUserViewModel.addWord(Word(0, "especially", "особенно", -1))
                mUserViewModel.addWord(Word(0, "reliable", "надежный", -1))
            }
        }
        initSwipeToDelete()
    }

    private fun initSwipeToDelete() {
        val onItemSwipedToDelete = { positionForRemove: Int ->
            Thread(Runnable {

                val a = mUserViewModel.readAllWord1()
                mUserViewModel.deleteWord(a[positionForRemove])
            }).start()

        }

        val swipeToDeleteCallback = SwipeToDelete(onItemSwipedToDelete)
        ItemTouchHelper(swipeToDeleteCallback).attachToRecyclerView(binding.recyclerViewWord)
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}