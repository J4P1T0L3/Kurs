package pl.japitole.notentegoaplikacja;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by uczen on 2017-09-24.
 */

public class Logowanie extends AppCompatActivity {

    private EditText editlogin;
    private EditText edithaslo;
    private Button zaloguj;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logowanie);

        editlogin = (EditText) findViewById(R.id.logowanie);
        edithaslo = (EditText) findViewById(R.id.haslo);
        zaloguj = (Button) findViewById(R.id.przycisk);


        zaloguj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean czyMogeLogowac = true;
                String s;
                String h;
                h = edithaslo.getText().toString();
                s = editlogin.getText().toString();
                if (s.isEmpty()) {
                    editlogin.setError("Login nie może być pusty");
                    czyMogeLogowac = false;
                } else {
                    editlogin.setError(null);
                }
                if (h.isEmpty()) {
                    edithaslo.setError("Hasło nie może być puste");
                    czyMogeLogowac = false;
                } else {
                    edithaslo.setError(null);
                }
                if (czyMogeLogowac == true && s.equals("user") && h.equals("qwe123")) {
                    Toast.makeText(getApplicationContext(), "Zostałeś Zalogowany", Toast.LENGTH_LONG).show();
                } else if (czyMogeLogowac == true) {
                    Toast.makeText(getApplicationContext(), "Masz błędne dane logowania", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
