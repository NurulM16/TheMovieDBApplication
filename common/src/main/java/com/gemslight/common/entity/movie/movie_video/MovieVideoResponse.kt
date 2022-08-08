package com.gemslight.common.entity.movie.movie_video


import com.google.gson.annotations.SerializedName

data class MovieVideoResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("results")
    val videos: List<Video>
)