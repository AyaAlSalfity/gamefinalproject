package com.example.gamefinalproject1;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public class Assetsfile {

    public static String readFromAsssets(Context context, String fileName) {
        String string = "";
        try {
            InputStream inputStream=context.getAssets().open(fileName);
         int size=inputStream.available();
         byte[] bytes=new byte[size];
         inputStream.read(bytes);
         inputStream.close();
         string=new String(bytes,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }


        return string;
    }
}