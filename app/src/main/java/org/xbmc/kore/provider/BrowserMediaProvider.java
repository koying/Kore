package org.xbmc.kore.provider;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.content.CursorLoader;
import android.text.TextUtils;

import org.xbmc.kore.R;
import org.xbmc.kore.Settings;
import org.xbmc.kore.host.HostInfo;
import org.xbmc.kore.host.HostManager;
import org.xbmc.kore.model.Media;
import org.xbmc.kore.ui.MovieListFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Christian on 14-03-15.
 */
public class BrowserMediaProvider {

    private static HashMap<String, List<Media>> sMediaList;
    private static Context sContext;
    private static HostManager hostManager;
    private static HostInfo hostInfo;

    public BrowserMediaProvider() {
    }

    public static void setContext(Context context) {
        if (sContext == null)
            sContext = context;

        hostManager = HostManager.getInstance(sContext);
        hostInfo = hostManager.getHostInfo();
    }

    public static HashMap<String,List<Media>> buildMedia(Context ctx) {
        if (null != sMediaList) {
            return sMediaList;
        }
        sMediaList = new HashMap<String, List<Media>>();
        ArrayList<Media> recentMovies = new ArrayList<Media>();
        ArrayList<Media> recentEpisodes = new ArrayList<Media>();

        StringBuilder selection = new StringBuilder();
        String selectionArgs[] = null;
        String sortOrderStr = "";

        Uri uri = MediaContract.MoviesRecent.buildRecentMoviesListUri(hostInfo != null ? hostInfo.getId() : -1);
        Cursor cursor = sContext.getContentResolver().query(uri, MovieListFragment.MovieListQuery.PROJECTION, selection.toString(),
                selectionArgs, sortOrderStr);
        if (cursor != null) {
            boolean ok = cursor.moveToFirst();
            while(ok) {
                Media media = new Media();
                media.setId(cursor.getString(MovieListFragment.MovieListQuery.MOVIEID));
                media.setTitle(cursor.getString(MovieListFragment.MovieListQuery.TITLE));
                media.setDescription(cursor.getString(MovieListFragment.MovieListQuery.TAGLINE));
                media.setCardImageUrl(hostManager.getHostInfo().getImageUrl(cursor.getString(MovieListFragment.MovieListQuery.THUMBNAIL)));
                media.setBackgroundImageUrl(hostManager.getHostInfo().getImageUrl(cursor.getString(MovieListFragment.MovieListQuery.FANART)));
                media.setRuntime(cursor.getString(MovieListFragment.MovieListQuery.RUNTIME));
                media.setYear(cursor.getString(MovieListFragment.MovieListQuery.YEAR));
                recentMovies.add(media);

                ok = cursor.moveToNext();
            }
            cursor.close();
        }

        sMediaList.put(sContext.getResources().getString(R.string.recent_movies), recentMovies);
        sMediaList.put(sContext.getResources().getString(R.string.recent_episodes), recentEpisodes);

        return sMediaList;
    }

    public static HashMap<String, List<Media>> getMediaList() {
        return sMediaList;
    }
}