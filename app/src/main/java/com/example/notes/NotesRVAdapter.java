package com.example.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesRVAdapter extends RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder> {

    ArrayList<Note> allNotes = new ArrayList<>();
    Context context;
    INotesRVAdapter listener;

    public NotesRVAdapter(Context context, INotesRVAdapter listener){
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NoteViewHolder viewHolder = new NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note, parent, false));
        viewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(allNotes.get(viewHolder.getAdapterPosition()));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NotesRVAdapter.NoteViewHolder holder, int position) {
        holder.textView.setText(allNotes.get(position).text);
    }

    @Override
    public int getItemCount() {
        return allNotes.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView deleteButton;

        public NoteViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
            deleteButton = itemView.findViewById(R.id.deleteButton);

        }
    }

    public void updateList(List<Note> newList){
        allNotes.clear();
        allNotes.addAll(newList);

        notifyDataSetChanged();
    }
}

interface INotesRVAdapter{
    void onItemClicked(Note note);
}
