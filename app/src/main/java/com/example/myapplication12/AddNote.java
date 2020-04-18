package com.example.myapplication12;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication12.databinding.ActivityAddNoteBinding;

public class AddNote extends AppCompatActivity {

    private ActivityAddNoteBinding binding;
    private AddNoteClickHandler clickHandler;
    Note note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_note);
        note = new Note();
        binding.setNote(note);
        clickHandler = new AddNoteClickHandler(this);
        binding.setClickHandlerSave(clickHandler);

    }

    public class AddNoteClickHandler{
        Context context;

        public AddNoteClickHandler(Context context) {
            this.context = context;
            String name = "amir" ;
        }

        public void saveNote(View view){

            if (note.getTitle() == null){
                Toast.makeText(AddNote.this,"برای ذخیره فلیدها را پر کنید!",Toast.LENGTH_LONG).show();
            }else {
                Intent intent = new Intent();
                intent.putExtra("title",note.getTitle());
                intent.putExtra("desc",note.getDescription());
                setResult(RESULT_OK,intent);
                finish();
            }

        }
    }
}
