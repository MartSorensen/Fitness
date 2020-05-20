package com.example.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class WorkoutFragment extends Fragment {
    private static final String TAG = "WorkoutFragment";
    public static final int ADD_NOTE_REQUEST = 1;
    public static final int EDIT_NOTE_REQUEST = 2;

    private Button btnNavHome;

    private NoteViewModel noteViewModel;

    private RecyclerView mRecyclerView;
    private GoalsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static ArrayList<GoalsItems> workoutsList = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout, container, false);
        btnNavHome = (Button) view.findViewById(R.id.btnNavHome);
       // Button addNewWorkoutBtn = (Button)view.findViewById(R.id.addNewWorkoutButton);
       // buildRecyclerView();

       FloatingActionButton buttonAddNote = view.findViewById(R.id.button_add_note);
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddEditNoteActivity.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
            }
        });
        //Database recyclerView display
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_workout);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setHasFixedSize(true);

        final NoteAdapter adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            //will only get called if the activity is in the foreground and will get destroyed after usage
            @Override
            public void onChanged(List<Note> notes) {
                //update view
                adapter.setNotes(notes);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override   //used for drag and drop
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                noteViewModel.delete(adapter.getNoteAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getContext(),"Note deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new NoteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Note note) {
                Intent intent = new Intent(getActivity(), AddEditNoteActivity.class);
                intent.putExtra(AddEditNoteActivity.EXTRA_ID, note.getId());
                intent.putExtra(AddEditNoteActivity.EXTRA_TITLE, note.getTitle());
                intent.putExtra(AddEditNoteActivity.EXTRA_WEIGHT, note.getWeight());
                intent.putExtra(AddEditNoteActivity.EXTRA_REPETITIONS, note.getRepetitions());
                startActivityForResult(intent, EDIT_NOTE_REQUEST);
            }
        });

     /*   addNewWorkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getActivity(), AddNewWorkout.class);
                startActivity(startIntent);

            } */
     /*   });
        Button clearWorkoutsBtn = (Button)view.findViewById(R.id.clearWorkouts);
        clearWorkoutsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workoutsList.clear();
                mAdapter.notifyDataSetChanged();

            }
        }); */

        btnNavHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPager(0);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddEditNoteActivity.EXTRA_TITLE);
            String weight = data.getStringExtra(AddEditNoteActivity.EXTRA_WEIGHT);
            int repetitions = data.getIntExtra(AddEditNoteActivity.EXTRA_REPETITIONS, 1);

            Note note = new Note(title, weight, repetitions);
            noteViewModel.insert(note);

            Toast.makeText(getContext(), "Note saved", Toast.LENGTH_SHORT).show();
        }  else if(requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK){
            int id = data.getIntExtra(AddEditNoteActivity.EXTRA_ID, -1);

            if (id == -1) {
                Toast.makeText(getContext(), "Note Can't be updated", Toast.LENGTH_SHORT).show();
            }

            String title = data.getStringExtra(AddEditNoteActivity.EXTRA_TITLE);
            String weight = data.getStringExtra(AddEditNoteActivity.EXTRA_WEIGHT);
            int repetitions = data.getIntExtra(AddEditNoteActivity.EXTRA_REPETITIONS, 1);

            Note note = new Note(title, weight, repetitions);
            note.setId(id);
            noteViewModel.update(note);
            Toast.makeText(getContext(), "Note updated", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getContext(), "Note not saved", Toast.LENGTH_SHORT).show();
        }
    }


    /*public void buildRecyclerView(){
        mRecyclerView = getActivity().findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(mRecyclerView.getContext());
        mAdapter = new GoalsAdapter(workoutsList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new GoalsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                workoutsList.get(position);
            }

            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }


        });
    }
    public void removeItem(int position){
        workoutsList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }*/
}

