package com.example.border;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;

public class UidlTextView extends androidx.appcompat.widget.AppCompatEditText{

    Paint borderPaint;
    float strokeWidth = 40f;

    float shiftX = 0, shiftY = 0;

    public UidlTextView(Context context){
        super(context);

        this.setId(ViewCompat.generateViewId()); // unique int id for android view
        initView();
        borderPaint = new Paint();
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setColor(Color.BLACK);
        borderPaint.setStrokeWidth(strokeWidth*2);

    }

    // for debug
    public String seeBits(int value){
        String result = Integer.toBinaryString(value);
        return String.format("%32s", result).replaceAll(" ", "0");  // 32-bit Integer
    }

    @SuppressLint("ClickableViewAccessibility")
    public void initView(){
        this.setBackground(null); // disable the default green underline
        // set top, left, width, height
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                400, 200);
        this.setLayoutParams(layoutParams);
        this.setHint("PLACEHOLDER");


        this.setGravity(Gravity.LEFT|Gravity.BOTTOM);
        this.setInputType(InputType.TYPE_CLASS_TEXT);
        this.setTypeface(null, Typeface.NORMAL);


        final int gravity = this.getGravity();
        Log.i("TAG", "GRAVITY");
        Log.i("TAG", seeBits(gravity));
        Log.i("TAG", "Gravity.HORIZONTAL_GRAVITY_MASK)");
        Log.i("TAG", seeBits(Gravity.HORIZONTAL_GRAVITY_MASK));

        Log.i("TAG", "Gravity.Start)");
        Log.i("TAG", seeBits(Gravity.START));

        Log.i("TAG", "Gravity.LEFT)");
        Log.i("TAG", seeBits(Gravity.LEFT));


        final int horizontalGravity = gravity & Gravity.HORIZONTAL_GRAVITY_MASK;
        if (horizontalGravity == Gravity.LEFT){
            shiftX = strokeWidth;
        }else if (horizontalGravity == Gravity.RIGHT){
            shiftX = -strokeWidth;
        }else{
            shiftX = 0;
        }

        final int verticalGravity = gravity & Gravity.VERTICAL_GRAVITY_MASK;
        if (verticalGravity == Gravity.TOP){
            shiftY = strokeWidth;
        }else if (verticalGravity == Gravity.BOTTOM){
            shiftY = -strokeWidth;
        }else{
            shiftY = 0;
        }

    }
    @Override
    protected void onDraw(Canvas canvas) {

        // draw the border
        canvas.save();
        canvas.translate(getScrollX(), getScrollY());
        canvas.drawRect(0,0, getWidth(), getHeight(), borderPaint);
        canvas.restore();

        // finally draw the text, take into account the border width
        canvas.translate(shiftX, shiftY);
        super.onDraw(canvas);
    }
}

