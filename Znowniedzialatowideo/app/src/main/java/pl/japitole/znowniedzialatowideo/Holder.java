package pl.japitole.znowniedzialatowideo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * Created by uczen on 2017-10-22.
 */

public class Holder extends RecyclerView.ViewHolder implements OnClickListener{

    private TextView sciezkapliku;
    private Context context;

    public Holder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        sciezkapliku = (TextView) itemView.findViewById(R.id.sciezkaplikow);
        itemView.setOnClickListener(this);

    }
    public void bindData (String sciezkaplikuString){
        sciezkapliku.setText(sciezkaplikuString);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, OtwarzanieFilmu.class);
        intent.putExtra("video_to_play", sciezkapliku.getText().toString());
        context.startActivity(intent);
    }
}
