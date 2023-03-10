package com.flow.searchlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.flow.searchlist.databinding.ItemSearchListBinding
import com.flow.searchlist.model.SearchUiModel

class SearchListAdapter(
    private val itemClickListener: (SearchUiModel) -> Unit
) : ListAdapter<SearchUiModel, SearchListAdapter.SearchListViewHolder>(DIFF_COMPARATOR) {

    inner class SearchListViewHolder(
        private val binding: ItemSearchListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: SearchUiModel,
            itemClickListener: (SearchUiModel) -> Unit
        ) {
            binding.tvSearch.text = item.title
            binding.root.setOnClickListener {
                itemClickListener.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchListBinding.inflate(inflater, parent, false)
        return SearchListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchListViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it, itemClickListener)
        }
    }

    override fun submitList(list: List<SearchUiModel>?) {
        if ((list?.size ?: 0) > 10) super.submitList(list?.takeLast(10))
        else super.submitList(list)
    }

    companion object {
        private val DIFF_COMPARATOR = object : DiffUtil.ItemCallback<SearchUiModel>() {
            override fun areItemsTheSame(oldItem: SearchUiModel, newItem: SearchUiModel): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(
                oldItem: SearchUiModel,
                newItem: SearchUiModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}