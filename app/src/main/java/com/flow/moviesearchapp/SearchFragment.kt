package com.flow.moviesearchapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val searchViewModel: SearchViewModel by viewModels()

    val args: SearchFragmentArgs by navArgs()

    private val searchAdapter: SearchAdapter by lazy {
        SearchAdapter(
            itemClickListener = {
                Toast.makeText(requireContext(), "Click!", Toast.LENGTH_SHORT).show()
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

        val searchData = args.searchData?.title
        if (searchData != null) {
            searchMovie(searchData)
        }

        initRecyclerView()

        binding.btnSearch.setOnClickListener {

            val et = binding.etSearch.text.toString()
            val data = SearchUiModel(null, et)
            searchViewModel.insertSearch(data)

            lifecycleScope.launch(Dispatchers.Main) {
                searchMovie(et)
                observeMovieList()
            }

        }

        binding.btnSearchList.setOnClickListener {
            val action = SearchFragmentDirections.actionSearchFragmentToSearchListFragment()
            findNavController().navigate(action.actionId)
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
            searchViewModel.searchMovieStateFlow.collect { state ->
                when(state) {
                    is SearchMovieState.Empty -> {
                        Toast.makeText(requireContext(), "검색결과가 없습니다", Toast.LENGTH_SHORT).show()
                        // TODO 화면에 표시 해주기
                    }
                    is SearchMovieState.Loading -> {
                        binding.pbSearch.isVisible = true
                        binding.rvSearch.isVisible = false
                    }
                    is SearchMovieState.Success -> {
                        binding.pbSearch.isVisible = false
                        binding.rvSearch.isVisible = true
                        state.data.collect {
                            searchAdapter.submitData(viewLifecycleOwner.lifecycle, it)
                            binding.rvSearch.adapter = searchAdapter
                        }
                    }
                    is SearchMovieState.Failed -> {
                        Toast.makeText(requireContext(), "에러발생", Toast.LENGTH_SHORT).show()
                        // 에러 로그 찍어주기
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