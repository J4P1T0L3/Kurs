package com.example.tomasz.playground;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by Tomasz on 06.10.2017.
 */

public class WeatherViewHolder extends RecyclerView.ViewHolder {

    private ImageView logo;
    private TextView city;
    private TextView date;
    private TextView temp;
    private TextView group;

    private Context context;

    public WeatherViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
        logo = (ImageView) itemView.findViewById(R.id.logo);
        city = (TextView) itemView.findViewById(R.id.city_res);
        date = (TextView) itemView.findViewById(R.id.date_res);
        temp = (TextView) itemView.findViewById(R.id.temp_res);
        group = (TextView) itemView.findViewById(R.id.group);
    }

    public void bindData(WeatherModel model) {
        city.setText(model.getCity());
        date.setText(model.getDate());
        temp.setText(model.getTemp()+"");
        group.setText(model.getWeatherGroup());

        Glide.with(context)
                .load("http://openweathermap.org/img/w/"+model.getIconId()+".png")
                .into(logo);
    }
}
