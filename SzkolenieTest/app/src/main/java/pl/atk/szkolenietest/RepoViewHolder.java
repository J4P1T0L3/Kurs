package pl.atk.szkolenietest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by Tomasz on 04.11.2017.
 */

public class RepoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private Context context;
    private ImageView icon;
    private TextView name;
    private TextView user;
    private Repo repos;
    public RepoViewHolder(View itemView, Context context){
        super(itemView);
        this.context = context;
        icon =  (ImageView) itemView.findViewById(R.id.imageviewrelative);
        name =  (TextView) itemView.findViewById(R.id.textviewrelative);
        user = (TextView) itemView.findViewById(R.id.textviewrelative0);

        itemView.setOnClickListener(this);
    }
    public void bindData(Repo repos){
        this.repos = repos;

        name.setText(repos.getName());
        user.setText(repos.getUser());

        Glide.with(context)
                .load(repos.getAvatarUrl())
                .into(icon);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra("repos", repos);
        context.startActivity(intent);
    }
    //todo 5: zaimplementuj viewholderKKK


    //todo 9: dodaj funkcjonalność klikania na viewholderKKK
    //todo 10.2: po kliknięciu prześlij do drugiej aktywności obiekt repo i wystartuj aktywnoscKKK
}
