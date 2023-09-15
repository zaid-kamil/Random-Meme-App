package com.example.randommeme.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Meme(
    @SerialName("postLink") var postLink: String? = null,
    var subreddit: String? = null,
    var title: String? = null,
    var url: String? = null,
    var nsfw: Boolean? = null,
    var spoiler: Boolean? = null,
    var author: String? = null,
    var ups: Int? = null,
    var preview: ArrayList<String> = arrayListOf()
)
