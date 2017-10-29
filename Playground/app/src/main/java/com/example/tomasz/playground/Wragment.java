package com.example.tomasz.playground;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by uczen on 2017-10-29.
 */

public class Wragment extends Fragment  implements LoaderManager.LoaderCallbacks<String> {

    private TextView result;
    private ProgressBar progressBar;
    private RecyclerView recycler;
    private WeatherAdapter adapter;

    private static final String API_KEY = "&APPID=e378120272c670db540b58db72d8c753";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/forecast?q=";

    private String unit = "&units=metric";

    WeatherDB db;

    public static final int LOADER_ID = 23;

    List<WeatherModel> forecast = new LinkedList<>();

    public static Wragment newInstance() {
        Wragment wragment = new Wragment();
        return wragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_prefs, container, false);

        db = new WeatherDB(getContext());

        result = (TextView) view.findViewById(R.id.result);
        progressBar = (ProgressBar) view.findViewById(R.id.progress);
        recycler = (RecyclerView) view.findViewById(R.id.recycler);

        adapter = new WeatherAdapter(getContext(), forecast);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        final LoaderManager.LoaderCallbacks<String> callbacks = this;

        getActivity().getSupportLoaderManager().initLoader(LOADER_ID, null, callbacks);

        return view;
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences prefs = getActivity().getSharedPreferences("weather", Activity.MODE_PRIVATE);

        String degrees = prefs.getString("degrees", "C");

        if (degrees.equals("C")) {
            unit = "&units=metric";
        } else if (degrees.equals("F")) {
            unit = "&units=imperial";
        } else if (degrees.equals("K")) {
            unit = "&units=default";
        }

        String cityString = prefs.getString("city", "Warszawa");

        if (isNetworkAvailable()) {
            String searchQuery = BASE_URL + cityString + API_KEY + unit;
            Uri uri = Uri.parse(searchQuery);
            URL url = null;
            try {
                url = new URL(uri.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            if (url != null) {
                Bundle queryBundle = new Bundle();
                queryBundle.putString("query", url.toString());

                LoaderManager loaderManager = getActivity().getSupportLoaderManager();
                Loader<String> stringLoader = loaderManager.getLoader(LOADER_ID);

                if (stringLoader == null) {
                    loaderManager.initLoader(LOADER_ID, queryBundle, this);
                } else {
                    loaderManager.restartLoader(LOADER_ID, queryBundle, this);
                }
            }
        } else {
            adapter = new WeatherAdapter(getContext(), db.getAllRepos());
            recycler.setAdapter(adapter);
        }
    }

    @Override
    public Loader<String> onCreateLoader(int id, final Bundle args) {
        return new AsyncTaskLoader<String>(getContext()) {
            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                if (args == null) {
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                result.setVisibility(View.INVISIBLE);

                forceLoad();
            }

            @Override
            public String loadInBackground() {
                String urlString = args.getString("query");
                if (urlString == null || urlString.isEmpty()) {
                    return null;
                }
                try {
                    URL url = new URL(urlString);
                    return NetUtils.getResponseFromHttpUrl(url);
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
       progressBar.setVisibility(View.INVISIBLE);
        result.setVisibility(View.VISIBLE);
        if (data != null && !data.equals("")) {
            result.setText("");

            db.clearRepos();

            forecast.clear();
            try {
                JSONObject in = new JSONObject(data);

                JSONObject cityObj = in.getJSONObject("city");
                String city = cityObj.getString("name");

                JSONArray forecasts = in.getJSONArray("list");
                for (int i = 0; i < forecasts.length(); i++) {
                    JSONObject obj = forecasts.getJSONObject(i);

                    long dt = obj.getLong("dt");
                    Date date = new Date(dt * 1000);

                    JSONObject main = obj.getJSONObject("main");
                    double temp = main.getDouble("temp");

                    JSONArray weath = obj.getJSONArray("weather");
                    String weatherGroup = weath.getJSONObject(0).getString("main");
                    String iconId = weath.getJSONObject(0).getString("icon");

                    WeatherModel w = new WeatherModel(city, date.toString(), temp, weatherGroup, iconId);
                    forecast.add(w);
                    db.addRepo(w);
                }
                adapter = new WeatherAdapter(getContext(), forecast);
                recycler.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
                result.setText(e.toString());
            }
        } else {
            result.setText("Brak wyników zapytania");
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
}
