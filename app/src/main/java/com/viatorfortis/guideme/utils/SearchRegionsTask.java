package com.viatorfortis.guideme.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

public class SearchRegionsTask extends AsyncTask <String, Void, String> {

    private final String Tag = "SearchRegionsTask";
    private Context context;
    private String mLanguages;
    private String mQuery;

    public SearchRegionsTask(Context context) {
        super();

        this.context = context;
    }

    @Override
    protected String doInBackground(String... searchParameters) {

        mLanguages = searchParameters[0];
        mQuery = searchParameters[1];

        try {
            return IziTravelApi.getRegionListJson(context, searchParameters[0], searchParameters[1]);
        } catch (IOException e) {
            Log.e(Tag, e.getMessage() );
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        Toast.makeText(context, "getRegionListJson() returns " + result, Toast.LENGTH_LONG).show();

        String searchParameters[] = {mLanguages, mQuery};

        SearchMTGObjectsTask searchMTGObjectsTask = new SearchMTGObjectsTask(context);
        searchMTGObjectsTask.execute(searchParameters);
    }
}
