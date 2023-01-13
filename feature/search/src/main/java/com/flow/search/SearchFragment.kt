package com.flow.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.flow.search.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val searchViewModel: SearchViewModel by viewModels()
    private val searchAdapter: SearchAdapter by lazy { SearchAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        binding.btnSearch.setOnClickListener {
            val et = binding.etSearch.text.toString()
            lifecycleScope.launch(Dispatchers.Main) {
                searchMovie(et)
                observeMovieList()
            }
        }
        observeMovieList()
    }

    private fun initRecyclerView() {
        binding.rvSearch.apply {
            adapter = searchAdapter
        }
    }

    private fun searchMovie(queue: String) {
        searchViewModel.searchMovie(queue)
    }

    private fun observeMovieList() {
        lifecycleScope.launch(Dispatchers.Main) {
            searchViewModel.searchStateFlow.collect { state ->
                when(state) {
                    is SearchState.Empty -> {
                        Toast.makeText(requireContext(), "검색결과가 없습니다", Toast.LENGTH_SHORT).show()
                        // TODO 화면에 표시 해주기
                    }
                    is SearchState.Loading -> {
                        binding.pbSearch.isVisible = true
                        binding.rvSearch.isVisible = false
                    }
                    is SearchState.Success -> {
                        binding.pbSearch.isVisible = false
                        binding.rvSearch.isVisible = true
                        state.data.collect {
                            searchAdapter.submitData(viewLifecycleOwner.lifecycle, it)
                            binding.rvSearch.adapter = searchAdapter
                        }
                    }
                    is SearchState.Failed -> {
                        Toast.makeText(requireContext(), "에러발생", Toast.LENGTH_SHORT).show()
                        // 에러 로그 찍어주기
                    }
                }
            }
        }
    }

    /*override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }*/
}