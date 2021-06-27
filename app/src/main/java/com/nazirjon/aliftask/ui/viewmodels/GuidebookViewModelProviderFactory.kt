package com.nazirjon.aliftask.ui.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nazirjon.aliftask.repository.GuidebookRepository

class GuidebookViewModelProviderFactory : ViewModelProvider.Factory {

    val app: Application
    val repository: GuidebookRepository

    constructor(app: Application, repository: GuidebookRepository) {
        this.app = app
        this.repository = repository
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GuidebookViewModel(app, repository) as T
    }
}