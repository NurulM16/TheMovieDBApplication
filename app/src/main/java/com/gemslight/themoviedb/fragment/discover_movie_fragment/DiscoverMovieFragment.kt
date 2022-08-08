package com.gemslight.themoviedb.fragment.discover_movie_fragment

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.gemslight.common.base.BaseFragment
import com.gemslight.themoviedb.R
import com.gemslight.themoviedb.databinding.LayoutDiscoverMovieFragmentBinding
import com.gemslight.themoviedb.fragment.movie_detail_fragment.MovieDetailFragmentDirections
import com.gemslight.themoviedb.paging_state_adapter.PagingStateAdapter
import com.gemslight.themoviedb.view_model.DiscoverMovieViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DiscoverMovieFragment :
    BaseFragment<DiscoverMovieViewModel, LayoutDiscoverMovieFragmentBinding>() {
    override val vm: DiscoverMovieViewModel by viewModels()
    val discoverMovieArgs: DiscoverMovieFragmentArgs by navArgs()
    override val layoutResourceId: Int = R.layout.layout_discover_movie_fragment
    val adapter = DiscoverMoviePagingAdapter {
        findNavController().navigate(MovieDetailFragmentDirections.discoverMovieToMovieDetail(it))
    }
    val loadStateAdapter = PagingStateAdapter{
        adapter.retry()
    }

    override fun initBinding(binding: LayoutDiscoverMovieFragmentBinding) {
        super.initBinding(binding)
        observeLiveData()
    }
}