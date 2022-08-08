package com.gemslight.common.entity.discover_movie


import com.google.gson.annotations.SerializedName

data class DiscoverMovieResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val discoverMovies: List<DiscoverMovie>
)