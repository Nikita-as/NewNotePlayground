package com.example.newnoteplayground.repository

import com.example.newnoteplayground.database.NoteDatabase
import com.example.newnoteplayground.models.Note

class NoteRepository(private val database: NoteDatabase) {

    suspend fun addNote(note: Note) = database.getNoteDao().addNote(note)
    suspend fun updateNote(note: Note) = database.getNoteDao().updateNote(note)
    suspend fun deleteNote(note: Note) = database.getNoteDao().deleteNote(note)

    fun getAllNotes() = database.getNoteDao().getAllNotes()
}