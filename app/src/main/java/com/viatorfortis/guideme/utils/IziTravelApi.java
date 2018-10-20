package com.viatorfortis.guideme.utils;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.viatorfortis.guideme.R;

import java.net.MalformedURLException;
import java.net.URL;

public class IziTravelApi {

    private static final String tag = "IziTravelApi";

    private static final String BASE_URL = "https://api.izi.travel";

    private static final String MTGO_SEARCH_ENDPOINT = "mtg/objects/search";

    private static final String API_VERSION_PARAMETER = "version";

    private static final String LANGUAGES_PARAMETER = "languages";

    private static final String MTGO_TYPE_PARAMETER = "type";

    private static final String QUERY_PARAMETER = "query";

    private static final String LIMIT_PARAMETER = "limit";

    private static final String COUNTRIES_SEGMENT = "countries";

    private static final String CITIES_SEGMENT = "cities";

    private static final String CHILDREN_SEGMENT = "children";

    private static final String EXCEPT_PARAMETER = "except";

    private static URL buildSearchRegionUrl(Context context, String languages, String query) {

        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .appendEncodedPath(MTGO_SEARCH_ENDPOINT)
                .appendQueryParameter(API_VERSION_PARAMETER, context.getString(R.string.api_version) )
                .appendQueryParameter(LANGUAGES_PARAMETER, languages)
                .appendQueryParameter(MTGO_TYPE_PARAMETER, context.getString(R.string.mtgo_region_type) )
                .appendQueryParameter(QUERY_PARAMETER, query)
                .appendQueryParameter(LIMIT_PARAMETER, context.getString(R.string.mtgo_search_result_limit) )
                .build();

        URL url = null;

        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            Log.e(tag, e.getMessage() );
        }

        return url;
    }

    public static URL buildGetMuseumAndTourByCountryUrl(Context context, String countryUuid, String languages) {
        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .appendEncodedPath(COUNTRIES_SEGMENT)
                .appendEncodedPath(countryUuid)
                .appendEncodedPath(CHILDREN_SEGMENT)
                .appendQueryParameter(API_VERSION_PARAMETER, context.getString(R.string.api_version) )
                .appendQueryParameter(LANGUAGES_PARAMETER, languages)
                .appendQueryParameter(EXCEPT_PARAMETER, context.getString(R.string.mtgo_publisher))
                .appendQueryParameter(LIMIT_PARAMETER, context.getString(R.string.mtgo_search_result_limit) )
                .build();

        URL url = null;

        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            Log.e(tag, e.getMessage() );
        }

        return url;
    }

    public static URL buildGetMuseumAndTourByCityUrl(Context context, String cityUuid, String languages) {
        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .appendEncodedPath(CITIES_SEGMENT)
                .appendEncodedPath(cityUuid)
                .appendEncodedPath(CHILDREN_SEGMENT)
                .appendQueryParameter(API_VERSION_PARAMETER, context.getString(R.string.api_version) )
                .appendQueryParameter(LANGUAGES_PARAMETER, languages)
                .appendQueryParameter(EXCEPT_PARAMETER, context.getString(R.string.mtgo_publisher))
                .appendQueryParameter(LIMIT_PARAMETER, context.getString(R.string.mtgo_search_result_limit) )
                .build();

        URL url = null;

        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            Log.e(tag, e.getMessage() );
        }

        return url;
    }

    private static URL buildSearchMuseumAndTourUrl(Context context, String languages, String query) {

        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .appendEncodedPath(MTGO_SEARCH_ENDPOINT)
                .appendQueryParameter(API_VERSION_PARAMETER, context.getString(R.string.api_version) )
                .appendQueryParameter(LANGUAGES_PARAMETER, languages)
                .appendQueryParameter(MTGO_TYPE_PARAMETER, context.getString(R.string.mtgo_museum_type) + "," + context.getString(R.string.mtgo_tour_type) )
                .appendQueryParameter(QUERY_PARAMETER, query)
                .appendQueryParameter(LIMIT_PARAMETER, context.getString(R.string.mtgo_search_result_limit) )
                .build();

        URL url = null;

        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            Log.e(tag, e.getMessage() );
        }

        return url;
    }

}
