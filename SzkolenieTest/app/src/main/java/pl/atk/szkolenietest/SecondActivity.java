package pl.atk.szkolenietest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by Tomasz on 04.11.2017.
 */

public class SecondActivity extends AppCompatActivity {

    //todo 11: odbierz intentKKK
    //todo 12: przypisz dane do komponentówKKK
    private ImageView icon;
    private TextView name;
    private TextView user;
    private TextView cloneurl;
    private TextView date;
    private TextView language;
    private TextView size;
    private TextView watchers;
    private TextView desc;

    private Repo repos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        icon = (ImageView) findViewById(R.id.image);
        name = (TextView) findViewById(R.id.name);
        user = (TextView) findViewById(R.id.user);
        cloneurl = (TextView) findViewById(R.id.cloneurl);
        date = (TextView) findViewById(R.id.date);
        language = (TextView) findViewById(R.id.language);
        size = (TextView) findViewById(R.id.size);
        watchers = (TextView) findViewById(R.id.watchers);
        desc = (TextView) findViewById(R.id.desc);

        Intent intent = getIntent();
        if(intent !=  null){
            repos = (Repo) intent.getSerializableExtra("repos");
        }

        if(repos != null){
            Glide.with(this)
                    .load(repos.getAvatarUrl()).into(icon);
            name.setText(repos.getName());
            user.setText(repos.getUser());
            cloneurl.setText(repos.getCloneUrl());
            date.setText(repos.getCreatedDate());
            language.setText(repos.getLanguage());
            size.setText(repos.getSize()+"");
            watchers.setText(repos.getWatchers()+"");
            desc.setText(repos.getDesc());
        }

    }
}
