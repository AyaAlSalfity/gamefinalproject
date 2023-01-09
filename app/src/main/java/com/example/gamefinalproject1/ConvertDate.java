package com.example.gamefinalproject1;

import androidx.room.TypeConverter;

import java.util.Date;

public class ConvertDate {
    @TypeConverter
    public static Date toData(Long milliseconds) {
        return milliseconds == null ? null : new Date(milliseconds);

    }


    @TypeConverter
    public static Long fromDate(Date date) {
        return date == null ? null : date.getTime();

    }
}
