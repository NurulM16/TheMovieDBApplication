package com.gemslight.themoviedb.fragment.movie_detail_fragment

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.gemslight.common.base.BaseFragment
import com.gemslight.themoviedb.R
import com.gemslight.themoviedb.databinding.LayoutMovieDetailFragmentBinding
import com.gemslight.themoviedb.paging_state_adapter.PagingStateAdapter
import com.gemslight.themoviedb.view_model.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailFragment: BaseFragment<MovieDetailViewModel, LayoutMovieDetailFragmentBinding>() {
    override val vm: MovieDetailViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.layout_movie_detail_fragment
    val adapter = MovieReviewPagingAdapter()
    val loadStateAdapter = PagingStateAdapter{
        adapter.retry()
    }
    val movieDetailsArgs:MovieDetailFragmentArgs by navArgs()


    override fun initBinding(binding: LayoutMovieDetailFragmentBinding) {
        super.initBinding(binding)
        observeLiveData()
    }
}