package com.example.hp.mybrowser.activitys;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import com.example.hp.mybrowser.R;
import com.example.hp.mybrowser.fragments.HistoryFragment;
import com.example.hp.mybrowser.fragments.WebFragment;
import com.example.hp.mybrowser.providers.OperationsProvider;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private HistoryFragment historyFragment;
    private FragmentManager fragmentManager;
    private WebFragment webFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        webView = findViewById(R.id.web);
        historyFragment = new HistoryFragment();
        webFragment = new WebFragment();
        fragmentManager = getSupportFragmentManager();
        setFragment(webFragment);

        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                webFragment.addWeb(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_search:

                return true;
            case R.id.action_bookmarks:
                setFragment(historyFragment);
                return true;
            case R.id.action_history:
                setFragment(historyFragment);
                return true;
            case R.id.action_add:
                OperationsProvider.bookmarkList.add(OperationsProvider.historyList.get(OperationsProvider.historyList.size() - 1));
                return true;

        }

        return super.onOptionsItemSelected(item);
    }


    private void setFragment(Fragment fragment){
        fragmentManager.beginTransaction().replace(R.id.container_operations, fragment).addToBackStack(null).commit();
    }

}
