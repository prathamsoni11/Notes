package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements INotesRVAdapter {

    private NoteViewModel viewModel;
    RecyclerView recyclerView;
    TextView input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        NotesRVAdapter adapter = new NotesRVAdapter(this,this);
        recyclerView.setAdapter(adapter);


        viewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(NoteViewModel.class);
        viewModel.allNotes.observe(this,list -> adapter.updateList(list));
    }

    @Override
    public void onItemClicked(Note note) {
        viewModel.deleteNote(note);
    }

    public void submitData(View view) {
        input = findViewById(R.id.input);
        String str = input.getText().toString();
        if (!str.isEmpty()){
            Note noteText = new Note(str);
            viewModel.insertNote(noteText);
        }
    }
}