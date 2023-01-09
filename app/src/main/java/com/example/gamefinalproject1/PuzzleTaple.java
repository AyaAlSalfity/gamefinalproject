package com.example.gamefinalproject1;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = LevelTable.class ,
        parentColumns = "levelNo" , childColumns ="levelNo" ,
        onDelete = ForeignKey.CASCADE , onUpdate = ForeignKey.CASCADE),
        @ForeignKey(entity = PatternTable.class ,
                parentColumns = "patternId" , childColumns ="patternId" ,
                onDelete = ForeignKey.CASCADE , onUpdate = ForeignKey.CASCADE)})
public class PuzzleTaple {
    @PrimaryKey(autoGenerate = true)
    int id ;
    String title ;
    String answer1 ;
    String answer2 ;
    String answer3 ;
    String answer4 ;
    String trueAnswer ;
    int points ;
    int currentquestion;
    int duration ;
    int patternId ;
    String hint ;
    public int levelNo ;

    public PuzzleTaple() {
    }

    public PuzzleTaple(String title, String answer1, String answer2, String answer3, String answer4, String trueAnswer, int points, int duration, int patternId, String hint, int levelNo) {
        this.title = title;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.trueAnswer = trueAnswer;
        this.points = points;
        this.duration = duration;
        this.patternId = patternId;
        this.hint = hint;
        this.levelNo = levelNo;
    }

    public int getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(int levelNo) {
        this.levelNo = levelNo;
    }

    public int getPatternId() {
        return patternId;
    }

    public void setPatternId(int patternId) {
        this.patternId = patternId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public String getTrueAnswer() {
        return trueAnswer;
    }

    public void setTrueAnswer(String trueAnswer) {
        this.trueAnswer = trueAnswer;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public int getCurrentquestion() {
        return currentquestion;
    }

    public void setCurrentquestion(int currentquestion) {
        this.currentquestion = currentquestion;
    }
}
