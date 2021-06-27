package com.nazirjon.aliftask.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nazirjon.aliftask.data.models.Data

@Dao
interface GuidebookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(guides : List<Data>)

    @Query("SELECT * FROM data")
    fun getData() : LiveData<List<Data>>

}