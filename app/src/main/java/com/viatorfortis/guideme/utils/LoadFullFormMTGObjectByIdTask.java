package com.viatorfortis.guideme.utils;

import android.os.AsyncTask;
import android.content.Context;
import android.util.Log;
import java.io.IOException;

public class LoadFullFormMTGObjectByIdTask extends AsyncTask <String, Void, String> {

    private final String tag = this.getClass().getSimpleName();

    private Context mContext;

    public interface OnTaskCompleteListener {
        void onTaskComplete(String result);
    }


    public LoadFullFormMTGObjectByIdTask (Context context) {
        if (! (context instanceof OnTaskCompleteListener) ) {
            throw new ClassCastException(context.toString()
                    + " doesn't implement "
                    + OnTaskCompleteListener.class.getName() );
        }

        mContext = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... loadParameters) {
        try {
            return IziTravelApi.getFullFormMTGObjectByIdJson(mContext, loadParameters[0], loadParameters[1]);
        } catch (IOException e) {
            Log.d(tag, e.getMessage() );
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        ( (OnTaskCompleteListener) mContext).onTaskComplete(result);

//        try {
//            mFullFormMTGObject = new Gson().fromJson(result, FullFormMTGObject.class);
//        } catch (JsonSyntaxException e) {
//            Log.d(tag, e.getMessage() );
//        }
    }
}
