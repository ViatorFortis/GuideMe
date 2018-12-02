package com.viatorfortis.guideme.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

public class SearchMTGObjectsTask extends AsyncTask <String, Void, String> {

    private final String Tag = this.getClass().getSimpleName();
    private Context context;

    public SearchMTGObjectsTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... searchParameters) {
        try {
            return IziTravelApi.getMuseumAndTourListBySearchJson(context, searchParameters[0], searchParameters[1]);
        } catch (IOException e) {
            Log.e(Tag, e.getMessage() );
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        Toast.makeText(context, "getMuseumAndTourListBySearchJson retuurns " + result, Toast.LENGTH_LONG).show();
    }
}