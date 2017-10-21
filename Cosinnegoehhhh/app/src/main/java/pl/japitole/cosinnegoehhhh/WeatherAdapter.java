public class WeatherAdapter extends RecyclerView.Adapter<WeatherViewHolder> {

    private List<WeatherModel> forcasts;
    private Context context;

    public WeatherAdapter(Context context, List<WeatherModel> forcasts) {
        this.context = context;
        this.forcasts = forcasts;
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_weather, parent, false);

        return new WeatherViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        holder.bindData(forcasts.get(position));
    }

    @Override
    public int getItemCount() {
        return forcasts.size();
    }

    public void insertList(List<WeatherModel> list) {
        forcasts.clear();
        for (int i = 0; i < list.size(); i++) {
            forcasts.add(list.get(i));
            notifyItemInserted(forcasts.size() - 1);
        }
    }
}
