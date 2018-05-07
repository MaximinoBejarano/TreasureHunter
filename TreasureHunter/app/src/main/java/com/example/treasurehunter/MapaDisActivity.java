package com.example.treasurehunter;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaDisActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerDragListener, GoogleMap.OnMarkerClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_dis);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        colocar = false;
        indexMarker = 0;
        btn_fijar = (Button) findViewById(R.id.btnFijar);
        btn_nuevo = (Button) findViewById(R.id.btnNuevo);
        btn_pregunta = (Button) findViewById(R.id.btnPregunta);
        btn_rompe = (Button) findViewById(R.id.btnRompe);
        btn_cripto = (Button) findViewById(R.id.btnCripto);
        lnl_opciones = (LinearLayout) findViewById(R.id.lnlOpciones);

        btn_nuevo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LatLng nuevoPunto = new LatLng(latitud, longitud);
                mMap.addMarker(new MarkerOptions()
                        .position(nuevoPunto)
                        .title("Nuevo Punto")
                        .draggable(true));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nuevoPunto, 17));
                indexMarker++;
            }
        });
        btn_fijar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lnl_opciones.setVisibility(View.VISIBLE);
            }
        });
        btn_pregunta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lnl_opciones.setVisibility(View.INVISIBLE);
            }
        });
        btn_rompe.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lnl_opciones.setVisibility(View.INVISIBLE);
            }
        });
        btn_cripto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lnl_opciones.setVisibility(View.INVISIBLE);
            }
        });

    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        miUbicacion();
        googleMap.setOnMarkerClickListener(this);
        googleMap.setOnMarkerDragListener(this);
    }

    private void agregarMarcador(double lat, double lon) {
        LatLng coordenadas = new LatLng(lat, lon);
        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 17);
        mMap.animateCamera(miUbicacion);
    }

    private void actualizarUbicacion(Location location) {
        if (location != null) {
            latitud = location.getLatitude();
            longitud = location.getLongitude();
            agregarMarcador(latitud, longitud);
        }
    }

    LocationListener locationListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            if (colocar == false) {
                actualizarUbicacion(location);
                colocar = true;
            }
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
    };

    private void miUbicacion() {


        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
          return;
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        actualizarUbicacion(location);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,0,locationListener);
    }

    @Override
    public void onMarkerDragStart(Marker marker) {


    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        lonmarker=marker.getPosition().longitude;
        latmarker=marker.getPosition().latitude;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        lonmarker=marker.getPosition().longitude;
        latmarker=marker.getPosition().latitude;
        Toast.makeText(this,String.valueOf(lonmarker)+String.valueOf(latmarker),Toast.LENGTH_SHORT).show();
        return false;
    }

    private GoogleMap mMap;
    double longitud, latitud;
    double lonmarker, latmarker;
    boolean colocar;
    Button btn_nuevo;
    Button btn_fijar;
    Button btn_pregunta;
    Button btn_rompe;
    Button btn_cripto;
    LinearLayout lnl_opciones;
    float indexMarker;
}
