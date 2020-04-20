package com.example.filternewsexample.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class EntityNews(
    @NonNull
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "title")
    val title : String ,
    @ColumnInfo(name = "description")
    val description : String?,
    @ColumnInfo(name = "image_url")
    val imageUrl : String?
)