package com.example.psicological;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import android.widget.ImageButton;
import android.widget.Switch;

public class FormFeel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_feel);

        ImageButton volverbtn = (ImageButton) findViewById(R.id.btnVolver);
        volverbtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        volverbtn.setImageResource(R.drawable.volver_pressed);
                        return true;

                    case MotionEvent.ACTION_UP:

                        volverbtn.setImageResource(R.drawable.volver_static);
                        startActivity(new Intent(FormFeel.this, MainActivity.class));
                        return true;

                    default:
                        return false;
                }
            }
        });
    }
}