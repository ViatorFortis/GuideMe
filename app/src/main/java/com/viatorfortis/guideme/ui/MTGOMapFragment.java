package com.viatorfortis.guideme.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.viatorfortis.guideme.R;
import com.viatorfortis.guideme.model.FullFormMTGObject;
import com.viatorfortis.guideme.model.Map;

public class MTGOMapFragment extends Fragment {

    private MapView mMapView;
    private GoogleMap mGoogleMap;


    private FullFormMTGObject mFullFormMTGObject;


    private static final String FULL_FORM_MTGOBJECT_PARCEL_KEY = "full_form_motgobject_parcel";

    public static Fragment newInstance(FullFormMTGObject fullFormMTGObject) {

        MTGOMapFragment mtgoMapFragment = new MTGOMapFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(FULL_FORM_MTGOBJECT_PARCEL_KEY, fullFormMTGObject);
        mtgoMapFragment.setArguments(bundle);

        return mtgoMapFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(FULL_FORM_MTGOBJECT_PARCEL_KEY)) {
            mFullFormMTGObject = getArguments().getParcelable(FULL_FORM_MTGOBJECT_PARCEL_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mtgomap, container, false);

        mMapView = rootView.findViewById(R.id.mv_mtgo_map);
        mMapView.onCreate(savedInstanceState);

        //mMapView.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext() );
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                mGoogleMap = mMap;

                MoveCameraToMTGObjectBounds();
                AddRouteToMap();
            }
        });

        return rootView;
    }

    private void MoveCameraToMTGObjectBounds() {
        String [] mapBoundsArray = mFullFormMTGObject.getMap().getBounds().split(",");

        LatLng swLatLng = new LatLng(
                Float.parseFloat(mapBoundsArray[0]),
                Float.parseFloat(mapBoundsArray[1])
        );

        LatLng neLatLng = new LatLng(
                Float.parseFloat(mapBoundsArray[2]),
                Float.parseFloat(mapBoundsArray[3])
        );

        LatLngBounds mapBounds = new LatLngBounds(swLatLng, neLatLng);

        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(mapBounds, 0) );
    }

    private void AddRouteToMap() {
        String routeString = mFullFormMTGObject.getMap().getRoute();
        PolylineOptions polylineOptions = new PolylineOptions();

        for (String point : routeString.split(";")) {
            String [] pointPosition = point.split(",");
            polylineOptions.add(new LatLng(
                    Float.parseFloat(pointPosition[0]),
                    Float.parseFloat(pointPosition[1]) ) );
        }

        polylineOptions.color(getResources().getColor(R.color.colorAccent) )
                .width(5);
        mGoogleMap.addPolyline(polylineOptions);
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onStart() {
        super.onStart();
        mMapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mMapView.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }
}
