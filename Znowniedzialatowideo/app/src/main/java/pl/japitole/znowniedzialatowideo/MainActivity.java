package pl.japitole.znowniedzialatowideo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button filmyZTelefonuButton;
    private Button poscZLinkuButton;
    private EditText wstawLinkEdittext;
    private static final int READ_PERM_CODE = 1001;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        filmyZTelefonuButton = (Button) findViewById(R.id.filmyztelefonubutton);
        poscZLinkuButton = (Button) findViewById(R.id.posczlinkubutton);
        wstawLinkEdittext = (EditText) findViewById(R.id.wstawlinkedittext);

        poscZLinkuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OtwarzanieFilmu.class);
                intent.putExtra("video_to_play", wstawLinkEdittext.getText().toString());
                startActivity(intent);
            }
        });
        filmyZTelefonuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 23){
                    int sprawdzajCzyJestPermisja = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
                    if (sprawdzajCzyJestPermisja != PackageManager.PERMISSION_GRANTED){
                        //nie mamy prermisji
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_PERM_CODE);
                    }else {
                        //masz permisje
                        startAktywnosciWybieraniaPlikow();
                    }
                } else {
                    startAktywnosciWybieraniaPlikow();
                }
            }
        });


    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == READ_PERM_CODE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //permiasje nienadano
                startAktywnosciWybieraniaPlikow();
            }else{
                //permisji nienadano
                Toast.makeText(getApplicationContext(), "Nie ma pozwolenia, nie ma aplkiacji :P", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void startAktywnosciWybieraniaPlikow(){
        Intent intent = new Intent(this, WybieraniePlikow.class);
        startActivity(intent);
    }
}
