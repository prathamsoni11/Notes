package com.example.notes;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes_table")
public class Note {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "text")
    String text;

    public Note(String text){
        this.text = text;
    }
}
