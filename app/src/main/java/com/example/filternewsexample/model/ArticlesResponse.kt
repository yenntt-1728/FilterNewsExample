package com.example.filternewsexample.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArticlesResponse(
    @SerializedName("status")
    val status : String? = null,
    @SerializedName("totalResults")
    val totalResults: Int? = null,
    @SerializedName("articles")
    val articles : List<News> = mutableListOf()
) : Parcelable