package pl.japitole.ocenyapek;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by uczen on 2017-11-04.
 */

public class AppViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private Context context;
    private TextView tytul;
    private ImageView icon;
    private RatingBar ocena;
    private Niewazneehhhhhhhhhhhhhhhh niewazneehhhhhhhhhhhhhhhh;

    public AppViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;

        tytul = (TextView) itemView.findViewById(R.id.apkname);
        icon = (ImageView) itemView.findViewById(R.id.apkicon);
        ocena = (RatingBar) itemView.findViewById(R.id.apkrating);

        itemView.setOnClickListener(this);
    }
    public void bindData(Niewazneehhhhhhhhhhhhhhhh niewazneehhhhhhhhhhhhhhhh){
        this.niewazneehhhhhhhhhhhhhhhh = niewazneehhhhhhhhhhhhhhhh;

        tytul.setText(niewazneehhhhhhhhhhhhhhhh.getName());
        ocena.setRating(niewazneehhhhhhhhhhhhhhhh.getRating());

        Glide.with(context)
                .load(niewazneehhhhhhhhhhhhhhhh.getLogoUrl())
                .into(icon);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, SecoundActivity.class);
        intent.putExtra("niewazneehhhhhhhhhhhhhhhh", niewazneehhhhhhhhhhhhhhhh);
    }
}
