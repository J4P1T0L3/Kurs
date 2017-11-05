package pl.atk.szkolenietest;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by Tomasz on 04.11.2017.
 */

public class SettingsActivity extends AppCompatActivity {

    private TextView prefLang;
    private Button changeBtn;
    private EditText editText;
    //todo 15: dodaj edit text w kodzieKKK

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        editText = (EditText) findViewById(R.id.edittext);
        prefLang = (TextView) findViewById(R.id.prefs_lang_tv);
        changeBtn = (Button) findViewById(R.id.change_btn);

        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo 16: zapisz dane do shared preferencesKKK
                SharedPreferences prefs = getSharedPreferences("language", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("language", editText.getText().toString());
                editor.commit();
                //todo 17: zmien text w textviewKKK
                prefLang.setText(editText.getText().toString());
                //todo 18: wyświetl toast który poinformuje użytkownaika że zmiany zostały zapisaneKKK
                Toast.makeText(getApplicationContext(), "Zmiany zostały zapisane", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getSharedPreferences("language", Activity.MODE_PRIVATE);
        String languageString = prefs.getString("language", "");
        prefLang.setText(languageString);

        //todo 19: pobierz aktualnie zapisany język i wyświetl go w textviewKKK
    }
}
