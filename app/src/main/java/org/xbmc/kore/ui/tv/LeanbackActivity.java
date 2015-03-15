package org.xbmc.kore.ui.tv;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.transition.TransitionInflater;
import android.view.Window;

import org.xbmc.kore.R;
import org.xbmc.kore.host.HostManager;
import org.xbmc.kore.model.Media;
import org.xbmc.kore.service.LibrarySyncService;
import org.xbmc.kore.ui.BaseActivity;
import org.xbmc.kore.ui.MovieDetailsFragment;
import org.xbmc.kore.ui.MovieListFragment;
import org.xbmc.kore.ui.hosts.AddHostActivity;
import org.xbmc.kore.utils.Utils;

/**
 * Created by cbro on 3/13/15.
 */

/*
 * MainActivity class that loads MainFragment
 */
public class LeanbackActivity extends BaseActivity
        implements LeanbackMainFragment.OnMediaSelectedListener {

    public static final String SHARED_ELEMENT_NAME = "hero";
    public static final String MEDIA = "Media";
    public static final String NOTIFICATION_ID = "NotificationId";

    public static Media getSelectedMedia() {
        return mSelectedMedia;
    }

    private static Media mSelectedMedia;

    /**
     * Host manager singleton
     */
    private HostManager hostManager = null;

    /**
     * Called when the activity is first created.
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Request transitions on lollipop
        if (Utils.isLollipopOrLater()) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_media);

        if (savedInstanceState == null) {
            LeanbackMainFragment lbFragment = new LeanbackMainFragment();

            // Setup animations
            if (Utils.isLollipopOrLater()) {
                lbFragment.setExitTransition(null);
                lbFragment.setReenterTransition(TransitionInflater
                        .from(this)
                        .inflateTransition(android.R.transition.fade));
            }
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, lbFragment)
                    .commit();
        }

        hostManager = HostManager.getInstance(this);
        // Check if we have any hosts setup
        if (hostManager.getHostInfo() == null) {
            final Intent intent = new Intent(this, AddHostActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("nextActivity", LeanbackActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        Intent syncIntent = new Intent(this, LibrarySyncService.class);
        syncIntent.putExtra(LibrarySyncService.SYNC_RECENT_MOVIES, true);
        startService(syncIntent);
    }

    /**
     * Callback from fragment when a movie is selected.
     * Switch fragment in portrait
     * @param media Media selected
     */
    @TargetApi(21)
    public void onMediaSelected(Media media) {
        mSelectedMedia = media;

        MovieDetailsFragment movieDetailsFragment = MovieDetailsFragment.newInstance(media.getId());
        FragmentTransaction fragTrans = getFragmentManager().beginTransaction();

        // Set up transitions
        if (Utils.isLollipopOrLater()) {
            movieDetailsFragment.setEnterTransition(TransitionInflater
                    .from(this)
                    .inflateTransition(R.transition.media_details));
            movieDetailsFragment.setReturnTransition(null);
        } else {
            fragTrans.setCustomAnimations(R.anim.fragment_details_enter, 0,
                    R.anim.fragment_list_popenter, 0);
        }

        fragTrans.replace(R.id.fragment_container, movieDetailsFragment)
                .addToBackStack(null)
                .commit();
    }

    /*
    @Override
    public boolean onSearchRequested() {
        startActivity(new Intent(this, SearchActivity.class));
        return true;
    }
    */
}
