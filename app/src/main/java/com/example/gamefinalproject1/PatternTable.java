package com.example.gamefinalproject1;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PatternTable {
    @PrimaryKey(autoGenerate = true)
    int patternId ;
    String patternName ;

    public int id ;


    public int getPatternId() {
        return patternId;
    }

    public void setPatternId(int patternId) {
        this.patternId = patternId;
    }

    public String getPatternName() {
        return patternName;
    }

    public void setPatternName(String patternName) {
        this.patternName = patternName;
    }
}
