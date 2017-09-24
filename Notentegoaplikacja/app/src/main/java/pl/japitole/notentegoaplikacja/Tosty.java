package pl.japitole.notentegoaplikacja;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by uczen on 2017-09-24.
 */

public class Tosty extends AppCompatActivity {
    private Button przycisk1;
    private Button przycisk2;
    private Button przycisk3;
    private Button przycisk4;
    private Button przycisk5;
    private Button przycisk6;
    private Button przycisk7;
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jeszczekolejnylayout);

        przycisk1 = (Button) findViewById(R.id.jeden);
        przycisk2 = (Button) findViewById(R.id.dwa);
        przycisk3 = (Button) findViewById(R.id.trzy);
        przycisk4 = (Button) findViewById(R.id.cztery);
        przycisk5 = (Button) findViewById(R.id.srodek);
        przycisk6 = (Button) findViewById(R.id.srodeklewo);
        przycisk7 = (Button) findViewById(R.id.srodekprawo);

        przycisk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), przycisk1.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        przycisk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), przycisk2.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        przycisk3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), przycisk3.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        przycisk4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), przycisk4.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        przycisk5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), przycisk5.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        przycisk6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), przycisk6.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        przycisk7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), przycisk7.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
