package pl.atk.szkolenietest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;

/**
 * Created by Tomasz on 04.11.2017.
 */

public class RepoAdapter extends RecyclerView.Adapter<RepoViewHolder> {

    private Context context;
    private LinkedList<Repo> repos;
    public  RepoAdapter (Context context, LinkedList<Repo> repos){
        this.context = context;
        this.repos = repos;
    }
    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item, parent, false);
        return new RepoViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {
        holder.bindData(repos.get(position));
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }
    //todo 6: zaimplementuj adapterKKK
}
