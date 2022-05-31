package com.zura.mysuperapp.api.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Resources")
data class Resource (
    @ColumnInfo(name = "Id")
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    @ColumnInfo(name = "Name")
    val name: String,
    @ColumnInfo(name = "Year")
    val year: Int,
    @ColumnInfo(name = "Color")
    val color: String,
    @ColumnInfo(name = "PantoneValue")
    @SerializedName("pantone_value")
    val pantoneValue: String
)