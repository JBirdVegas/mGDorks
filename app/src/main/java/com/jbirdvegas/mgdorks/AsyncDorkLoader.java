package com.jbirdvegas.mgdorks;

import android.content.Context;
import android.os.AsyncTask;

import java.util.Collections;
import java.util.List;

public abstract class AsyncDorkLoader extends AsyncTask<Void, Void, List<Dork>> {
    private final Context mContext;

    public AsyncDorkLoader(Context context) {
        mContext = context;
    }

    @Override
    protected List<Dork> doInBackground(Void... params) {
        List<Dork> orderedDorksList = new GhdbParser().parse(mContext);
        // the list is ordered oldest first... lets show the latest first
        Collections.reverse(orderedDorksList);
        return orderedDorksList;
    }

    @Override
    protected abstract void onPostExecute(List<Dork> dorks);
}
