package com.example.plantapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DomainCategory(
    val id: Int,
    val name: String,
    val title: String,
    val rank: Int,
    val image: DomainCategoryImage
) : Parcelable

@Parcelize
data class DomainCategoryImage(
    val id: Int,
    val name: String,
    val url: String
) : Parcelable