package com.example.gamefinalproject1;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LevelTable {
        @PrimaryKey(autoGenerate = true)
        int levelNo ;
        int unlockPoints ;

    public LevelTable() {
    }

    public LevelTable(int levelNo, int unlockPoints) {
        this.levelNo = levelNo;
        this.unlockPoints = unlockPoints;
    }

    public int getLevelNo() {
            return levelNo;
        }

        public void setLevelNo(int levelNo) {
            this.levelNo = levelNo;
        }

        public int getUnlockPoints() {
            return unlockPoints;
        }

        public void setUnlockPoints(int unlockPoints) {
            this.unlockPoints = unlockPoints;
        }
    }

