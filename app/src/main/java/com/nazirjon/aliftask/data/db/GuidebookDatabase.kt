package com.nazirjon.aliftask.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nazirjon.aliftask.data.models.Data

@Database(
    entities = [Data::class],
    version = 1
)
abstract class GuidebookDatabase : RoomDatabase() {

    abstract fun guidebookDao(): GuidebookDao

    companion object {
        @Volatile
        private var instance : GuidebookDatabase ?= null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                GuidebookDatabase::class.java,
                "guidebook.db"
            ).build()
    }
}