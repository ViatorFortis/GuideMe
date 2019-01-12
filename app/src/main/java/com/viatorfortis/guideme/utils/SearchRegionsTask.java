package com.viatorfortis.guideme.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.viatorfortis.guideme.model.Region;
import com.viatorfortis.guideme.rv.SearchResultAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchRegionsTask extends AsyncTask <String, Void, String> {

    private final String Tag = "SearchRegionsTask";
    private Context context;
    private String mLanguages;
    private String mQuery;
    private String mSortingType;
    private String mSortingOrder;

    private SearchResultAdapter mSearchResultAdapter;

    public SearchRegionsTask(Context context, SearchResultAdapter searchResultAdapter) {
        super();

        this.context = context;
        this.mSearchResultAdapter = searchResultAdapter;
    }

    @Override
    protected String doInBackground(String... searchParameters) {

        mLanguages = searchParameters[0];
        mQuery = searchParameters[1];
        mSortingType = searchParameters[2];
        mSortingOrder = searchParameters[3];

        try {
            return IziTravelApi.getRegionListJson(context, mLanguages, mQuery);
        } catch (IOException e) {
            Log.e(Tag, e.getMessage() );
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        //Toast.makeText(context, "getRegionListJson() returns " + result, Toast.LENGTH_LONG).show();
        List<Region> regionList = JsonUtils.parseRegionListJson(result);


        mSearchResultAdapter.addRegionList((ArrayList<Region>) regionList);

        String searchParameters[] = {mLanguages, mQuery, mSortingType, mSortingOrder};

        SearchMTGObjectsTask searchMTGObjectsTask = new SearchMTGObjectsTask(context, mSearchResultAdapter);
        searchMTGObjectsTask.execute(searchParameters);
    }
}
