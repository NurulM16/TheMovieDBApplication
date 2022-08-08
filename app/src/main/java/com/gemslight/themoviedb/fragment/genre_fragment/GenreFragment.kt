package com.gemslight.themoviedb.fragment.genre_fragment

import androidx.fragment.app.viewModels
import com.gemslight.common.base.BaseFragment
import com.gemslight.themoviedb.R
import com.gemslight.themoviedb.databinding.LayoutGenreFragmentBinding
import com.gemslight.themoviedb.view_model.GenreViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenreFragment : BaseFragment<GenreViewModel, LayoutGenreFragmentBinding>(){
    override val vm: GenreViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.layout_genre_fragment
    val adapter = GenreAdapter{
        vm.selectionTracker?.isSelected(it) ?: false
    }

    override fun initBinding(binding: LayoutGenreFragmentBinding) {
        super.initBinding(binding)
        observeLiveData()
    }
}