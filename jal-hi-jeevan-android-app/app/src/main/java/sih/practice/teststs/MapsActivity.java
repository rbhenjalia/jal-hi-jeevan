package sih.practice.teststs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,LocationListener {

    private GoogleMap mMap;
    private Button next;
    private LatLng complain=null;
    private LocationManager locationManager;
    private static final long MIN_TIME = 400;
    private static final float MIN_DISTANCE = 1000;
    String u,uid;
    Double lat=0.0,lon=0.0;

    private PlaceAutocompleteFragment placeAutoComplete;
    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Intent iEx = getIntent();
        u = iEx.getStringExtra("URI");
        uid = iEx.getStringExtra("UID");
        lat=iEx.getDoubleExtra("Latitude",0);
        lon=iEx.getDoubleExtra("Longitude",0);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DISTANCE, this);

    }
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(lat,lon);
        next = (Button)findViewById(R.id.Next);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Here"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,16));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener(){
            @Override
            public void onMapClick(LatLng latLng){
                mMap.clear();
                mMap.setMyLocationEnabled(true);
                mMap.addMarker(new MarkerOptions().position(latLng).title("Here"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                complain = latLng;
                lat=complain.latitude;
                lon=complain.longitude;
                next.setVisibility(View.VISIBLE);
            }
        });
        placeAutoComplete = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete);
        placeAutoComplete.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {

                Log.d("Maps", "Place selected: " + place.getName());
                Toast.makeText(getApplicationContext(),place.getName(),Toast.LENGTH_SHORT).show();
                LatLng latLng = place.getLatLng();
                mMap.clear();
                mMap.setMyLocationEnabled(true);
                mMap.addMarker(new MarkerOptions().position(latLng).title("Here"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                complain = latLng;
                lat=complain.latitude;
                lon=complain.longitude;
            }
            @Override
            public void onError(Status status) {
                Log.d("Maps", "An error occurred: " + status);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),complain.toString(),Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),Complaints.class);
                i.putExtra("URI",u);
                i.putExtra("Latitude",lat);
                i.putExtra("Longitude",lon);
                i.putExtra("UID",uid);
                startActivity(i);
                finish();
            }
        });
    }
    @Override
    public void onLocationChanged(Location location) {
        mMap.clear();
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.addMarker(new MarkerOptions().position(latLng).title("Here"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
        locationManager.removeUpdates(this);
        complain = latLng;
        lat=complain.latitude;
        lon=complain.longitude;
        //Toast.makeText(getApplicationContext(),"Focus gone",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

}
