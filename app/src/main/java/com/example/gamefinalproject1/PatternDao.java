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
public interface PatternDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPatternData(PatternTable pattern);


    @Query("select * from PatternTable")
    LiveData<List<PatternTable>> getALlPattern();

    @Update
    void updatePattern(PatternTable pattern);
    @Delete
    void deletePattern(PatternTable pattern);

}
