package com.viatorfortis.guideme.ui;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.viatorfortis.guideme.R;
import com.viatorfortis.guideme.model.MTGObject;
import com.viatorfortis.guideme.rv.SearchResultAdapter;
import com.viatorfortis.guideme.utils.SearchRegionsTask;
import com.viatorfortis.guideme.model.Region;

import java.util.ArrayList;

public class SearchByNameModeActivity extends AppCompatActivity {

    private EditText mSearchEditText;

    private SearchResultAdapter mSearchResultAdapter;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_name_mode);

        Toolbar appBar = findViewById(R.id.tb_search_by_name);
        setSupportActionBar(appBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Text search");
        }

        RecyclerView recyclerView = findViewById(R.id.rv_search_result);

        GridLayoutManager gridLayoutManager= new GridLayoutManager (this, 1);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);

        ArrayList<Region> regionList = new ArrayList<Region>();
        ArrayList<MTGObject> mtgObjectList = new ArrayList<MTGObject>();
        mSearchResultAdapter = new SearchResultAdapter(regionList, mtgObjectList);
        recyclerView.setAdapter(mSearchResultAdapter);

        mSearchEditText = findViewById(R.id.tv_searchField);
        mSearchEditText.addTextChangedListener(new SearchTextWatcher(mSearchEditText) );
        mSearchEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= v.getRight() - ((EditText) v).getCompoundDrawables()[2].getBounds().width() ) {
                        ((EditText) v).setText("");
                        return true;
                    }
                }
                return false;
            }
        });
        mSearchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    startSearch();
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    private void startSearch() {
        mSearchResultAdapter.clear();

        SearchRegionsTask searchRegionsTask = new SearchRegionsTask(SearchByNameModeActivity.this, mSearchResultAdapter);
        String [] searchParameters = {"any",
                SearchByNameModeActivity.this.mSearchEditText.getText().toString()};
        searchRegionsTask.execute(searchParameters);
    }

}
