package com.example.programmaticviews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create instruction for children
        JSONArray children = new JSONArray();
        try{
            JSONObject text1 = new JSONObject();
            JSONObject text2 = new JSONObject();
            children.put(text1);
            children.put(text2);

            text1.put("text", "TEXT1");
            text1.put("textSize", 55);
            text1.put("top", 200);
            //text1.put("width", 100); // uncomment to see effect

            text2.put("text", "TEXT2");
            text2.put("textSize", 120);
            text2.put("marginTop", 400);
            //text2.put("top", 600);
            //text2.put("width", 100);
        }catch (JSONException e){
            e.printStackTrace();
        }

        UidlView uidlView = new UidlView(this, children);

        FrameLayout frameLayout = findViewById(R.id.root_view);
        frameLayout.addView(uidlView);
    }
}