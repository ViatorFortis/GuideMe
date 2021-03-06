package com.viatorfortis.guideme.utils;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.viatorfortis.guideme.BuildConfig;
import com.viatorfortis.guideme.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class IziTravelApi {

    private static final String tag = "IziTravelApi";

    private static final String BASE_URL = "https://api.izi.travel";

    private static final String MTGO_SEARCH_ENDPOINT = "mtg/objects/search";

    private static final String MTGO_ENDPOINT = "mtgobjects";

    private static final String API_VERSION_PARAMETER = "version";

    private static final String LANGUAGES_PARAMETER = "languages";

    private static final String MTGO_TYPE_PARAMETER = "type";

    private static final String QUERY_PARAMETER = "query";

    private static final String LIMIT_PARAMETER = "limit";

    private static final String COUNTRIES_SEGMENT = "countries";

    private static final String CITIES_SEGMENT = "cities";

    private static final String MEDIA_BASE_URL = "https://media.izi.travel";

    private static final String JPEG_EXTENSION = "jpg";

    public enum RegionType {
        COUNTRY, CITY
    }

    public enum SortingType {
        TITLE, RATING
    }

    public enum SortingOrder {
        ASC, DESC
    }

    private static final String CHILDREN_SEGMENT = "children";

    private static final String EXCEPT_PARAMETER = "except";

    private static final String SORTING_PARAMETER = "sort_by";

    private static final String API_KEY_HEADER = "X-IZI-API-KEY";

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

    public static URL buildGetMuseumAndTourByRegionUrl(Context context, String regionType, String regionUuid, String languages, String sortingType, String sortingOrder) {
        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .appendEncodedPath(regionType.equals(RegionType.COUNTRY.toString().toLowerCase() ) ? COUNTRIES_SEGMENT : CITIES_SEGMENT)
                .appendEncodedPath(regionUuid)
                .appendEncodedPath(CHILDREN_SEGMENT)
                .appendQueryParameter(API_VERSION_PARAMETER, context.getString(R.string.api_version) )
                .appendQueryParameter(LANGUAGES_PARAMETER, languages)
                .appendQueryParameter(EXCEPT_PARAMETER, context.getString(R.string.mtgo_publisher))
                .appendQueryParameter(LIMIT_PARAMETER, context.getString(R.string.mtgo_search_result_limit) )
                .appendQueryParameter(SORTING_PARAMETER, sortingType + ":" + sortingOrder)
                .build();

        URL url = null;

        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            Log.e(tag, e.getMessage() );
        }

        return url;
    }

    private static URL buildSearchMuseumAndTourUrl(Context context, String languages, String query, String sortingType, String sortingOrder) {

        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .appendEncodedPath(MTGO_SEARCH_ENDPOINT)
                .appendQueryParameter(API_VERSION_PARAMETER, context.getString(R.string.api_version) )
                .appendQueryParameter(LANGUAGES_PARAMETER, languages)
                .appendQueryParameter(MTGO_TYPE_PARAMETER, context.getString(R.string.mtgo_country_type)
                        + "," + context.getString(R.string.mtgo_city_type)
                        + "," + context.getString(R.string.mtgo_museum_type)
                        + "," + context.getString(R.string.mtgo_tour_type) )
                .appendQueryParameter(QUERY_PARAMETER, query)
                .appendQueryParameter(LIMIT_PARAMETER, context.getString(R.string.mtgo_search_result_limit) )
                .appendQueryParameter(SORTING_PARAMETER, sortingType + ":" + sortingOrder)
                .build();

        URL url = null;

        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            Log.e(tag, e.getMessage() );
        }

        return url;
    }

    private static String getHttpResponse(Context context, URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod(context.getString(R.string.http_get_method) );
        urlConnection.setRequestProperty(API_KEY_HEADER, BuildConfig.iziTravelApiKey);

        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static String getRegionListJson(Context context, String languages, String query)
            throws IOException {
        URL url = buildSearchRegionUrl(context, languages, query);
        return getHttpResponse(context, url);
    }

    public static String getMuseumAndTourListByRegionJson(Context context, String regionType, String regionUuid, String languages, String sortingType, String sortingOrder)
            throws IOException {
        URL url = buildGetMuseumAndTourByRegionUrl(context, regionType, regionUuid, languages, sortingType, sortingOrder);
        return getHttpResponse(context, url);
    }

    public static String getMuseumAndTourListBySearchJson(Context context, String languages, String query, String sortingType, String sortingOrder)
            throws IOException {
        URL url = buildSearchMuseumAndTourUrl(context, languages, query, sortingType, sortingOrder);
        return getHttpResponse(context, url);
    }

    private static URL buildLoadFullFormMTGObjectByIdUrl(Context context, String uuid, String languages) {
        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .appendEncodedPath(MTGO_ENDPOINT)
                .appendEncodedPath(uuid)
                .appendQueryParameter(API_VERSION_PARAMETER, context.getString(R.string.api_version) )
                .appendQueryParameter(LANGUAGES_PARAMETER, languages)
                .build();

        URL url = null;

        try {
            url = new URL(uri.toString() );
        } catch (MalformedURLException e) {
            Log.d(tag, e.getMessage() );
        }

        return url;
    }

    public static String getFullFormMTGObjectByIdJson(Context context, String uuid, String languages)
            throws IOException {
        URL url = buildLoadFullFormMTGObjectByIdUrl(context, uuid, languages);
        return getHttpResponse(context, url);
    }

    public static Uri buildStoryImageUri(String contentProviderUuid, String imageUuid, String imageSize) {
        String imageSegment = String.format("%s_%s.%s",
                imageUuid,
                imageSize,
                JPEG_EXTENSION);

        return Uri.parse(MEDIA_BASE_URL).buildUpon()
                .appendEncodedPath(contentProviderUuid)
                .appendEncodedPath(imageSegment)
                .build();
    }
}
