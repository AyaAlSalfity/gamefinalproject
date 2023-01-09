package com.example.gamefinalproject1;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModel extends AndroidViewModel {


    private Repository repository ;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }



    public LiveData<List<LevelTable>> getAllLevel(){

        return repository.getAllLevel();
    }

    public void insertLevelData(LevelTable Level){

        repository.insertLevelData(Level);
    }

    void deleteLevel(LevelTable level){

            repository.deleteLevel(level);

    }
    public LiveData<List<PuzzleTaple>> getAllQuestions(){

        return repository.getAllQuestions();
    }

    public LiveData<List<PuzzleTaple>> getAllQuestionsByLevelId(int levelNo){
        return repository.getAllQuestionsByLevelId(levelNo);
    }


    public void insertQuestionData(PuzzleTaple Data){
        repository.insertQuestionData(Data);

    }
    void deletePuzzle(PuzzleTaple Data){
          repository.deletePuzzle(Data);
    }
    public void insertPatternData(PatternTable pattern){

        repository.insertPatternData(pattern);
    }


    LiveData<List<PatternTable>> getALlPattern(){

        return repository.getALlPattern();
    }
    public void insertUser(UserTable user){
        repository.insertUser(user);
    }
    public void updateUser(UserTable user){
        repository.updateUser(user);
    }
    public void deleteUser(UserTable user){
        repository.deleteUser(user);
    }
}

