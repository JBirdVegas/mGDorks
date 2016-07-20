package com.jbirdvegas.mgdorks;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

public class ScrollingDorksActivity extends AppCompatActivity {
    private static final String TAG = ScrollingDorksActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling_dorks);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        // setup parser
        if (savedInstanceState == null) {
            new AsyncDorkLoader(this) {
                @Override
                protected void onPostExecute(List<Dork> dorks) {
                    Log.d(TAG, "Found " + dorks.size() + " dorks.");
                    mRecyclerView = (RecyclerView) findViewById(R.id.ghdb_listview);
                    if (mRecyclerView != null) {
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        mRecyclerView.setAdapter(new GhdbListAdapter(dorks, new DorkClickListener()));
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling_dorks, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class DorkClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Dork tag = (Dork) v.getTag();
            String uriString = "https://www.google.com/search?q=" + tag.getQueryString();

            Log.d(TAG, "Using dork: " + tag);
            Log.d(TAG, "Launching Url: " + tag.getQueryString());
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uriString));
            startActivity(browserIntent);
        }
    }
}
