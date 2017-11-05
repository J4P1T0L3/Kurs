package pl.japitole.ocenyapek;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by uczen on 2017-11-04.
 */

public class SecoundActivity extends AppCompatActivity {

    private ImageView logo;
    private TextView name;
    private RatingBar ratingBar;
    private TextView desc;

    private Niewazneehhhhhhhhhhhhhhhh niewazneehhhhhhhhhhhhhhhh;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apkview);

        logo = (ImageView) findViewById(R.id.iconapk);
        name = (TextView) findViewById(R.id.nameapk);
        ratingBar = (RatingBar) findViewById(R.id.rateapk);
        desc = (TextView) findViewById(R.id.opis);

        Intent intent = getIntent();
        if(intent !=  null){
            name.setText(niewazneehhhhhhhhhhhhhhhh.getName());
            ratingBar.setRating(niewazneehhhhhhhhhhhhhhhh.setRating());
            desc.setText(niewazneehhhhhhhhhhhhhhhh.setDesc());

        }
    }
}
