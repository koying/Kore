package org.xbmc.kore.presenter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.Presenter;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.xbmc.kore.R;
import org.xbmc.kore.host.HostManager;
import org.xbmc.kore.model.Media;
import org.xbmc.kore.utils.UIUtils;

/**
 * Created by Christian on 14-03-15.
 */
public class MoviePresenter extends Presenter {
    private static final String TAG = "MoviePresenter";
    private HostManager hostManager;
    private int artWidth, artHeight;

    public MoviePresenter(Context ctx) {
        // Get the art dimensions
        Resources resources = ctx.getResources();
        artWidth = (int)(resources.getDimension(R.dimen.movielist_art_width) /
                UIUtils.IMAGE_RESIZE_FACTOR);
        artHeight = (int)(resources.getDimension(R.dimen.movielist_art_heigth) /
                UIUtils.IMAGE_RESIZE_FACTOR);

        hostManager = HostManager.getInstance(ctx);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        Log.d(TAG, "onCreateViewHolder");

        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_item_movie_lb, parent, false);

        // Setup View holder pattern
        MovieViewHolder viewHolder = new MovieViewHolder();
        viewHolder.titleView = (TextView)view.findViewById(R.id.title);
        viewHolder.detailsView = (TextView)view.findViewById(R.id.details);
//            viewHolder.yearView = (TextView)view.findViewById(R.id.year);
        viewHolder.durationView = (TextView)view.findViewById(R.id.duration);
        viewHolder.artView = (ImageView)view.findViewById(R.id.art);

        view.setTag(viewHolder);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object o) {
        Log.d(TAG, "onBindViewHolder");

        final MovieViewHolder movieHolder = (MovieViewHolder)viewHolder.view.getTag();
        final Media movie = (Media) o;
        final Context context = viewHolder.view.getContext();

        // Save the movie id
        movieHolder.movieId = movie.getId();
        movieHolder.movieTitle = movie.getTitle();

        movieHolder.titleView.setText(movieHolder.movieTitle);
        String details = TextUtils.isEmpty(movie.getDescription()) ?
                movie.getCategory() :
                movie.getDescription();
        movieHolder.detailsView.setText(details);
//            viewHolder.yearView.setText(String.valueOf(cursor.getInt(MovieListQuery.YEAR)));
        int runtime = Integer.parseInt(movie.getRuntime()) / 60;
        String duration =  runtime > 0 ?
                String.format(context.getString(R.string.minutes_abbrev), String.valueOf(runtime)) +
                        "  |  " + movie.getYear() :
                movie.getYear();
        movieHolder.durationView.setText(duration);
        UIUtils.loadImageWithCharacterAvatar(context, hostManager,
                movie.getCardImageUrl(), movieHolder.movieTitle,
                movieHolder.artView, artWidth, artHeight);
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {
        Log.d(TAG, "onUnbindViewHolder");
        final MovieViewHolder movieHolder = (MovieViewHolder)viewHolder.view.getTag();
        // TODO: Remove references to images so that the garbage collector can free up memory
    }

    /**
     * View holder pattern
     */
    private static class MovieViewHolder {
        TextView titleView;
        TextView detailsView;
        //        TextView yearView;
        TextView durationView;
        ImageView artView;

        int movieId;
        String movieTitle;
    }

}
