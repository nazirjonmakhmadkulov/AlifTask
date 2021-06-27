package com.nazirjon.aliftask.ui.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.nazirjon.aliftask.data.models.Data
import com.nazirjon.aliftask.data.models.GuidesResponse
import com.nazirjon.aliftask.repository.GuidebookRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GuidebookViewModel(
    app : Application,
    private val repository: GuidebookRepository
) : AndroidViewModel(app) {

    val guidebookResponse: MutableLiveData<GuidesResponse> = MutableLiveData()
    val error : MutableLiveData<String> = MutableLiveData()

    fun getUpcomingGuides() = viewModelScope.launch {
        guidesResponseCall()
    }

    private fun guidesResponseCall() {
        viewModelScope.launch {
            val response = repository.getUpcomingGuides()
            response.enqueue(object: Callback<GuidesResponse> {
                override fun onResponse(
                        call: Call<GuidesResponse>,
                        response: Response<GuidesResponse>
                ) {
                    guidebookResponse.postValue(response.body())
                }

                override fun onFailure(call: Call<GuidesResponse>, t: Throwable) {
                    println(t.message)
                    error.postValue(t.message)
                }

            })
        }
    }

    fun saveGuidesToDb(guides: List<Data>) = viewModelScope.launch {
        repository.upsert(guides)
    }

}