package com.viatorfortis.guideme.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.viatorfortis.guideme.R;
import com.viatorfortis.guideme.model.MTGObject;
import com.viatorfortis.guideme.rv.SearchResultAdapter;
import com.viatorfortis.guideme.utils.IziTravelApi;
import com.viatorfortis.guideme.utils.SearchMTGObjectsByRegion;
import com.viatorfortis.guideme.utils.SearchMTGObjectsTask;
import com.viatorfortis.guideme.model.Region;

import java.util.ArrayList;

public class SearchByNameModeActivity extends AppCompatActivity
        implements SearchResultAdapter.GridItemClickListener {

    private EditText mSearchEditText;

    private SearchResultAdapter mSearchResultAdapter;

    private IziTravelApi.SortingType mSortingType;

    private IziTravelApi.SortingOrder mSortingOrder;

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


        ArrayList<Region> regionList;

        if (savedInstanceState == null || !savedInstanceState.containsKey("RegionList") ) {
            regionList = new ArrayList<Region>();
        } else {
            regionList = savedInstanceState.getParcelableArrayList("RegionList");
        }

        ArrayList<MTGObject> mtgObjectList;

        if (savedInstanceState == null || !savedInstanceState.containsKey("MTGObjectList") ) {
            mtgObjectList = new ArrayList<MTGObject>();
        } else {
            mtgObjectList = savedInstanceState.getParcelableArrayList("MTGObjectList");
        }

        mSearchResultAdapter = new SearchResultAdapter(regionList, mtgObjectList, this);
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
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //v.clearFocus();
                    startSearch();

                    return false;
                } else {
                    return false;
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        try {
            outState.putParcelableArrayList("RegionList", mSearchResultAdapter.getRegionList() );
            outState.putParcelableArrayList("MTGObjectList", mSearchResultAdapter.getMTGObjectList() );
        } catch (Exception e) {
            Log.d(e.getClass().getName(), e.getMessage() );
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sort_types_menu, menu);
        menu.findItem(R.id.item_sort_by_name_asc).setChecked(true);
        mSortingType = IziTravelApi.SortingType.TITLE;
        mSortingOrder = IziTravelApi.SortingOrder.ASC;

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.setChecked(true);

        switch (item.getItemId()) {
            case R.id.item_sort_by_name_asc:
                mSortingType = IziTravelApi.SortingType.TITLE;
                mSortingOrder = IziTravelApi.SortingOrder.ASC;
                break;

            case R.id.item_sort_by_name_desc:
                mSortingType = IziTravelApi.SortingType.TITLE;
                mSortingOrder = IziTravelApi.SortingOrder.DESC;
                break;

            case R.id.item_sort_by_rating_asc:
                mSortingType = IziTravelApi.SortingType.RATING;
                mSortingOrder = IziTravelApi.SortingOrder.ASC;
                break;

            case R.id.item_sort_by_rating_desc:
                mSortingType = IziTravelApi.SortingType.RATING;
                mSortingOrder = IziTravelApi.SortingOrder.DESC;
                break;
        }

        startSearch();

        return super.onOptionsItemSelected(item);
    }

    private void startSearch() {
        mSearchResultAdapter.clear();

        SearchMTGObjectsTask searchMTGObjectsTask = new SearchMTGObjectsTask(SearchByNameModeActivity.this, mSearchResultAdapter);
        String [] searchParameters = {"en",
                SearchByNameModeActivity.this.mSearchEditText.getText().toString(),
                mSortingType.toString().toLowerCase(),
                mSortingOrder.toString().toLowerCase()
        };
        searchMTGObjectsTask.execute(searchParameters);
    }

    @Override
    public void onGridItemClick(Object object) {
        if (object instanceof Region) {
            mSearchResultAdapter.clear();

            SearchMTGObjectsByRegion searchMTGObjectsByRegion = new SearchMTGObjectsByRegion(SearchByNameModeActivity.this, mSearchResultAdapter);

            Region region = (Region) object;

            String[] searchParameters = {region.getType(),
                    region.getUuid(),
                    "en",
                    mSortingType.toString().toLowerCase(),
                    mSortingOrder.toString().toLowerCase()
            };

            searchMTGObjectsByRegion.execute(searchParameters);
        }

        if (object instanceof MTGObject) {
            MTGObject mtgObject = (MTGObject) object;

            Intent intent = new Intent(this, MTGObjectActivity.class);
            intent.putExtra(getString(R.string.mtgobject_parcel_key), mtgObject);

            startActivity(intent);
        }
    }
}
