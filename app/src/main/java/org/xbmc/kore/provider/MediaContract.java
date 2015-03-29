/*
 * Copyright 2015 Synced Synapse. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.xbmc.kore.provider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Contract class for interacting with {@link MediaProvider}.
 */
public class MediaContract {

    public static final String CONTENT_AUTHORITY = "org.xbmc.kore.provider";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /**
     * Paths to tables
     */
    public static final String PATH_HOSTS = "hosts";
    public static final String PATH_MOVIES = "movies";
    public static final String PATH_RECENT_MOVIES = "recent_movies";
    public static final String PATH_MOVIE_CAST = "movie_cast";
    public static final String PATH_TVSHOWS = "tvshows";
    public static final String PATH_TVSHOW_CAST = "tvshow_cast";
    public static final String PATH_SEASONS = "seasons";
    public static final String PATH_EPISODES = "episodes";
    public static final String PATH_ARTISTS = "artists";
    public static final String PATH_ALBUMS = "albums";
    public static final String PATH_AUDIO_GENRES = "audio_genres";
    public static final String PATH_SONGS = "songs";
    public static final String PATH_ALBUM_ARTISTS = "album_artists";
    public static final String PATH_ALBUM_GENRES = "album_genres";
    public static final String PATH_MUSIC_VIDEOS = "music_videos";

    /** Last time this entry was updated or synchronized. */
    public interface SyncColumns {
        String UPDATED = "updated";
    }

    /**
     * Columns for table HOSTS
     */
    public interface HostsColumns {
        public final static String NAME = "name";
        public final static String ADDRESS = "address";
        public final static String PROTOCOL = "protocol";
        public final static String HTTP_PORT = "http_port";
        public final static String TCP_PORT = "tcp_port";
        public final static String USERNAME = "username";
        public final static String PASSWORD = "password";
        public final static String MAC_ADDRESS = "mac_address";
        public final static String WOL_PORT = "wol_port";
    }

    public static class Hosts implements BaseColumns, SyncColumns, HostsColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_HOSTS).build();

        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/vnd.org.xbmc." + PATH_HOSTS;
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/vnd.org.xbmc." + PATH_HOSTS;

        /** Build {@link Uri} for requested {@link #_ID}. */
        public static Uri buildHostUri(long hostId) {
            return CONTENT_URI.buildUpon().appendPath(String.valueOf(hostId)).build();
        }

        /** Read {@link #_ID} from {@link Hosts} {@link Uri}. */
        public static String getHostId(Uri uri) {
            return uri.getPathSegments().get(1);
        }

        public final static String[] ALL_COLUMNS = {
                _ID, UPDATED, NAME, ADDRESS, PROTOCOL, HTTP_PORT, TCP_PORT, USERNAME, PASSWORD,
                MAC_ADDRESS, WOL_PORT
        };
    }

    /**
     * Columns for table Movies
     * For XBMC reference/unique key use HOST_ID + MOVIEID
     */
    public interface MoviesColumns {
        public final static String HOST_ID = "host_id";
        public final static String MOVIEID = "movieid";

        public final static String FANART = "fanart";
        public final static String THUMBNAIL = "thumbnail";
        public final static String PLAYCOUNT = "playcount";
        public final static String TITLE = "title";
        public final static String FILE = "file";
        public final static String PLOT = "plot";
        public final static String DIRECTOR = "director";
        public final static String RUNTIME = "runtime";
        public final static String AUDIO_CHANNELS = "audio_channels";
        public final static String AUDIO_CODEC = "audio_coded";
        public final static String AUDIO_LANGUAGE = "audio_language";
        public final static String SUBTITLES_LANGUAGES = "subtitles_languages";
        public static final String VIDEO_ASPECT = "video_aspect";
        public static final String VIDEO_CODEC = "video_codec";
        public static final String VIDEO_HEIGHT = "video_height";
        public static final String VIDEO_WIDTH = "video_width";
        public static final String COUNTRIES = "countries";
        public static final String GENRES = "genres";
        public static final String IMDBNUMBER = "imdbnumber";
        public static final String MPAA = "mpaa";
        public static final String RATING = "rating";
        public static final String SET = "movie_set";
        public static final String SETID = "setid";
        public static final String STUDIOS = "studios";
        public static final String TAGLINE = "tagline";
        public static final String TOP250 = "top250";
        public static final String TRAILER = "trailer";
        public static final String VOTES = "votes";
        public static final String WRITERS = "writers";
        public static final String YEAR = "year";
        public static final String DATEADDED = "dateadded";
    }

    public static class Movies implements BaseColumns, SyncColumns, MoviesColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIES).build();

        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/vnd.org.xbmc." + PATH_MOVIES;
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/vnd.org.xbmc." + PATH_MOVIES;

        /** Build {@link Uri} for movies list. */
        public static Uri buildMoviesListUri(long hostId) {
            return Hosts.buildHostUri(hostId).buildUpon()
                        .appendPath(PATH_MOVIES)
                        .build();
        }

        /** Build {@link Uri} for requested {@link #_ID}. */
        public static Uri buildMovieUri(long hostId, long movieId) {
            return Hosts.buildHostUri(hostId).buildUpon()
                        .appendPath(PATH_MOVIES)
                        .appendPath(String.valueOf(movieId))
                        .build();
        }

        /** Read {@link #_ID} from {@link Movies} {@link Uri}. */
        public static String getMovieId(Uri uri) {
            return uri.getPathSegments().get(3);
        }

        public final static String[] ALL_COLUMNS = {
                _ID, UPDATED, HOST_ID, MOVIEID, FANART, THUMBNAIL, PLAYCOUNT, TITLE, FILE, PLOT,
                DIRECTOR, RUNTIME, AUDIO_CHANNELS, AUDIO_CODEC, AUDIO_LANGUAGE,
                SUBTITLES_LANGUAGES, VIDEO_ASPECT, VIDEO_CODEC, VIDEO_HEIGHT, VIDEO_WIDTH,
                COUNTRIES, GENRES, IMDBNUMBER, MPAA, RATING, SET, SETID, STUDIOS, TAGLINE,
                TOP250, TRAILER, VOTES, WRITERS, YEAR, DATEADDED
        };
    }

    public static class MoviesRecent implements BaseColumns, SyncColumns, MoviesColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_RECENT_MOVIES).build();

        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/vnd.syncedsynapse." + PATH_RECENT_MOVIES;
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/vnd.syncedsynapse." + PATH_RECENT_MOVIES;

        /** Build {@link Uri} for recent movies. */
        public static Uri buildRecentMoviesListUri(long hostId) {
            return Hosts.buildHostUri(hostId).buildUpon()
                    .appendPath(PATH_RECENT_MOVIES)
                    .build();
        }

        /** Read {@link #_ID} from {@link Movies} {@link Uri}. */
        public static String getMovieId(Uri uri) {
            return uri.getPathSegments().get(3);
        }

        public final static String[] ALL_COLUMNS = {
                _ID, UPDATED, HOST_ID, MOVIEID, FANART, THUMBNAIL, PLAYCOUNT, TITLE, FILE, PLOT,
                DIRECTOR, RUNTIME, AUDIO_CHANNELS, AUDIO_CODEC, AUDIO_LANGUAGE,
                SUBTITLES_LANGUAGES, VIDEO_ASPECT, VIDEO_CODEC, VIDEO_HEIGHT, VIDEO_WIDTH,
                COUNTRIES, GENRES, IMDBNUMBER, MPAA, RATING, SET, SETID, STUDIOS, TAGLINE,
                TOP250, TRAILER, VOTES, WRITERS, YEAR, DATEADDED
        };
    }

    /**
     * Columns for MovieCast table
     * For XBMC reference/unique key use HOST_ID + MOVIEID + NAME
     */
    public interface MovieCastColumns {
        public final static String HOST_ID = "host_id";
        public final static String MOVIEID = "movieid";
        public final static String NAME = "name";

        public final static String ORDER = "cast_order";
        public final static String ROLE = "role";
        public final static String THUMBNAIL = "thumbnail";
    }

    public static class MovieCast implements BaseColumns, SyncColumns, MovieCastColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIE_CAST).build();

        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/vnd.org.xbmc." + PATH_MOVIE_CAST;
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/vnd.org.xbmc." + PATH_MOVIE_CAST;

        /** Build {@link Uri} for requested {@link #_ID}. */
        public static Uri buildMovieCastListUri(long hostId, long movieId) {
            return Movies.buildMovieUri(hostId, movieId).buildUpon()
                        .appendPath(PATH_MOVIE_CAST)
                        .build();
        }
    }

    /**
     * Columns for table TVShows
     * For XBMC reference use HOST_ID + TVSHOWID
     */
    public interface TVShowsColumns {
        public final static String HOST_ID = "host_id";
        public final static String TVSHOWID = "tvshowid";

        public static final String FANART = "fanart";
        public static final String THUMBNAIL = "thumbnail";
        public static final String PLAYCOUNT = "playcount";
        public static final String TITLE = "title";
        public static final String DATEADDED = "dateadded";
        public static final String FILE = "file";
        public static final String PLOT = "plot";
        public static final String EPISODE = "episode";
        public static final String IMDBNUMBER = "imdbnumber";
        public static final String MPAA = "mpaa";
        public static final String PREMIERED = "premiered";
        public static final String RATING = "rating";
        public static final String STUDIO = "studio";
        public static final String WATCHEDEPISODES = "watchedepisodes";
        public static final String GENRES = "genres";
    }

    public static class TVShows implements BaseColumns, SyncColumns, TVShowsColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_TVSHOWS).build();

        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/vnd.org.xbmc." + PATH_TVSHOWS;
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/vnd.org.xbmc." + PATH_TVSHOWS;

        /** Build {@link Uri} for tvshows list. */
        public static Uri buildTVShowsListUri(long hostId) {
            return Hosts.buildHostUri(hostId).buildUpon()
                        .appendPath(PATH_TVSHOWS)
                        .build();
        }

        /** Build {@link Uri} for requested {@link #_ID}. */
        public static Uri buildTVShowUri(long hostId, long tvshowId) {
            return Hosts.buildHostUri(hostId).buildUpon()
                        .appendPath(PATH_TVSHOWS)
                        .appendPath(String.valueOf(tvshowId))
                        .build();
        }

        /** Read {@link #_ID} from {@link TVShows} {@link Uri}. */
        public static String getTVShowId(Uri uri) {
            return uri.getPathSegments().get(3);
        }

        public final static String[] ALL_COLUMNS = {
                _ID, UPDATED, HOST_ID, TVSHOWID, FANART, THUMBNAIL, PLAYCOUNT, TITLE, DATEADDED,
                FILE, PLOT, EPISODE, IMDBNUMBER, MPAA, PREMIERED, RATING, STUDIO,
                WATCHEDEPISODES, GENRES
        };
    }

    /**
     * Columns for TVShowCast table
     * For XBMC reference/unique key use HOST_ID + TVSHOWID + NAME
     */
    public interface TVShowCastColumns {
        public final static String HOST_ID = "host_id";
        public final static String TVSHOWID = "tvshowid";
        public final static String NAME = "name";

        public final static String ORDER = "cast_order";
        public final static String ROLE = "role";
        public final static String THUMBNAIL = "thumbnail";
    }

    public static class TVShowCast implements BaseColumns, SyncColumns, TVShowCastColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_TVSHOW_CAST).build();

        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/vnd.org.xbmc." + PATH_TVSHOW_CAST;
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/vnd.org.xbmc." + PATH_TVSHOW_CAST;

        /** Build {@link Uri} for requested {@link #_ID}. */
        public static Uri buildTVShowCastListUri(long hostId, long tvshowId) {
            return TVShows.buildTVShowUri(hostId, tvshowId).buildUpon()
                         .appendPath(PATH_TVSHOW_CAST)
                         .build();
        }
    }

    /**
     * Columns for Seasons table
     * For XBMC reference/unique key use HOST_ID + TVSHOWID + SEASON
     */
    public interface SeasonsColumns {
       public final static String HOST_ID = "host_id";
        public final static String TVSHOWID = "tvshowid";
        public static final String SEASON = "season";

        public static final String LABEL = "label";
        public static final String FANART = "fanart";
        public static final String THUMBNAIL = "thumbnail";
        public static final String EPISODE = "episode";
        public static final String SHOWTITLE = "showtitle";
        public static final String WATCHEDEPISODES = "watchedepisodes";
    }

    public static class Seasons implements BaseColumns, SyncColumns, SeasonsColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_SEASONS).build();

        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/vnd.org.xbmc." + PATH_SEASONS;
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/vnd.org.xbmc." + PATH_SEASONS;


        /** Build {@link Uri} for requested {@link #_ID}. */
        public static Uri buildTVShowSeasonsListUri(long hostId, long tvshowId) {
            return TVShows.buildTVShowUri(hostId, tvshowId).buildUpon()
                          .appendPath(PATH_SEASONS)
                          .build();
        }

        /** Build {@link Uri} for requested {@link #_ID}. */
        public static Uri buildTVShowSeasonUri(long hostId, long tvshowId, long season) {
            return TVShows.buildTVShowUri(hostId, tvshowId).buildUpon()
                        .appendPath(PATH_SEASONS)
                        .appendPath(String.valueOf(season))
                        .build();
        }

        /** Read {@link #_ID} from {@link Seasons} {@link Uri}. */
        public static String getTVShowSeasonId(Uri uri) {
            return uri.getPathSegments().get(5);
        }

        public final static String[] ALL_COLUMNS = {
                _ID, UPDATED, HOST_ID, TVSHOWID, SEASON, LABEL, FANART, THUMBNAIL, EPISODE,
                SHOWTITLE, WATCHEDEPISODES,
        };
    }

    /**
     * Columns for Episodes table
     * For XBMC reference/unique key use HOST_ID + EPISODEID
     */
    public interface EpisodesColumns {
        public final static String HOST_ID = "host_id";
        public static final String EPISODEID = "episodeid";

        public final static String TVSHOWID = "tvshowid";
        public static final String SEASON = "season";
        public static final String EPISODE = "episode";

        public static final String FANART = "fanart";
        public static final String THUMBNAIL = "thumbnail";
        public static final String PLAYCOUNT = "playcount";
        public static final String TITLE = "title";
        public static final String DATEADDED = "dateadded";
        public static final String FILE = "file";
        public static final String PLOT = "plot";
        public static final String DIRECTOR = "director";
        public static final String RUNTIME = "runtime";
        public static final String FIRSTAIRED = "firstaired";
        public static final String RATING = "rating";
        public static final String SHOWTITLE = "showtitle";
        public static final String WRITER = "writer";
        public final static String AUDIO_CHANNELS = "audio_channels";
        public final static String AUDIO_CODEC = "audio_coded";
        public final static String AUDIO_LANGUAGE = "audio_language";
        public final static String SUBTITLES_LANGUAGES = "subtitles_languages";
        public static final String VIDEO_ASPECT = "video_aspect";
        public static final String VIDEO_CODEC = "video_codec";
        public static final String VIDEO_HEIGHT = "video_height";
        public static final String VIDEO_WIDTH = "video_width";
    }

    public static class Episodes implements BaseColumns, SyncColumns, EpisodesColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_EPISODES).build();

        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/vnd.org.xbmc." + PATH_EPISODES;
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/vnd.org.xbmc." + PATH_EPISODES;

        /** Build {@link Uri} for tvshows list. */
        public static Uri buildTVShowEpisodesListUri(long hostId, long tvshowId) {
            return TVShows.buildTVShowUri(hostId, tvshowId).buildUpon()
                          .appendPath(PATH_EPISODES)
                          .build();
        }

        /** Build {@link Uri} for tvshows for a season list. */
        public static Uri buildTVShowSeasonEpisodesListUri(long hostId, long tvshowId, long season) {
            return Seasons.buildTVShowSeasonUri(hostId, tvshowId, season).buildUpon()
                          .appendPath(PATH_EPISODES)
                          .build();
        }

        /** Build {@link Uri} for requested {@link #_ID}. */
        public static Uri buildTVShowEpisodeUri(long hostId, long tvshowId, long episodeId) {
            return TVShows.buildTVShowUri(hostId, tvshowId).buildUpon()
                          .appendPath(PATH_EPISODES)
                          .appendPath(String.valueOf(episodeId))
                          .build();
        }

        /** Build {@link Uri} for requested {@link #_ID}. */
        public static Uri buildTVShowSeasonEpisodeUri(long hostId, long tvshowId,
                                                      long season, long episodeId) {
            return Seasons.buildTVShowSeasonUri(hostId, tvshowId, season).buildUpon()
                          .appendPath(PATH_EPISODES)
                          .appendPath(String.valueOf(episodeId))
                          .build();
        }

        /** Read {@link #_ID} from {@link Episodes} {@link Uri}. */
        public static String getTVShowEpisodeId(Uri uri) {
            return uri.getPathSegments().get(5);
        }

        /** Read {@link #_ID} from {@link Episodes} {@link Uri}. */
        public static String getTVShowSeasonEpisodeId(Uri uri) {
            return uri.getPathSegments().get(7);
        }

        public final static String[] ALL_COLUMNS = {
                _ID, UPDATED, HOST_ID, EPISODEID, TVSHOWID, SEASON, EPISODE, FANART, THUMBNAIL,
                PLAYCOUNT, TITLE, DATEADDED, FILE, PLOT, DIRECTOR, RUNTIME, FIRSTAIRED, RATING,
                SHOWTITLE, WRITER, AUDIO_CHANNELS, AUDIO_CODEC, AUDIO_LANGUAGE,
                SUBTITLES_LANGUAGES, VIDEO_ASPECT, VIDEO_CODEC, VIDEO_HEIGHT, VIDEO_WIDTH,
        };
    }

    /**
     * Columns for Artists table
     * For XBMC reference/unique key use HOST_ID + ARTISTID
     */
    public interface ArtistsColumns {
        public final static String HOST_ID = "host_id";
        public static final String ARTISTID = "artistid";

        public static final String ARTIST = "artist";
        public final String DESCRIPTION = "description";
        public final String GENRE = "genre";
        public final String FANART = "fanart";
        public final String THUMBNAIL = "thumbnail";
    }

    public static class Artists implements BaseColumns, SyncColumns, ArtistsColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_ARTISTS).build();

        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/vnd.org.xbmc." + PATH_ARTISTS;
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/vnd.org.xbmc." + PATH_ARTISTS;

        /** Build {@link Uri} for artists list. */
        public static Uri buildArtistsListUri(long hostId) {
            return Hosts.buildHostUri(hostId).buildUpon()
                        .appendPath(PATH_ARTISTS)
                        .build();
        }

        /** Build {@link Uri} for requested {@link #_ID}. */
        public static Uri buildArtistUri(long hostId, long artistId) {
            return Hosts.buildHostUri(hostId).buildUpon()
                        .appendPath(PATH_ARTISTS)
                        .appendPath(String.valueOf(artistId))
                        .build();
        }

        /** Read {@link #_ID} from {@link Artists} {@link Uri}. */
        public static String getArtistId(Uri uri) {
            return uri.getPathSegments().get(3);
        }

        public final static String[] ALL_COLUMNS = {
                _ID, UPDATED, HOST_ID, ARTISTID, ARTIST, DESCRIPTION, GENRE, FANART, THUMBNAIL,
        };
    }

    /**
     * Columns for Albums table
     * For XBMC reference/unique key use HOST_ID + ALBUMID
     */
    public interface AlbumsColumns {
        public final static String HOST_ID = "host_id";
        public static final String ALBUMID = "albumid";

        public static final String FANART = "fanart";
        public static final String THUMBNAIL = "thumbnail";
        public static final String DISPLAYARTIST = "displayartist";
        public static final String RATING = "rating";
        public static final String TITLE = "title";
        public static final String YEAR = "year";
        public static final String ALBUMLABEL = "albumlabel";
        public static final String DESCRIPTION = "description";
        public static final String PLAYCOUNT = "playcount";
        public static final String GENRE = "genre";
    }

    public static class Albums implements BaseColumns, SyncColumns, AlbumsColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_ALBUMS).build();

        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/vnd.org.xbmc." + PATH_ALBUMS;
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/vnd.org.xbmc." + PATH_ALBUMS;

        /** Build {@link Uri} for albums list. */
        public static Uri buildAlbumsListUri(long hostId) {
            return Hosts.buildHostUri(hostId).buildUpon()
                        .appendPath(PATH_ALBUMS)
                        .build();
        }

        /** Build {@link Uri} for albums artists list. */
        public static Uri buildAlbumArtistsListUri(long hostId, long albumId) {
            return Hosts.buildHostUri(hostId).buildUpon()
                        .appendPath(PATH_ALBUMS)
                        .appendPath(String.valueOf(albumId))
                        .appendPath(PATH_ARTISTS)
                        .build();
        }

        /** Build {@link Uri} for albums genres list. */
        public static Uri buildAlbumGenresListUri(long hostId, long albumId) {
            return Hosts.buildHostUri(hostId).buildUpon()
                        .appendPath(PATH_ALBUMS)
                        .appendPath(String.valueOf(albumId))
                        .appendPath(PATH_AUDIO_GENRES)
                        .build();
        }

        /** Build {@link Uri} for requested {@link #_ID}. */
        public static Uri buildAlbumUri(long hostId, long albumId) {
            return Hosts.buildHostUri(hostId).buildUpon()
                        .appendPath(PATH_ALBUMS)
                        .appendPath(String.valueOf(albumId))
                        .build();
        }

        /** Read {@link #_ID} from {@link Albums} {@link Uri}. */
        public static String getAlbumId(Uri uri) {
            return uri.getPathSegments().get(3);
        }

        public final static String[] ALL_COLUMNS = {
                _ID, UPDATED, HOST_ID, ALBUMID, FANART, THUMBNAIL, DISPLAYARTIST, RATING, TITLE,
                YEAR, ALBUMLABEL, DESCRIPTION, PLAYCOUNT, GENRE
        };
    }

    /**
     * Columns for Songs table
     * For XBMC reference/unique key use HOST_ID + ALBUMID + SONGID
     */
    public interface SongsColumns {
        public final static String HOST_ID = "host_id";
        public static final String ALBUMID = "albumid";
        public static final String SONGID = "songid";

        public static final String DURATION = "duration";
        public static final String THUMBNAIL = "thumbnail";
        public static final String FILE = "file";
        public static final String TRACK = "track";
        public static final String TITLE = "title";
    }

    public static class Songs implements BaseColumns, SyncColumns, SongsColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_SONGS).build();

        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/vnd.org.xbmc." + PATH_SONGS;
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/vnd.org.xbmc." + PATH_SONGS;

        /** Build {@link Uri} for albums list. */
        public static Uri buildSongsListUri(long hostId, long albumId) {
            return Albums.buildAlbumUri(hostId, albumId).buildUpon()
                        .appendPath(PATH_SONGS)
                        .build();
        }

        /** Build {@link Uri} for requested {@link #_ID}. */
        public static Uri buildSongUri(long hostId, long albumId, long songId) {
            return Albums.buildAlbumUri(hostId, albumId).buildUpon()
                         .appendPath(PATH_SONGS)
                         .appendPath(String.valueOf(songId))
                         .build();
        }

        /** Read {@link #_ID} from {@link Albums} {@link Uri}. */
        public static String getSongId(Uri uri) {
            return uri.getPathSegments().get(5);
        }

        public final static String[] ALL_COLUMNS = {
                _ID, UPDATED, HOST_ID, ALBUMID, SONGID, DURATION, THUMBNAIL, FILE, TRACK, TITLE,
        };
    }

    /**
     * Columns for AudioGenres table
     * For XBMC reference/unique key use HOST_ID + GENREID
     */
    public interface AudioGenresColumns {
        public final static String HOST_ID = "host_id";
        public static final String GENREID = "genreid";

        public static final String THUMBNAIL = "thumbnail";
        public static final String TITLE = "title";
    }

    public static class AudioGenres implements BaseColumns, SyncColumns, AudioGenresColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_AUDIO_GENRES).build();

        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/vnd.org.xbmc." + PATH_AUDIO_GENRES;
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/vnd.org.xbmc." + PATH_AUDIO_GENRES;

        /** Build {@link Uri} for genres list. */
        public static Uri buildAudioGenresListUri(long hostId) {
            return Hosts.buildHostUri(hostId).buildUpon()
                        .appendPath(PATH_AUDIO_GENRES)
                        .build();
        }

        /** Build {@link Uri} for requested {@link #_ID}. */
        public static Uri buildAudioGenreUri(long hostId, long genreId) {
            return Hosts.buildHostUri(hostId).buildUpon()
                        .appendPath(PATH_AUDIO_GENRES)
                        .appendPath(String.valueOf(genreId))
                        .build();
        }

        /** Read {@link #_ID} from {@link Albums} {@link Uri}. */
        public static String getAudioGenreId(Uri uri) {
            return uri.getPathSegments().get(3);
        }

        public final static String[] ALL_COLUMNS = {
                _ID, UPDATED, HOST_ID, GENREID, THUMBNAIL, TITLE,
        };
    }

    /**
     * Columns for AlbumArtists table
     * All Other IDs refer to XBMC Ids, not Internal ones
     */
    public interface AlbumArtistsColumns {
        public final static String HOST_ID = "host_id";
        public static final String ALBUMID = "albumid";
        public static final String ARTISTID = "artistid";
    }

    public static class AlbumArtists implements BaseColumns, AlbumArtistsColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_ALBUM_ARTISTS).build();
        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/vnd.org.xbmc." + PATH_ALBUM_ARTISTS;

        /** Build {@link Uri} for requested {@link #_ID}. */
        public static Uri buildAlbumsForArtistListUri(long hostId, long artistId) {
            return Hosts.buildHostUri(hostId).buildUpon()
                        .appendPath(PATH_ARTISTS)
                        .appendPath(String.valueOf(artistId))
                        .appendPath(PATH_ALBUMS)
                        .build();
        }

        public final static String[] ALL_COLUMNS = {
                _ID, HOST_ID, ALBUMID, ARTISTID,
        };
    }

    /**
     * Columns for AlbumGenres table
     * All Other IDs refer to XBMC Ids, not Internal ones
     */
    public interface AlbumGenresColumns {
        public final static String HOST_ID = "host_id";
        public static final String ALBUMID = "albumid";
        public static final String GENREID = "genreid";
    }

    public static class AlbumGenres implements BaseColumns, AlbumGenresColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_ALBUM_GENRES).build();
        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/vnd.org.xbmc." + PATH_ALBUM_GENRES;

        /** Build {@link Uri} for requested {@link #_ID}. */
        public static Uri buildAlbumsForGenreListUri(long hostId, long genreId) {
            return Hosts.buildHostUri(hostId).buildUpon()
                        .appendPath(PATH_AUDIO_GENRES)
                        .appendPath(String.valueOf(genreId))
                        .appendPath(PATH_ALBUMS)
                        .build();
        }

        public final static String[] ALL_COLUMNS = {
                _ID, HOST_ID, ALBUMID, GENREID,
        };
    }

    /**
     * Columns for table MusicVideos
     * For XBMC reference/unique key use HOST_ID + MUSICVIDEOID
     */
    public interface MusicVideosColumns {
        public final static String HOST_ID = "host_id";
        public final static String MUSICVIDEOID = "musicvideoid";

        // ItemType.DetailsBase
        //public static final String LABEL = "label";

        // MediaType.DetailsBase
        public static final String FANART = "fanart";
        public static final String THUMBNAIL = "thumbnail";

        // DetailsBase
        //public static final String ART = "art";
        public static final String PLAYCOUNT = "playcount";

        // DetailsMedia
        public static final String TITLE = "title";

        // DetailsItem
        //public static final String DATEADDED = "dateadded";
        public static final String FILE = "file";
        //public static final String LASTPLAYED = "lastplayed";
        public static final String PLOT = "plot";

        // DetailsFile
        public static final String DIRECTOR = "director";
        //public static final String RESUME = "resume";
        public static final String RUNTIME = "runtime";
        //public static final String STREAMDETAILS = "streamdetails";
        public final static String AUDIO_CHANNELS = "audio_channels";
        public final static String AUDIO_CODEC = "audio_coded";
        public final static String AUDIO_LANGUAGE = "audio_language";
        public final static String SUBTITLES_LANGUAGES = "subtitles_languages";
        public static final String VIDEO_ASPECT = "video_aspect";
        public static final String VIDEO_CODEC = "video_codec";
        public static final String VIDEO_HEIGHT = "video_height";
        public static final String VIDEO_WIDTH = "video_width";

        // MusicVideo
        public static final String ALBUM = "album";
        public static final String ARTIST = "artist";
        public static final String GENRES = "genre";
        public static final String STUDIOS = "studio";
        public static final String TAG = "tag";
        public static final String TRACK = "track";
        public static final String YEAR = "year";
    }

    public static class MusicVideos implements BaseColumns, SyncColumns, MusicVideosColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_MUSIC_VIDEOS).build();

        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/vnd.org.xbmc." + PATH_MUSIC_VIDEOS;
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/vnd.org.xbmc." + PATH_MUSIC_VIDEOS;

        /** Build {@link Uri} for music videos list. */
        public static Uri buildMusicVideosListUri(long hostId) {
            return Hosts.buildHostUri(hostId).buildUpon()
                        .appendPath(PATH_MUSIC_VIDEOS)
                        .build();
        }

        /** Build {@link Uri} for requested {@link #_ID}. */
        public static Uri buildMusicVideoUri(long hostId, long musicVideoId) {
            return Hosts.buildHostUri(hostId).buildUpon()
                        .appendPath(PATH_MUSIC_VIDEOS)
                        .appendPath(String.valueOf(musicVideoId))
                        .build();
        }

        /** Read {@link #_ID} from {@link MusicVideos} {@link Uri}. */
        public static String getMusicVideoId(Uri uri) {
            return uri.getPathSegments().get(3);
        }

        public final static String[] ALL_COLUMNS = {
                _ID, UPDATED, HOST_ID, MUSICVIDEOID, FANART, THUMBNAIL, PLAYCOUNT, TITLE, FILE,
                PLOT, DIRECTOR, RUNTIME, AUDIO_CHANNELS, AUDIO_CODEC, AUDIO_LANGUAGE,
                SUBTITLES_LANGUAGES, VIDEO_ASPECT, VIDEO_CODEC, VIDEO_HEIGHT, VIDEO_WIDTH,
                ALBUM, ARTIST, GENRES, STUDIOS, TAG, TRACK, YEAR
        };
    }

}
