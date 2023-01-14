package com.flow.moviesearchapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.flow.moviesearchapp.databinding.FragmentSearchBinding
import com.flow.search.SearchAdapter
import com.flow.search.SearchMovieState
import com.flow.search.SearchViewModel
import com.flow.search.model.SearchUiModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val searchViewModel: SearchViewModel by viewModels()

    val args: SearchFragmentArgs by navArgs()

    private val searchAdapter: SearchAdapter by lazy {
        SearchAdapter(
            itemClickListener = {
                navigateWithArgs(
                    SearchFragmentDirections.actionSearchFragmentToWebviewFragment(
                        it
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
        _binding = FragmentSearchBinding.inflate(
            inflater, container, false
        )
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        clickSearch()
        clickSearchList()
        searchArgs()
        observeMovieList()
    }

    private fun clickSearch() {
        binding.btnSearch.setOnClickListener {

            val et = binding.etSearch.text.toString()
            val data = SearchUiModel(null, et)
            searchViewModel.insertSearch(data)

            lifecycleScope.launch(Dispatchers.Main) {
                searchMovie(et)
                observeMovieList()
            }
        }
    }

    private fun clickSearchList() {
        binding.btnSearchList.setOnClickListener {
            val action = SearchFragmentDirections.actionSearchFragmentToSearchListFragment()
            findNavController().navigate(action.actionId)
        }
    }

    private fun initRecyclerView() {
        binding.rvSearch.apply {
            adapter = searchAdapter
        }
    }

    private fun searchMovie(queue: String) {
        searchViewModel.searchMovie(queue)
    }

    private fun searchArgs() {
        val searchData = args.searchData?.title
        if (searchData != null) {
            searchMovie(searchData)
        }
    }

    private fun observeMovieList() {
        lifecycleScope.launch(Dispatchers.Main) {
            searchViewModel.searchMovieStateFlow.collect { state ->
                when (state) {
                    is SearchMovieState.Empty -> {
                        binding.ivEmpty.isVisible = true
                        binding.tvEmpty.isVisible = true
                        binding.pbSearch.isVisible = false
                        binding.rvSearch.isVisible = false
                    }
                    is SearchMovieState.Loading -> {
                        binding.ivEmpty.isVisible = false
                        binding.tvEmpty.isVisible = false
                        binding.pbSearch.isVisible = true
                        binding.rvSearch.isVisible = false
                    }
                    is SearchMovieState.Success -> {
                        binding.ivEmpty.isVisible = false
                        binding.tvEmpty.isVisible = false
                        binding.pbSearch.isVisible = false
                        binding.rvSearch.isVisible = true

                        state.data.collect {
                            searchAdapter.submitData(viewLifecycleOwner.lifecycle, it)
                            binding.rvSearch.adapter = searchAdapter
                        }
                    }
                    is SearchMovieState.Failed -> {
                        Timber.e("에러 발생: ${state.message}")
                        state.message.printStackTrace()
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