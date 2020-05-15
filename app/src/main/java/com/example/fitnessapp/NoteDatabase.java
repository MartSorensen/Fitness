package com.example.fitnessapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = Note.class, version = 3, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {

    //Used to access singleton database
    private static NoteDatabase instance;

    public abstract NoteDao noteDao();

    // synchronized avoids multiple treads from accessing the method aka makes sure we only gets one instance of the database
    public static  synchronized NoteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "node_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    //The following methods are used for populating the database on creation
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
           // new PopulateDbAsyncTask(instance).execute();
        }
    };

  /*  private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;

        private PopulateDbAsyncTask(NoteDatabase db) {
            noteDao = db.noteDao();
        }

    @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Bench", "50 kg", 1));
            noteDao.insert(new Note("Bicep curls", "70 kg", 2));
            noteDao.insert(new Note("Death lift", "30 kg", 3));
            return null;
        }
    }*/
}
