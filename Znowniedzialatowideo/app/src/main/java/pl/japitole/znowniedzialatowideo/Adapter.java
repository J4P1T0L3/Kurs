package pl.japitole.znowniedzialatowideo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by uczen on 2017-10-22.
 */

public class Adapter extends RecyclerView.Adapter<Holder> {

    private Context context;
    private List<String> data;

    public Adapter(Context context, List<String> data){
        this.context = context;
        this.data = data;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new Holder(context, view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.bindData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
