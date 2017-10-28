package com.example.tomasz.playground;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Tomasz on 06.10.2017.
 */

public class Settings extends AppCompatActivity {

    private Button cel;
    private Button far;
    private Button kal;
    private Button changecitybutton;
    private TextView deg;
    private TextView city;
    private Boolean czyCosZmieniono = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        cel = (Button) findViewById(R.id.cel);
        far = (Button) findViewById(R.id.far);
        kal = (Button) findViewById(R.id.kal);
        changecitybutton = (Button) findViewById(R.id.zmienmiasto);
        deg = (TextView) findViewById(R.id.deg);
        city = (TextView) findViewById(R.id.city);

        cel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deg.setText("C");
                czyCosZmieniono = true;
            }
        });

        far.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deg.setText("F");
                czyCosZmieniono = true;
            }
        });

        kal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deg.setText("K");
                czyCosZmieniono = true;
            }
        });

        changecitybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
                LayoutInflater inflater = Settings.this.getLayoutInflater();
                View dialogview = inflater.inflate(R.layout.alert, null);
                builder.setView(dialogview);

                final AlertDialog alertDialog = builder.create();

                final EditText editText = (EditText) dialogview.findViewById(R.id.miastoedittext);
                Button button = (Button) dialogview.findViewById(R.id.potwierdzalert);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (editText.getText().toString().isEmpty()) {
                            editText.setError("Miasto nie może byc puste");
                        } else {
                            editText.setError(null);
                            city.setText(editText.getText().toString());
                            czyCosZmieniono = true;
                            editText.setText("");
                            alertDialog.dismiss();
                        }

                    }
                });
                alertDialog.show();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getSharedPreferences("weather", Activity.MODE_PRIVATE);
        String cityString = prefs.getString("city", "Warszawa");
        String degrees = prefs.getString("degrees", "C");

        deg.setText(degrees);
        city.setText(cityString);
    }

    public void saveDegrees(String degrees) {
        SharedPreferences prefs = getSharedPreferences("weather", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("degrees", degrees);
        editor.commit();
    }

    public void saveCity(String cityToChange) {
        SharedPreferences prefs = getSharedPreferences("weather", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("city", cityToChange);
        editor.commit();

    }

    @Override
    public void onBackPressed() {
        if (czyCosZmieniono == true) {
            AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
            builder.setTitle("Zmieniono Ustawienia");
            builder.setMessage("Czy chcesz zapisać zmiany?");
            builder.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    saveCity(city.getText().toString());
                    saveDegrees(deg.getText().toString());
                    Settings.super.onBackPressed();
                }
            });
            builder.setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Settings.super.onBackPressed();
                }
            });
            builder.show();
        } else {
            Settings.super.onBackPressed();
        }
    }
}
