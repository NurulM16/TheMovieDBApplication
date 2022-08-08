package com.gemslight.themoviedb.paging_state_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gemslight.themoviedb.databinding.LayoutItemStateBinding

class PagingStateAdapter(val onClick:()->Unit) : LoadStateAdapter<PagingStateAdapter.PagingStateViewHolder>() {
    class PagingStateViewHolder(
        private val binding: LayoutItemStateBinding,
        val onClick:()->Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            binding.errorContainer.setOnClickListener {
                onClick()
            }
            when (loadState) {
                is LoadState.Error -> {
                    binding.errorContainer.visibility = View.VISIBLE
                    binding.loadingContainer.visibility = View.GONE
                }
                is LoadState.Loading -> {
                    binding.errorContainer.visibility = View.GONE
                    binding.loadingContainer.visibility = View.VISIBLE
                }
                is LoadState.NotLoading -> {
                    binding.errorContainer.visibility = View.GONE
                    binding.loadingContainer.visibility = View.GONE
                }
            }
        }
    }

    override fun onBindViewHolder(holder: PagingStateViewHolder, loadState: LoadState) =
        holder.bind(loadState)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): PagingStateViewHolder = PagingStateViewHolder(
        LayoutItemStateBinding.inflate(LayoutInflater.from(parent.context), parent, false),onClick
    ).apply {
        this.bind(loadState)
    }
}