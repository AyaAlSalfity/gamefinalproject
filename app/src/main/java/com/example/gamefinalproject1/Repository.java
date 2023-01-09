package com.example.gamefinalproject1;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;

import java.util.List;

public class Repository {

    private LevelDao levelDao;
    private PuzzelDao puzzelDao;
    private PatternDao patternDao;
    private UserDao userDao;

    Repository(Application application) {
        RoomBase db = RoomBase.getDatabase(application);
        levelDao = db.levelDao();
        puzzelDao = db.questionDao();
        userDao=db.userDao();
        patternDao = db.patternDao();
    }

    LiveData<List<LevelTable>> getAllLevel(){

        return levelDao.getAllLevel() ;
    }

    void insertLevelData(LevelTable Level){

        RoomBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                levelDao.
                        insertLevelData(Level);
            }
        });
    }

    void deleteLevel(LevelTable level){
        RoomBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                levelDao.deleteLevel(level);
            }
        });
    }

    LiveData<List<PuzzleTaple>> getAllQuestions(){

        return puzzelDao.getAllQuestions();
    }

    LiveData<List<PuzzleTaple>> getAllQuestionsByLevelId(int levelNo){
        return puzzelDao.getAllQuestionsByLevelId(levelNo);
    }

    void insertQuestionData(PuzzleTaple Data){

        RoomBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                puzzelDao.insertQuestionData(Data);
            }
        });
    }
    void deletePuzzle(PuzzleTaple Data){
        RoomBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                puzzelDao.deletePuzzle(Data);
            }
        });
    }
    LiveData<List<UserTable>> getALlUsers(){

        return userDao.getAllUsers();
    }

    void insertUser(UserTable user){
        RoomBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.insertUser(user);
            }
        });
    }

    void updateUser(UserTable user){
        RoomBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.updateUser(user);
            }
        });
    }

    void deleteUser(UserTable user){
        RoomBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.deleteUser(user);
            }
        });
    }




    void insertPatternData(PatternTable pattern){

        RoomBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                patternDao.insertPatternData(pattern);
            }
        });
    }


    LiveData<List<PatternTable>> getALlPattern(){

        return patternDao.getALlPattern();
    }


}

