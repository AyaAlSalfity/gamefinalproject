package com.example.gamefinalproject1;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {UserTable.class,LevelTable.class , PuzzleTaple.class , PatternTable.class}, version = 1, exportSchema = false)
public abstract class RoomBase extends RoomDatabase {

    public abstract LevelDao levelDao();
    public abstract PuzzelDao questionDao();
    public abstract PatternDao patternDao();
    public abstract UserDao userDao();

    private static volatile RoomBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static RoomBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RoomBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    RoomBase.class, "game_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
