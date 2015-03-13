package org.xbmc.kore.ui.tv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import org.xbmc.kore.R;

/**
 * Created by cbro on 3/13/15.
 */

/*
 * MainActivity class that loads MainFragment
 */
public class LeanbackActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leanback);
    }

    /*
    @Override
    public boolean onSearchRequested() {
        startActivity(new Intent(this, SearchActivity.class));
        return true;
    }
    */
}
