package pl.japitole.notentegoaplikacja;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.ActionMenuItemView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * Created by uczen on 2017-09-24.
 */

public class Eksdi extends AppCompatActivity {

    private EditText edit;
    private Button przycisk;
    private TextView napis;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kolejnezadanie);

        edit = (EditText) findViewById(R.id.wpisztext);
        przycisk = (Button) findViewById(R.id.przycisk);
        napis = (TextView) findViewById(R.id.napis);

        przycisk.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String s = edit.getText().toString();
                napis.setText(s);
            }
        });
    }
}



