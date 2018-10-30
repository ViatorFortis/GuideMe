package com.viatorfortis.guideme.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.viatorfortis.guideme.R;
import com.viatorfortis.guideme.utils.SearchRegionsTask;

public class SearchByNameModeActivity extends AppCompatActivity {

    private Button searchButton;

    private TextView searchTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_name_mode);

        Toolbar appBar = findViewById(R.id.tb_search_by_name);
        setSupportActionBar(appBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Search by name activity");
        }


        searchTextView = findViewById(R.id.tv_searchField);

        String a = searchTextView.getText().toString();

        searchButton = findViewById(R.id.btn_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchRegionsTask searchRegionsTask = new SearchRegionsTask(SearchByNameModeActivity.this);
                String [] searchParameters = {"any",
                        SearchByNameModeActivity.this.searchTextView.getText().toString()};
                searchRegionsTask.execute(searchParameters);
            }
        });

    }



}
