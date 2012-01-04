package com.kika.meowtag;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.SimpleLocationOverlay;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class MeowTagActivity extends Activity {
	
	private MapView mMapView;
	private SimpleLocationOverlay mMyLocationOverlay;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);
        
        mMapView = (MapView) findViewById(R.id.map_view);
        
        MapController mapController = mMapView.getController();
        mapController.setZoom(15);
        
        mMapView.setBuiltInZoomControls(true);
        mMapView.setMultiTouchControls(true);
        mMapView.setTileSource(TileSourceFactory.MAPNIK);
        
        mMyLocationOverlay = new SimpleLocationOverlay(this);
        
        mMapView.getOverlays().add(mMyLocationOverlay);

        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mLocListenner);
    }
	
	LocationListener mLocListenner = new LocationListener() {
	    public void onLocationChanged(Location location) {
	    	double lat = location.getLatitude();
	    	double lng = location.getLongitude();
	    	
	    	mMapView.getController().animateTo(lat, lng);
	    }

	    public void onStatusChanged(String provider, int status, Bundle extras) {}

	    public void onProviderEnabled(String provider) {}

	    public void onProviderDisabled(String provider) {}
	  };
}