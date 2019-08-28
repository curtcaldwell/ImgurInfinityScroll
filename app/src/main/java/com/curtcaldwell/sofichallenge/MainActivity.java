package com.curtcaldwell.sofichallenge;


import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.curtcaldwell.sofichallenge.model.CustomDisplayItem;


public class MainActivity extends AppCompatActivity {

    private PicAdapter adapter;
    public LinearLayoutManager linearLayoutManager;
    public static String input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        ProgressBar progressBar = findViewById(R.id.progress_bar);
        adapter = new PicAdapter(new DiffUtil.ItemCallback<CustomDisplayItem>() {
            @Override
            public boolean areItemsTheSame(@NonNull CustomDisplayItem oldItem, @NonNull CustomDisplayItem newItem) {
                return false;
            }

            @Override
            public boolean areContentsTheSame(@NonNull CustomDisplayItem oldItem, @NonNull CustomDisplayItem newItem) {
                return false;
            }
        }, new PictureClickListener() {
            @Override
            public void onPictureClicked(CustomDisplayItem item) {
                startPictureDetailsActivity(item);

            }
        });

        recyclerView.setLayoutManager(layoutManager);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.search)
                .getActionView();
        if (null != searchView) {
            searchView.setSearchableInfo(searchManager
                    .getSearchableInfo(getComponentName()));
            searchView.setIconifiedByDefault(true);
            searchView.setFocusable(true);
            searchView.setIconified(false);
            searchView.requestFocusFromTouch();
        }

        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String newText) {
                return true;
            }

            public boolean onQueryTextSubmit(String query) {
                input = query;
                final PagingViewModel pagingViewModel = new PagingViewModel(new RetroFitService(), query);
                pagingViewModel.getLiveData().observe(MainActivity.this, new Observer<PagedList<CustomDisplayItem>>() {
                    @Override
                    public void onChanged(PagedList<CustomDisplayItem> customDisplayItems) {
                        adapter.submitList(customDisplayItems);
                        hideKeyboard(MainActivity.this);

                    }
                });
                return true;
            }
        };
        searchView.setOnQueryTextListener(queryTextListener);

        return true;
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public interface PictureClickListener {
        void onPictureClicked(CustomDisplayItem item);
    }

    public void startPictureDetailsActivity(CustomDisplayItem item) {
        Intent intent = new Intent(MainActivity.this, PictureDetailsActivity.class);
        intent.putExtra("title", item.getTitle());
        intent.putExtra("link", item.getLink());
        intent.putExtra("description", item.getDescription());
        startActivity(intent);
    }
}


