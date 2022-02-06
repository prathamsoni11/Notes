package com.example.notes;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    NoteRepository repository;

    LiveData<List<Note>> allNotes;

    public NoteViewModel( Application application) {
        super(application);
        repository = new NoteRepository(application);
        allNotes = repository.allNotes;
    }

    public void deleteNote(Note note){
        repository.delete(note);
    }

    public void insertNote(Note note){
        repository.insert(note);
    }

}
