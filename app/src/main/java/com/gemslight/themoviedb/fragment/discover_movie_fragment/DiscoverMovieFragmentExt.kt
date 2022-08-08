package com.gemslight.themoviedb.fragment.discover_movie_fragment

import android.view.View
import androidx.paging.LoadState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun DiscoverMovieFragment.observeLiveData() = with(vm){
    getDiscoverMovie(discoverMovieArgs.genre)
    adapter.addLoadStateListener{
        if (it.refresh is LoadState.Error && adapter.itemCount == 0){
            binding.retryButton.visibility = View.VISIBLE
            binding.rvDiscoverMovie.visibility = View.GONE
            binding.loadingContainer.visibility = View.GONE
        } else if (it.refresh is LoadState.Loading && adapter.itemCount == 0){
            binding.retryButton.visibility = View.GONE
            binding.rvDiscoverMovie.visibility = View.GONE
            binding.loadingContainer.visibility = View.VISIBLE
        } else if (it.refresh is LoadState.NotLoading){
            binding.retryButton.visibility = View.GONE
            binding.rvDiscoverMovie.visibility = View.VISIBLE
            binding.loadingContainer.visibility = View.GONE
        }
    }
    binding.rvDiscoverMovie.adapter = adapter.withLoadStateFooter(loadStateAdapter)
    discoverMovieDataState.observe(this@observeLiveData){
        CoroutineScope(Dispatchers.Main).launch {
            adapter.submitData(it)
        }
    }
    binding.retryButton.setOnClickListener {
        adapter.retry()
    }
}