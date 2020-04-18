package com.example.myapplication12;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.myapplication12.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MainActivityClickHandler clickHandler;
    private ActivityMainBinding binding;
    private RecyclerView recyclerView;
    private NoteDatabase db;
    private ArrayList<Note> notes;
    private NoteAdapter adapter;

    public static final int NEW_NOTE_REQUEST_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        clickHandler = new MainActivityClickHandler(this);
        binding.setClickHandler(clickHandler);
        recyclerView = binding.recycler;
        db = Room.databaseBuilder(this,NoteDatabase.class,"noteDB").build();
        adapter = new NoteAdapter();
        loadData();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }

    public class MainActivityClickHandler{
        Context context ;

        public MainActivityClickHandler(Context context) {
            this.context = context;
        }

        public void addNoteClick(View view){
            Intent intent = new Intent(MainActivity.this,AddNote.class);
            Log.e("click","click");
            startActivityForResult(intent,NEW_NOTE_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_NOTE_REQUEST_CODE && resultCode ==RESULT_OK){
            assert data != null;
            String title = data.getStringExtra("title");
            String desc = data.getStringExtra("desc");

            Note note = new Note();
            note.setTitle(title);
            note.setDescription(desc);

            addNote(note);
        }
    }

    private void addNote(Note note){
        new AddNoteAsyncTask().execute(note);
    }

    private class AddNoteAsyncTask extends AsyncTask<Note,Void,Void>{
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            loadData();
        }

        @Override
        protected Void doInBackground(Note... notes) {
            db.dao().addNote(notes[0]);
            return null;
        }
    }

    private void loadData(){
        new GetAllNoteAsyncTask().execute();
    }

    private class GetAllNoteAsyncTask extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.setNotes(notes);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            notes= (ArrayList<Note>) db.dao().getAllNote();
            return null;
        }
    }
}
