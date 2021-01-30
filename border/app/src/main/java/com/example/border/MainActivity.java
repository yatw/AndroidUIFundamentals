package com.example.border;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout layout = findViewById(R.id.parent);

        UidlTextView uidlTextView = new UidlTextView(this);

        layout.addView(uidlTextView);

        // set the child view's top and left bind to this's top and left
        // NOTICE you MUST set constraint AFTER add textview to parent

        ConstraintSet constraint = new ConstraintSet();
        constraint.clone(layout); // MUST Clone, other wise overridden layout param and nothing show

        constraint.connect(uidlTextView.getId(), ConstraintSet.LEFT,
                layout.getId(), ConstraintSet.LEFT);
        constraint.connect(uidlTextView.getId(), ConstraintSet.TOP,
                layout.getId(), ConstraintSet.TOP);
        constraint.connect(uidlTextView.getId(), ConstraintSet.RIGHT,
                layout.getId(), ConstraintSet.RIGHT);
        constraint.connect(uidlTextView.getId(), ConstraintSet.BOTTOM,
                layout.getId(), ConstraintSet.BOTTOM);

        constraint.applyTo(layout);
    }
}