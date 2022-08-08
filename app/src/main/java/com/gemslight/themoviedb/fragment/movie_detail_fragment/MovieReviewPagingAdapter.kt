package com.gemslight.themoviedb.fragment.movie_detail_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gemslight.common.entity.movie.movie_review.Review
import com.gemslight.themoviedb.databinding.LayoutMovieReviewItemBinding


class MovieReviewPagingAdapter : PagingDataAdapter<Review, MovieReviewPagingAdapter.MovieReviewViewHolder>(
    differ
){


    class MovieReviewViewHolder(private val binding: LayoutMovieReviewItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(review: Review?){
            binding.data = review
        }

    }

    override fun onBindViewHolder(holder: MovieReviewViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieReviewViewHolder {
       return MovieReviewViewHolder(
           LayoutMovieReviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
       )
    }

    companion object{
        val differ = object : DiffUtil.ItemCallback<Review>(){
            override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
                return oldItem == newItem
            }
        }
    }
}