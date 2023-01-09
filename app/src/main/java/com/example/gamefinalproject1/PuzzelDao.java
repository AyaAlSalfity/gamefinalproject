package com.example.gamefinalproject1;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PuzzelDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertQuestionData(PuzzleTaple Data);

    @Query("select * from PuzzleTaple")
    LiveData<List<PuzzleTaple>> getAllQuestions();

    @Query("select * from PuzzleTaple where levelNo = :levelNo")
    LiveData<List<PuzzleTaple>> getAllQuestionsByLevelId(int levelNo);

    @Delete
    void deletePuzzle(PuzzleTaple puzzle);

}
