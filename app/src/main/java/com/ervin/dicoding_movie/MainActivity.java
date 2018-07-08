package com.ervin.dicoding_movie;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ArrayList<Movie> dataMovie;
    ListView lvMovie;
    EditText etSearch;
    Button btnSearch;
    ProgressBar loading;
    private static MovieListAdapter movieListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvMovie = (ListView) findViewById(R.id.lv_movie);
        btnSearch = (Button) findViewById(R.id.btn_search);
        etSearch = (EditText) findViewById(R.id.et_movie);
        loading = (ProgressBar) findViewById(R.id.pb_loading);
        btnSearch.setOnClickListener(this);
        if(savedInstanceState!=null){
            dataMovie = savedInstanceState.getParcelableArrayList("listview.state");
            movieListAdapter=new MovieListAdapter(dataMovie,this);
            lvMovie.setAdapter(movieListAdapter);
        }
        lvMovie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Movie movie = dataMovie.get(i);
                Intent moveObject = new Intent(MainActivity.this,DetailMovieActivity.class);
                moveObject.putExtra(DetailMovieActivity.DATA_MOVE,movie);
                startActivity(moveObject);
            }
        });
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_search :
                lvMovie.setAdapter(null);
                dataMovie = new ArrayList<>();
                String keyword = etSearch.getText().toString();
                String url = "https://api.themoviedb.org/3/search/movie?api_key="+ BuildConfig.TMDB_API+"&language=en-US&query="+keyword;
                AsyncHttpClient client = new AsyncHttpClient();
                client.get(url, new AsyncHttpResponseHandler() {
                    @Override
                    public void onStart() {
                        etSearch.onEditorAction(EditorInfo.IME_ACTION_DONE);
                         loading.setVisibility(View.VISIBLE);
                        super.onStart();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                       String result = new String(responseBody);
                       try{
                           JSONObject responseObject = new JSONObject(result);
                           JSONArray list = responseObject.getJSONArray("results");
                           for(int i=0;i<list.length();i++){
                               JSONObject infoMovie = list.getJSONObject(i);
                               Movie movie = new Movie(infoMovie.getString("title"),infoMovie.getString("release_date"),infoMovie.getString("overview"),infoMovie.getString("poster_path"));
                               dataMovie.add(movie);
                           }
                       } catch (JSONException e) {
                           e.printStackTrace();
                       }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }

                    @Override
                    public void onFinish() {
                        loading.setVisibility(View.GONE);
                        notifyDataChange();
                        super.onFinish();
                    }
                });
                break;

        }
    }

    private void notifyDataChange() {
        movieListAdapter=new MovieListAdapter(dataMovie,this);
        lvMovie.setAdapter(movieListAdapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("listview.state", (ArrayList<? extends Parcelable>) dataMovie);
    }
}
