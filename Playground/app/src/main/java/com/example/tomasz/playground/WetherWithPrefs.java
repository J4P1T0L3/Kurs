package com.example.tomasz.playground;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
 * Created by Tomasz on 06.10.2017.
 */

public class WetherWithPrefs extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private TextView result;
    private EditText searchET;
    private Button searchBtn;
    private Button settings;
    private ProgressBar progressBar;
    private RecyclerView recycler;
    private WeatherAdapter adapter;

    private static final String API_KEY = "&APPID=e378120272c670db540b58db72d8c753";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/forecast?q=";

    private String unit = "&units=metric";

    WeatherDB db;

    public static final int LOADER_ID = 23;

    List<WeatherModel> forecast = new LinkedList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_prefs);

        db = new WeatherDB(this);

        result = (TextView) findViewById(R.id.result);
        searchET = (EditText) findViewById(R.id.search_et);
        searchBtn = (Button) findViewById(R.id.search_btn);
        settings = (Button) findViewById(R.id.settings);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        recycler = (RecyclerView) findViewById(R.id.recycler);

        adapter = new WeatherAdapter(this, forecast);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        final LoaderManager.LoaderCallbacks<String> callbacks = this;

        getSupportLoaderManager().initLoader(LOADER_ID, null, callbacks);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Settings.class);
                startActivity(intent);
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetworkAvailable()) {
                    String searchQuery = BASE_URL + searchET.getText().toString() + API_KEY + unit;
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

                        LoaderManager loaderManager = getSupportLoaderManager();
                        Loader<String> stringLoader = loaderManager.getLoader(LOADER_ID);

                        if (stringLoader == null) {
                            loaderManager.initLoader(LOADER_ID, queryBundle, callbacks);
                        } else {
                            loaderManager.restartLoader(LOADER_ID, queryBundle, callbacks);
                        }
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Nie masz neta", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getSharedPreferences("weather", Activity.MODE_PRIVATE);

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

                LoaderManager loaderManager = getSupportLoaderManager();
                Loader<String> stringLoader = loaderManager.getLoader(LOADER_ID);

                if (stringLoader == null) {
                    loaderManager.initLoader(LOADER_ID, queryBundle, this);
                } else {
                    loaderManager.restartLoader(LOADER_ID, queryBundle, this);
                }
            }
        } else {
            adapter = new WeatherAdapter(this, db.getAllRepos());
            recycler.setAdapter(adapter);
        }
    }

    @Override
    public Loader<String> onCreateLoader(int id, final Bundle args) {
        return new AsyncTaskLoader<String>(this) {
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
                adapter = new WeatherAdapter(getApplicationContext(), forecast);
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

