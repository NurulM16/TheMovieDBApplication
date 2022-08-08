package com.gemslight.themoviedb.fragment.genre_fragment

import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import com.gemslight.common.base.ResponseError
import com.gemslight.common.base.ResponseLoading
import com.gemslight.common.base.ResponseSuccess

fun GenreFragment.observeLiveData() = with(vm) {
    binding.rvGenre.adapter = adapter
    selectionTracker = vm.selectionTracker?.let {
        createTracker().apply {
            it.selection.forEach {
                this.select(it)
            }
        }
    } ?: run {
        createTracker()
    }

    genreData.observe(this@observeLiveData) {
        when (it) {
            is ResponseSuccess -> {
                binding.retryButton.visibility = View.GONE
                binding.rvGenre.visibility = View.VISIBLE
                binding.loadingContainer.visibility = View.GONE
                it.data?.orEmpty()?.let { it1 ->
                    adapter.submitData(it1)
                }
            }
            is ResponseLoading -> {
                binding.retryButton.visibility = View.GONE
                binding.rvGenre.visibility = View.GONE
                binding.loadingContainer.visibility = View.VISIBLE
            }
            is ResponseError -> {
                binding.retryButton.visibility = View.VISIBLE
                binding.rvGenre.visibility = View.GONE
                binding.loadingContainer.visibility = View.GONE
                binding.retryButton.setOnClickListener {
                    getAllGenres()
                }
            }
        }
    }
    binding.fabToMovie.setOnClickListener {
        findNavController().navigate(
            GenreFragmentDirections.genreToDiscoverMovie(
                vm.selectionTracker?.selection?.toMutableList().orEmpty()
                    .joinToString(separator = ",")
            )
        )
    }
}

private fun GenreFragment.createTracker() =
    SelectionTracker.Builder<Long>(
        this::class.java.name,
        binding.rvGenre,
        adapter.getItemKeyProvider(),
        GenreItemDetailsLookUp(binding.rvGenre),
        StorageStrategy.createLongStorage()
    ).withOnItemActivatedListener { item, _ ->
        item.selectionKey?.let {
            vm.selectionTracker?.select(it)
        }
        true
    }.withSelectionPredicate(SelectionPredicates.createSelectAnything()).build()