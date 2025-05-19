package com.example.plantapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val id: Int,
    val name: String,
    val title: String,
    val rank: Int,
    val image: CategoryImage
) : Parcelable

@Parcelize
data class CategoryImage(
    val id: Int,
    val name: String,
    val url: String
) : Parcelable

data class CategoryResponse(
    val data: List<Category>
)