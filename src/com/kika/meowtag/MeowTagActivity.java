package com.kika.meowtag;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.SimpleLocationOverlay;

import android.app.Activity;
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
        mapController.setZoom(2);
        
        mMapView.setBuiltInZoomControls(true);
        mMapView.setMultiTouchControls(true);
        mMapView.setTileSource(TileSourceFactory.MAPNIK);
        
        mMyLocationOverlay = new SimpleLocationOverlay(this);
        mMapView.getOverlays().add(this.mMyLocationOverlay);
    }
}