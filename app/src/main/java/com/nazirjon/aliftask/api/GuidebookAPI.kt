package com.nazirjon.aliftask.api

import com.nazirjon.aliftask.data.models.GuidesResponse
import retrofit2.Call
import retrofit2.http.GET

interface GuidebookAPI {

    @GET("service/v2/upcomingGuides")
    fun getUpcomingGuides(): Call<GuidesResponse>
}