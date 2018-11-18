package bdybala.android.gpsalarm.main.maps;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.hannesdorfmann.mosby3.mvp.MvpFragment;

import bdybala.android.gpsalarm.R;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MapsFragment extends MvpFragment<MapsView, MapsPresenter> implements MapsView, OnMapReadyCallback {

    MapView mMapView;
    private GoogleMap mGoogleMap;
    private Circle mCircle;

    @Override
    public MapsPresenter createPresenter() {
        return new MapsPresenterImpl();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);

        mMapView = view.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(this);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mMapView.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mMapView.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        try {
            mGoogleMap.setMyLocationEnabled(true);
            mGoogleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                @Override
                public void onMapLongClick(LatLng latLng) {
                    getPresenter().handleLongClick(latLng);
                }
            });
        } catch (SecurityException e) {
            log.error("Location permissions denied");
        }
    }

    @Override
    public void drawCircleAndMoveThere(LatLng latLng, int radius) {
        if (mCircle != null) {
            mCircle.remove();
        }
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(latLng);
        circleOptions.radius(radius);
        circleOptions.strokeColor(Color.BLACK);
        circleOptions.fillColor(0x260000FF);
        circleOptions.strokeWidth(2);
        mCircle = mGoogleMap.addCircle(circleOptions);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, getZoomLevel(radius));
        mGoogleMap.animateCamera(cameraUpdate);
    }

    private float getZoomLevel(double radius) {
        float zoomLevel;
        radius = radius * 1.5;
        double scale = radius / 500;
        zoomLevel = (int) (16 - Math.log(scale) / Math.log(2));
        return zoomLevel;
    }

    @Override
    public void moveToNewPoint(LatLng point) {
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(point);
        mGoogleMap.animateCamera(cameraUpdate);
    }
}
