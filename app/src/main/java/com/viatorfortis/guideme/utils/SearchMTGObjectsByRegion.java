package com.viatorfortis.guideme.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import java.io.IOException;

public class SearchMTGObjectsByRegion extends AsyncTask<Object, Void, String> {

    private final String Tag = this.getClass().getSimpleName();
    private Context context;

    public SearchMTGObjectsByRegion(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Object... searchParameters) {
        try {
            return IziTravelApi.getMuseumAndTourListByRegionJson(context, (IziTravelApi.RegionType) searchParameters[0], (String) searchParameters[1], (String) searchParameters[2]);
        } catch (IOException e) {
            Log.e(Tag, e.getMessage() );
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        Toast.makeText(context, "SearchMTGObjectsByRegion returns " + result, Toast.LENGTH_LONG).show();
    }


}
