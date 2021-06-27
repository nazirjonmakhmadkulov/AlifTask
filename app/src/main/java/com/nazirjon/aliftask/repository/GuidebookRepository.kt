package com.nazirjon.aliftask.repository

import com.nazirjon.aliftask.api.RetrofitInstance
import com.nazirjon.aliftask.data.db.GuidebookDatabase
import com.nazirjon.aliftask.data.models.Data

class GuidebookRepository(private val db: GuidebookDatabase) {

    suspend fun getUpcomingGuides() = RetrofitInstance.api.getUpcomingGuides()

    suspend fun upsert(guides: List<Data>) = db.guidebookDao().upsert(guides)

}