package pl.atk.szkolenietest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private EditText searchEdit;
    private Button searchButton;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private static final String BASE_URL = "https://api.github.com/search/repositories?q=";
    public static final int LOADER_ID = 23;

    private String URL_LANG = "";

    private LinkedList<Repo> repos = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        searchEdit = (EditText) findViewById(R.id.search_edit);
        searchButton = (Button) findViewById(R.id.search_btn);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        //todo 8: ustaw LinearManager dla recyclerViewKKK
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final LoaderManager.LoaderCallbacks<String> callbacks = this;

        getSupportLoaderManager().initLoader(LOADER_ID, null, callbacks);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo 1: zabezpiecz edit text przed wpisaniem pustego pola i wyświetl error w edit text KKK
                String searchQuery = BASE_URL + searchEdit.getText().toString() + URL_LANG;
                searchQuery = searchQuery.replaceAll("\\s+", "");
                Uri uri = Uri.parse(searchQuery);
                URL url = null;
                searchEdit.setError(null);
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
                }else{
                    searchEdit.setError("Pole nie może być puste");
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();


        URL_LANG = "";

        //todo 20: pobierz język z shared preferencees i jeżeli nie jest pusty to zmień zmienną URL_LANG do postaci: +language:język

        SharedPreferences prefs = getSharedPreferences("language", Activity.MODE_PRIVATE);
        String languageString = prefs.getString("language", "");
        if (!languageString.equals("")){
            URL_LANG = "+language:" + languageString;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            //todo 13: wystartuj aktywność z ustawieniamiKKK
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
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
                recyclerView.setVisibility(View.INVISIBLE);

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
        if (data != null && !data.equals("")) {
            progressBar.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            this.repos.clear();
            try {
                JSONObject in = new JSONObject(data);
                JSONArray reposArr = in.getJSONArray("items");
                for (int i = 0; i < reposArr.length(); i++) {
                    JSONObject obj = reposArr.getJSONObject(i);

                    String name = obj.getString("name");

                    JSONObject owner = obj.getJSONObject("owner");
                    String login = owner.getString("login");
                    String userUrl = owner.getString("avatar_url");

                    String desc = obj.getString("description");
                    String cloneUrl = obj.getString("clone_url");
                    String date = obj.getString("created_at");
                    String language = obj.getString("language");
                    int size = obj.getInt("size");
                    int watchers = obj.getInt("watchers_count");

                    Repo repo = new Repo(name, login, userUrl, desc, cloneUrl, date, language, size, watchers);
                    this.repos.add(repo);
                }
                //todo 7: ustaw adapter dla recycler viewKKK;
                recyclerView.setAdapter(new RepoAdapter(this, repos));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "Brak wyników zapytania", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
}
