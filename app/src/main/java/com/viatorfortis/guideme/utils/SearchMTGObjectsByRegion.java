package com.viatorfortis.guideme.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.viatorfortis.guideme.model.MTGObject;
import com.viatorfortis.guideme.rv.SearchResultAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchMTGObjectsByRegion extends AsyncTask<String, Void, String> {

    private final String Tag = this.getClass().getSimpleName();
    private Context context;
    private SearchResultAdapter mSearchResultAdapter;

    public SearchMTGObjectsByRegion(Context context, SearchResultAdapter searchResultAdapter) {
        this.context = context;
        mSearchResultAdapter = searchResultAdapter;
    }

    @Override
    protected String doInBackground(String... searchParameters) {
        try {
            return IziTravelApi.getMuseumAndTourListByRegionJson(context, searchParameters[0], searchParameters[1], searchParameters[2], searchParameters[3], searchParameters[4]);
        } catch (IOException e) {
            Log.e(Tag, e.getMessage() );
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        List<MTGObject> mtgObjectList = JsonUtils.parseMTGObjectListJson(result);
        mSearchResultAdapter.addMTGObjectList( (ArrayList<MTGObject>) mtgObjectList);

        Toast.makeText(context, "SearchMTGObjectsByRegion returns " + result, Toast.LENGTH_LONG).show();
    }


}
