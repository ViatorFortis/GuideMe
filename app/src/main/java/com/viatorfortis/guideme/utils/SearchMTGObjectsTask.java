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

public class SearchMTGObjectsTask extends AsyncTask <String, Void, String> {

    private final String Tag = this.getClass().getSimpleName();
    private Context context;

    private SearchResultAdapter mSearchResultAdapter;

    public SearchMTGObjectsTask(Context context, SearchResultAdapter searchResultAdapter) {
        super();

        this.context = context;
        this.mSearchResultAdapter = searchResultAdapter;
    }

    @Override
    protected String doInBackground(String... searchParameters) {
        try {
            return IziTravelApi.getMuseumAndTourListBySearchJson(context, searchParameters[0], searchParameters[1], searchParameters[2], searchParameters[3]);
        } catch (IOException e) {
            Log.e(Tag, e.getMessage() );
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        //Toast.makeText(context, "getMuseumAndTourListBySearchJson returns " + result, Toast.LENGTH_LONG).show();
        List<MTGObject> mtgObjectList = JsonUtils.parseMTGObjectListJson(result);

        mSearchResultAdapter.addMTGObjectList( (ArrayList<MTGObject>) mtgObjectList);
    }
}