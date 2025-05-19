package com.example.baseapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val title: String,
    val rank: Int,
    val imageUrl: String
) 