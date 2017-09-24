package pl.japitole.notentegoaplikacja;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private TextView textEx;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie);
        textEx = (TextView) findViewById(R.id.text);
        String napis = textEx.getText().toString();
        films.add(film);
        films.add(film2);
        films.add(film3);
        films.add(film4);
        films.add(film5);
        films.add(film);
        films.add(film2);
        films.add(film3);
        films.add(film4);
        films.add(film5);
        films.add(film);
        films.add(film2);
        films.add(film3);
        films.add(film4);
        films.add(film5);
        films.add(film);
        films.add(film2);
        films.add(film3);
        films.add(film4);
        films.add(film5);
        films.add(film);
        films.add(film2);
        films.add(film3);
        films.add(film4);
        films.add(film5);
        films.add(film);
        films.add(film2);
        films.add(film3);
        films.add(film4);
        films.add(film5);
        films.add(film);
        films.add(film2);
        films.add(film3);
        films.add(film4);
        films.add(film5);
        films.add(film);
        films.add(film2);
        films.add(film3);
        films.add(film4);
        films.add(film5);
        films.add(film);
        films.add(film2);
        films.add(film3);
        films.add(film4);
        films.add(film5);


        for (int i = 0; i<films.size(); i++){
            textEx.append("Tytuł " + films.get(i).getTitle() + " oraz chyba ma " + films.get(i).getLenght() + " sezonw" + "\n");
        }

    }

    Movie film = new Movie("Fargo", 3);
    Movie film2 = new Movie("Preacher", 2);
    Movie film3 = new Movie("Miasteczko Twin Peaks", 3);
    Movie film4 = new Movie("iZombie", 3);
    Movie film5 = new Movie("American Gods", 2);

    LinkedList<Movie> films = new LinkedList<>();


}
