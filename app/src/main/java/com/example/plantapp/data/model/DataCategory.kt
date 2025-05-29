package com.example.plantapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataCategory(
    val id: Int,
    val name: String,
    val title: String,
    val rank: Int,
    val image: DataCategoryImage
) : Parcelable

@Parcelize
data class DataCategoryImage(
    val id: Int,
    val name: String,
    val url: String
) : Parcelable

data class DataCategoryResponse(
    val data: List<DataCategory>
)