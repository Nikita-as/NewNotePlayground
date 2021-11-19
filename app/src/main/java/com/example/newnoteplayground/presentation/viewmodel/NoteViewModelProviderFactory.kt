package com.example.newnoteplayground.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newnoteplayground.domain.repository.NoteRepository

class NoteViewModelProviderFactory(
    private val app: Application,
    private val noteRepository: NoteRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NoteViewModel(app, noteRepository) as T
    }
}