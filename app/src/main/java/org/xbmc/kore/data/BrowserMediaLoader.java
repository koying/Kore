package org.xbmc.kore.data;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import org.xbmc.kore.model.Media;
import org.xbmc.kore.provider.BrowserMediaProvider;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Christian on 14-03-15.
 */
/*
 * This class asynchronously loads videos from a backend
 */
public class BrowserMediaLoader extends AsyncTaskLoader<HashMap<String, List<Media>>> {

    private static final String TAG = "BrowserMediaLoader";
    private Context mContext;

    public BrowserMediaLoader(Context context) {
        super(context);
        mContext = context;
        BrowserMediaProvider.setContext(context);
    }

    @Override
    public HashMap<String, List<Media>> loadInBackground() {
        try {
            return BrowserMediaProvider.buildMedia(mContext);
        } catch (Exception e) {
            Log.e(TAG, "Failed to fetch media data", e);
            return null;
        }
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    /**
     * Handles a request to stop the Loader.
     */
    @Override
    protected void onStopLoading() {
        // Attempt to cancel the current load task if possible.
        cancelLoad();
    }

}
