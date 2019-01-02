package com.viatorfortis.guideme.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.viatorfortis.guideme.R;
import com.viatorfortis.guideme.model.MTGObject;
import com.viatorfortis.guideme.rv.SearchResultAdapter;
import com.viatorfortis.guideme.utils.SearchRegionsTask;
import com.viatorfortis.guideme.model.Region;

import java.util.ArrayList;

public class SearchByNameModeActivity extends AppCompatActivity {

    private Button searchButton;

    private EditText mSearchEditText;

    private SearchResultAdapter mSearchResultAdapter;


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

        searchButton = findViewById(R.id.btn_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchResultAdapter.clear();

                SearchRegionsTask searchRegionsTask = new SearchRegionsTask(SearchByNameModeActivity.this, mSearchResultAdapter);
                String [] searchParameters = {"any",
                        SearchByNameModeActivity.this.mSearchEditText.getText().toString()};
                searchRegionsTask.execute(searchParameters);
            }
        });

    }



}
