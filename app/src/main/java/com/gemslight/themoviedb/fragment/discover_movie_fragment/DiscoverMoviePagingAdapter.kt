package com.gemslight.themoviedb.fragment.discover_movie_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gemslight.common.entity.discover_movie.DiscoverMovie
import com.gemslight.themoviedb.databinding.LayoutDiscoverMovieItemBinding

class DiscoverMoviePagingAdapter(
    val selectedMovie: (Int) -> Unit
) : PagingDataAdapter<DiscoverMovie, DiscoverMoviePagingAdapter.DiscoverMovieViewHolder>(differ) {

    class DiscoverMovieViewHolder(val binding: LayoutDiscoverMovieItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: DiscoverMovieViewHolder, position: Int) {
        holder.binding.data = getItem(position)
        holder.binding.root.setOnClickListener {
            getItem(position)?.let { it1 ->
                selectedMovie(it1.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverMovieViewHolder =
        DiscoverMovieViewHolder(
            LayoutDiscoverMovieItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    companion object {
        val differ = object : DiffUtil.ItemCallback<DiscoverMovie>() {
            override fun areItemsTheSame(oldItem: DiscoverMovie, newItem: DiscoverMovie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: DiscoverMovie,
                newItem: DiscoverMovie
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}