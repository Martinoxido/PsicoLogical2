package com.example.psicological;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

public class FormFeel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_feel);

        List<String> datos = new ArrayList<String>();

        ImageButton volverbtn = (ImageButton) findViewById(R.id.btnVolver);
        Button btnAyuda = (Button) findViewById(R.id.btnAyuda);
        Switch sw1 = (Switch) findViewById(R.id.dolorcabeza);
        Switch sw2 = (Switch) findViewById(R.id.nervios);
        Switch sw3 = (Switch) findViewById(R.id.agitacion);
        Switch sw4 = (Switch) findViewById(R.id.Sobrepe);
        Switch sw5 = (Switch) findViewById(R.id.Pena);
        Switch sw6 = (Switch) findViewById(R.id.ira);
        Switch sw7 = (Switch) findViewById(R.id.frustracion);
        Switch sw8 = (Switch) findViewById(R.id.nudo);
        Switch sw9 = (Switch) findViewById(R.id.molestia);

        //***************************************ZONA DE CHECKEO***************************************//
        sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    datos.add((String) sw1.getText());
                }
                else {
                    datos.remove(datos.indexOf((String) sw1.getText()));
                }
            }
        });
        sw2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    datos.add((String) sw2.getText());
                } else {
                    datos.remove(datos.indexOf((String) sw2.getText()));
                }
            }
        });

        sw3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    datos.add((String) sw3.getText());
                } else {
                    datos.remove(datos.indexOf((String) sw3.getText()));
                }
            }
        });
        sw4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    datos.add((String) sw4.getText());
                } else {
                    datos.remove(datos.indexOf((String) sw4.getText()));
                }
            }
        });

        sw5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    datos.add((String) sw5.getText());
                } else {
                    datos.remove(datos.indexOf((String) sw5.getText()));
                }
            }
        });

        sw6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    datos.add((String) sw6.getText());
                } else {
                    datos.remove(datos.indexOf((String) sw6.getText()));
                }
            }
        });

        sw7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    datos.add((String) sw7.getText());
                } else {
                    datos.remove(datos.indexOf((String) sw7.getText()));
                }
            }
        });

        sw8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    datos.add((String) sw8.getText());
                } else {
                    datos.remove(datos.indexOf((String) sw8.getText()));
                }
            }
        });

        sw9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    datos.add((String) sw9.getText());
                } else {
                    datos.remove(datos.indexOf((String) sw9.getText()));
                }
            }
        });


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
        btnAyuda.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:


                        return true;

                    case MotionEvent.ACTION_UP:

                        Intent intent = new Intent(FormFeel.this, Ayuda.class);
                        String[] datoArray = datos.toArray(new String[0]);
                        intent.putExtra("clave", datoArray);
                        startActivity(intent);

                        return true;

                    default:
                        return false;
                }
            }
        });
    }
}