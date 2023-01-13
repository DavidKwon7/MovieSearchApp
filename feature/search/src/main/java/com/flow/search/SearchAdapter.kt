package com.flow.search

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.flow.data.model.MovieSearchEntityModel
import com.flow.domain.entity.Item
import com.flow.search.databinding.ItemSearchBinding

class SearchAdapter(

): PagingDataAdapter<Item, SearchAdapter.SearchMovieViewHolder>(DIFF_CALLBACK) {

    inner class SearchMovieViewHolder(
        private val binding: ItemSearchBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) = binding.apply {

            Glide.with(root).load(item.image).into(ivMovie)
            tvTitle.text = stripHtml(item.title)
            tvUserRating.text = item.userRating
            tvPubDate.text = item.pubDate
        }
    }

    override fun onBindViewHolder(holder: SearchMovieViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchBinding.inflate(inflater, parent, false)
        return SearchMovieViewHolder(binding)
    }

    fun stripHtml(value: String?) =
        Html.fromHtml(value, Html.FROM_HTML_MODE_LEGACY).toString()

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean =
                oldItem == newItem
        }
    }
}