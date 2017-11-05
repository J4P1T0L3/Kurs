package pl.japitole.ocenyapek;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinkedList<Niewazneehhhhhhhhhhhhhhhh> niewazneehhhhhhhhhhhhhhhh = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apklist);

        niewazneehhhhhhhhhhhhhhhh.add(new Niewazneehhhhhhhhhhhhhhhh("Vainglory", "https://lh3.googleusercontent.com/bLTJ2ggznyNZogbD7tC7UiCqqz5SjB6i7gEsiGKxNn135Wqyo_7Q-Kapxss8w8e1Exc=w300-rw", getResources().getString(R.string.opisvain), 4.4f));
        niewazneehhhhhhhhhhhhhhhh.add(new Niewazneehhhhhhhhhhhhhhhh("Arena of Valor: 5v5 Arena Game", "https://lh3.googleusercontent.com/xkznEC9SJloc_8S0IgesTIax0zsvPTUfluME947--DI_DF_cnSnW1Mon57QLoZy9slFy=w300-rw", getResources().getString(R.string.opisarena), 4.3f));
        recyclerView = (RecyclerView) findViewById(R.id.recycle);

        recyclerView.setAdapter(new AppAdapter(this, niewazneehhhhhhhhhhhhhhhh));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
