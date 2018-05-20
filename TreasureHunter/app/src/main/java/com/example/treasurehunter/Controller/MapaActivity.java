package com.example.treasurehunter.Controller;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.treasurehunter.Model.Busqueda;
import com.example.treasurehunter.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener  {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Intent intent = getIntent();
        busqueda_jugador=(Busqueda)intent.getExtras().get("busqueda_jugardor");


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        miUbicacion();
        googleMap.setOnMarkerClickListener(this);
        if( busqueda_jugador.getPuntos().length>=1 ) {
            cargarAcertijos(busqueda_jugador.getLatitud(), busqueda_jugador.getLongitud());
        }
    }

    private void agregarMarcador(double lat, double lon) {
        LatLng coordenadas = new LatLng(lat, lon);
        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 17);
        if (marcador_mi_posicion != null) {
            marcador_mi_posicion.remove();
        }

        marcador_mi_posicion = mMap.addMarker(new MarkerOptions()
                .position(coordenadas)
                .title("Posición Actual")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_mipuntero)));


        mMap.animateCamera(miUbicacion);
    }

    private void cargarAcertijos( double lat, double lon){
        LatLng coordenadas = new LatLng(lat, lon);
        if (marcador_acertijo != null) {
            marcador_acertijo.remove();
        }
        marcador_acertijo = mMap.addMarker(new MarkerOptions()
                .position(coordenadas)
                .title("Nuevo Acertijo")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_acertijo)));


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
            actualizarUbicacion(location);
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

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        actualizarUbicacion(location);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,0,locationListener);
    }

    private GoogleMap mMap;
    private Marker marcador_mi_posicion;
    private Marker marcador_acertijo;
    double longitud, latitud;
    private Busqueda busqueda_jugador;

    @Override
    public boolean onMarkerClick(Marker marker) {
        int numero = (int) (Math.random() * 3) + 1;
        if(numero==1){
            Intent Preguntas = new Intent(getApplicationContext(),PreguntaActivity.class);
            startActivity(Preguntas);
        }else if(numero==2){
            Intent Cripto = new Intent(getApplicationContext(),CriptoActivity.class);
            startActivity(Cripto);
        } else if(numero==3){
            Intent Imagen = new Intent(getApplicationContext(),RompecabezasActivity.class);
            startActivity(Imagen);
        }

        return false;
    }
}

