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

@AndroidEntryPoint
class SearchListFragment : Fragment() {

    private var _binding: FragmentSearchListBinding? = null
    private val binding get() = _binding!!
    private val searchListViewModel: SearchListViewModel by viewModels()
    private val searchListAdapter: SearchListAdapter by lazy {
        SearchListAdapter(
            itemClickListener = {
                Toast.makeText(requireContext(), "클릭", Toast.LENGTH_SHORT).show()
                //val action = SearchListFragmentDirections.actionSearchListFragmentToSearchFragment()
                //findNavController().navigate(action.actionId)
                navigateWithArgs(
                    SearchListFragmentDirections.actionSearchListFragmentToSearchFragment(
                        it,
                    )
                )
            }
        )
    }

    private val data = mutableListOf<SearchUiModel>()

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


        //searchListAdapter.deleteData()
    }

    private fun initRecyclerView() {
        binding.rvSearchList.apply {
            adapter = searchListAdapter
        }
    }

    /*@SuppressLint("NotifyDataSetChanged")
    private fun deleteData() {
        if (data.size > 11) {
            data.removeAt(0)
            searchListAdapter.notifyDataSetChanged()
        }
    }*/



    @SuppressLint("NotifyDataSetChanged")
    private fun observeRecentSearch() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchListViewModel.searchStateFlow.collectLatest { state ->
                    when(state) {
                        is SearchState.Empty -> {

                        }
                        is SearchState.Loading -> {
                            binding.pbSearch.isVisible = true
                        }
                        is SearchState.Success -> {
                            binding.pbSearch.isVisible = false
                            val data = state.data
                            /*//searchListAdapter.notifyDataSetChanged()
                            searchListAdapter.notifyItemChanged(searchListAdapter.itemCount -1 ,data)*/
                            searchListAdapter.submitList(data)
                        }
                        is SearchState.Failed -> {

                        }

                    }
                }
            }
        }
    }
}