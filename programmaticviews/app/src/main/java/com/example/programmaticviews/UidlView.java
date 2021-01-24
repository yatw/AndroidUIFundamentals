package com.example.programmaticviews;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.ViewCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UidlView extends ConstraintLayout{

    public JSONArray children;


    public UidlView(Context context, JSONArray children){
        super(context);

        this.children = children;

        this.setId(ViewCompat.generateViewId()); // unique int id for android view
        initView();
    }


    public void initView(){

        // set top, left, width, height
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
               ViewGroup.LayoutParams.MATCH_PARENT,
               ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(0,0,0,0);
        this.setLayoutParams(layoutParams);


        this.setWillNotDraw(false); // Constraint layout is a viewgroup, require setWillNotDraw(false) to false to override OnDraw()


        View lastComponent = null;

        // view may have children, create them under current view
        for (int i = 0; i < children.length(); i++){

            JSONObject child;

            try{
                child = children.getJSONObject(i);
            }catch (JSONException e){
                e.printStackTrace();
                continue;
            }

            UidlLabel childWidget = new UidlLabel(getContext(), child);

            this.addView(childWidget);

            // set the child view's top and left bind to this's top and left
            // NOTICE you MUST set constraint AFTER add textview to parent

            ConstraintSet constraint = new ConstraintSet();
            constraint.clone(this); // MUST Clone, other wise overridden layout param and nothing show

            constraint.connect(childWidget.getId(), ConstraintSet.LEFT,
                    this.getId(), ConstraintSet.LEFT);

            boolean constraintToLast = childWidget.constraintToLast();
            if (constraintToLast && lastComponent != null){
                // Set the constraint of this view, top to the bottom of the last view

                constraint.connect(childWidget.getId(), ConstraintSet.TOP,
                        lastComponent.getId(), ConstraintSet.BOTTOM);
            }else{
                // Otherwise set the child's top to this constriant layout's top

                constraint.connect(childWidget.getId(), ConstraintSet.TOP,
                        this.getId(), ConstraintSet.TOP);
            }

            constraint.applyTo(this);
            lastComponent = childWidget;
        }

    }


}