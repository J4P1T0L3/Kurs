package com.example.tomasz.playground;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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
    private Button change;
    private TextView deg;
    private TextView city;
    private EditText cityEt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        cel = (Button) findViewById(R.id.cel);
        far = (Button) findViewById(R.id.far);
        kal = (Button) findViewById(R.id.kal);
        change = (Button) findViewById(R.id.change);
        deg = (TextView) findViewById(R.id.deg);
        city = (TextView) findViewById(R.id.city);
        cityEt = (EditText) findViewById(R.id.city_et);

        cel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDegrees("C");
            }
        });

        far.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDegrees("F");
            }
        });

        kal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDegrees("K");
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cityEt.getText().toString().isEmpty()) {
                    cityEt.setError("Miasto nie może byc puste");
                } else {
                    cityEt.setError(null);
                    saveCity(cityEt.getText().toString());
                    cityEt.setText("");
                }
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

        deg.setText(degrees);

        Toast.makeText(getApplicationContext(), "Zmieniono", Toast.LENGTH_SHORT).show();
    }

    public void saveCity(String cityToChange) {
        SharedPreferences prefs = getSharedPreferences("weather", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("city", cityToChange);
        editor.commit();

        city.setText(cityToChange);

        Toast.makeText(getApplicationContext(), "Zmieniono", Toast.LENGTH_SHORT).show();
    }
}
