package com.example.gamefinalproject1;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

//@Entity(foreignKeys = {@ForeignKey(entity = LevelTable.class ,
  //      parentColumns = "levelNo" , childColumns ="levelid" ,
    //    onDelete = ForeignKey.CASCADE , onUpdate = ForeignKey.CASCADE),})
@Entity
@TypeConverters({ConvertDate.class})
public class UserTable {
    @PrimaryKey(autoGenerate = true)
    private int key;
    @ColumnInfo(name = "username")
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String email;
    @NonNull
    private String birthdate;
   // private Date birthdate;
    private String country;
   // private String gender;
      private  boolean gender;
    private boolean gender2;
  //  private  int levelid;
    private int score;
    private int wrong;
    private int correct;
    private int question;

    public UserTable() {
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(@NonNull String birthdate) {
        this.birthdate = birthdate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean isGender2() {
        return gender2;
    }

    public void setGender2(boolean gender2) {
        this.gender2 = gender2;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getWrong() {
        return wrong;
    }

    public void setWrong(int wrong) {
        this.wrong = wrong;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

//    public int getLevelid() {
//        return levelid;
//    }
//
//    public void setLevelid(int levelid) {
//        this.levelid = levelid;
//    }
}