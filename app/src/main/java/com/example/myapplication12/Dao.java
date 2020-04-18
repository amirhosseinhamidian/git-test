package com.example.myapplication12;

import java.util.List;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@androidx.room.Dao
public interface Dao {

    @Insert
    public long addNote(Note note);
    @Query("SELECT * FROM note_table")
    public List<Note> getAllNote();
    @Update
    public void update(Note note);
    @Delete
    public void delete(Note note);
    @Query("SELECT * FROM note_table WHERE id ==:noteId")
    public Note getNote(int noteId);
}
