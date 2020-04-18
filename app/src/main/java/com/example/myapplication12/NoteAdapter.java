package com.example.myapplication12;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication12.databinding.NoteItemBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private List<Note> notes;
    LayoutInflater layoutInflater;
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(parent.getContext());
        NoteItemBinding noteItemBinding = DataBindingUtil.inflate(layoutInflater,R.layout.note_item,parent,false);
        return new NoteViewHolder(noteItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.binding.setNote(note);
    }

    @Override
    public int getItemCount() {
        if (notes != null){
            return notes.size();
        }else {}

        return 0;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    protected class NoteViewHolder extends RecyclerView.ViewHolder {
        private NoteItemBinding binding;
        public NoteViewHolder(@NonNull NoteItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
