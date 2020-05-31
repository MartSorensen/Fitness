package com.example.fitnessapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.WorkoutHolder> {
    private List<Workout> workouts = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public WorkoutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.workout_item, parent, false);
        return new WorkoutHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutHolder holder, int position) {
        Workout currentWorkout = workouts.get(position);
        holder.textViewTitle.setText(currentWorkout.getTitle());
        holder.textViewWeight.setText(currentWorkout.getWeight());
        holder.textViewRepetitions.setText(String.valueOf(currentWorkout.getRepetitions()));
    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }

    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
        notifyDataSetChanged();
    }

    public Workout getWorkoutAt(int position) {
        return workouts.get(position);
    }


    class WorkoutHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewWeight;
        private TextView textViewRepetitions;


        public WorkoutHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewWeight = itemView.findViewById(R.id.text_view_weight);
            textViewRepetitions = itemView.findViewById(R.id.text_view_repetitions);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int posistion = getAdapterPosition();
                    if (listener != null && posistion != RecyclerView.NO_POSITION){
                        listener.onItemClick(workouts.get(posistion));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Workout workout);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}

