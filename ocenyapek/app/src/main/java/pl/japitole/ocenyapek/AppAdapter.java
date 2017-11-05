package pl.japitole.ocenyapek;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;

/**
 * Created by uczen on 2017-11-04.
 */

public class AppAdapter extends RecyclerView.Adapter<AppViewHolder> {

    private Context context;
    private LinkedList<Niewazneehhhhhhhhhhhhhhhh> niewazneehhhhhhhhhhhhhhhh;
    public AppAdapter (Context context, LinkedList<Niewazneehhhhhhhhhhhhhhhh> niewazneehhhhhhhhhhhhhhhh){
        this.context = context;
        this.niewazneehhhhhhhhhhhhhhhh = niewazneehhhhhhhhhhhhhhhh;
    }

    @Override
    public AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.apkrelative, parent, false);

        return new AppViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(AppViewHolder holder, int position) {
        holder.bindData(niewazneehhhhhhhhhhhhhhhh.get(position));

    }

    @Override
    public int getItemCount() {
        return niewazneehhhhhhhhhhhhhhhh.size();
    }
}
