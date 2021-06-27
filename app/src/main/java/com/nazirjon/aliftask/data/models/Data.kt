package com.nazirjon.aliftask.data.models


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity (tableName = "data")
@Parcelize
data class Data(
    @PrimaryKey(autoGenerate = false)
    val url: String,
    val name: String,
    val icon: String,
    val startDate: String,
    val endDate: String,
    val objType: String,
    val loginRequired: Boolean,
): Parcelable