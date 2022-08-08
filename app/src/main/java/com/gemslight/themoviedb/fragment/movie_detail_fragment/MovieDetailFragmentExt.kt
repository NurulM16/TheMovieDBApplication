package com.gemslight.themoviedb.fragment.movie_detail_fragment

import android.view.View
import androidx.paging.LoadState
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun MovieDetailFragment.observeLiveData() = with(vm){
    getMovieDetail(movieDetailsArgs.movieId)
    adapter.addLoadStateListener {
        if (it.refresh is LoadState.Error && adapter.itemCount == 0){
            binding.retryButton.visibility = View.VISIBLE
            binding.rvMovieDetail.visibility = View.GONE
            binding.loadingContainer.visibility = View.GONE
            binding.pageMovieDetail.visibility = View.GONE
            binding.retryButton.setOnClickListener {
                getMovieDetail(movieDetailsArgs.movieId)
            }
        }else if (it.refresh is LoadState.Loading && adapter.itemCount == 0){
            binding.retryButton.visibility = View.GONE
            binding.rvMovieDetail.visibility = View.GONE
            binding.loadingContainer.visibility = View.VISIBLE
            binding.pageMovieDetail.visibility = View.GONE
        } else if (it.refresh is LoadState.NotLoading){
            binding.retryButton.visibility = View.GONE
            binding.rvMovieDetail.visibility = View.VISIBLE
            binding.loadingContainer.visibility = View.GONE
            binding.pageMovieDetail.visibility = View.VISIBLE
        }
    }
    binding.rvMovieDetail.adapter = adapter.withLoadStateFooter(loadStateAdapter)
    movieDetailLiveDataState.observe(this@observeLiveData){
        binding.data = it.data
        binding.genre = it.data?.genres?.joinToString (separator = ","){
            it.name
        }
    }
    movieReviewPagingDataState.observe(this@observeLiveData){
        CoroutineScope(Dispatchers.Main).launch {
            adapter.submitData(it)
        }
    }
    lifecycle.addObserver(binding.videoTrailer)
    movieVideoLiveDataState.observe(this@observeLiveData){
        binding.videoTrailer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener(){
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                if (it != null){
                    youTubePlayer.loadVideo(it, 0f)
                }
            }

        })
    }
}