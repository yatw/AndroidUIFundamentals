package com.example.programmaticviews;

import android.content.Context;

import android.graphics.Color;
import android.util.Log;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;

import org.json.JSONException;
import org.json.JSONObject;


public class UidlLabel extends androidx.appcompat.widget.AppCompatTextView {

    JSONObject ins;

    String text = "NO TEXT";
    int textSize = 22;
    int width = ViewGroup.LayoutParams.WRAP_CONTENT;
    int height  = ViewGroup.LayoutParams.WRAP_CONTENT;
    int top = -1;
    int left = 0;
    int marginTop = 0;


    public UidlLabel(Context context, JSONObject instructions){
        super(context);
        this.setId(ViewCompat.generateViewId()); // unique int id for android view

        this.ins = instructions;
        initView();
    }

    public void initView(){


        try{
            text = ins.has("text")? ins.getString("text"): "NO TEXT";
            textSize = ins.has("textSize")? ins.getInt("textSize"): 22;
            width = ins.has("width")? ins.getInt("width"): ViewGroup.LayoutParams.WRAP_CONTENT;
            height = ins.has("height")? ins.getInt("height"): ViewGroup.LayoutParams.WRAP_CONTENT;
            top = ins.has("top")? ins.getInt("top"): -1;
            left = ins.has("left")? ins.getInt("left"): 0;
            marginTop = ins.has("marginTop")? ins.getInt("marginTop"): 0;
        }catch (JSONException e){
            e.printStackTrace();
        }

        // set top, left, width, height
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                width, height);


        // use top to determine, it means absolute Y distance from parent
        // or
        // marginTop, some distance from the last component
        if (top > 0){
            layoutParams.setMargins(left,top,0,0);
        }else{
            layoutParams.setMargins(left,marginTop,0,0);
        }

        this.setLayoutParams(layoutParams);

        this.setIncludeFontPadding(false);

        this.setText(text);
        this.setTextSize(textSize);
        this.setBackgroundColor(Color.RED);
    }

    public boolean constraintToLast(){
        return top < 0;
    }
}