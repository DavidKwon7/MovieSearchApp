package com.flow.moviesearchapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.flow.moviesearchapp.databinding.FragmentSearchListBinding
import com.flow.searchlist.SearchListAdapter
import com.flow.searchlist.SearchListViewModel
import com.flow.searchlist.SearchState
import com.flow.searchlist.model.SearchUiModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class SearchListFragment : Fragment() {

    private var _binding: FragmentSearchListBinding? = null
    private val binding get() = _binding!!
    private val searchListViewModel: SearchListViewModel by viewModels()
    private val searchListAdapter: SearchListAdapter by lazy {
        SearchListAdapter(
            itemClickListener = {
                navigateWithArgs(
                    SearchListFragmentDirections.actionSearchListFragmentToSearchFragment(
                        it,
                    )
                )
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchListBinding.inflate(
            inflater, container, false
        )
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        observeRecentSearch()
        searchListViewModel.getAllSearch()

    }

    private fun initRecyclerView() {
        binding.rvSearchList.apply {
            adapter = searchListAdapter
        }
    }

    private fun observeRecentSearch() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchListViewModel.searchStateFlow.collectLatest { state ->
                    when (state) {
                        is SearchState.Empty -> {
                            binding.ivEmpty.isVisible = true
                            binding.tvEmpty.isVisible = true
                            binding.pbSearch.isVisible = false
                            binding.rvSearchList.isVisible = false
                        }
                        is SearchState.Loading -> {
                            binding.ivEmpty.isVisible = false
                            binding.tvEmpty.isVisible = false
                            binding.pbSearch.isVisible = true
                            binding.rvSearchList.isVisible = false
                        }
                        is SearchState.Success -> {
                            binding.ivEmpty.isVisible = false
                            binding.tvEmpty.isVisible = false
                            binding.pbSearch.isVisible = false
                            binding.rvSearchList.isVisible = true

                            val data = state.data
                            searchListAdapter.submitList(data)
                        }
                        is SearchState.Failed -> {
                            Timber.e("?????? ??????: ${state.message}")
                            state.message.printStackTrace()
                        }

                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}