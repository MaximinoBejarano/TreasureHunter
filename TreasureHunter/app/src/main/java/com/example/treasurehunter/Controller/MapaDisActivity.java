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
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

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
        btn_pista = (Button) findViewById(R.id.btnPista);
        btn_nuevo = (Button) findViewById(R.id.btnNuevo);
        btn_pregunta = (Button) findViewById(R.id.btnPregunta);
        btn_rompe = (Button) findViewById(R.id.btnRompe);
        btn_cripto = (Button) findViewById(R.id.btnCripto);
        lnl_busqueda = (LinearLayout) findViewById(R.id.lnlBusqueda);
        lnl_tipo_acertijo= (LinearLayout) findViewById(R.id.lnlTipoAcertijo);
        lnl_tipo_acertijo.setVisibility(View.INVISIBLE);
        btn_empezar = (Button) findViewById(R.id.btnEmpezar);
        btn_nuevo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LatLng nuevoPunto = new LatLng(latitud, longitud);
                mMap.addMarker(new MarkerOptions()
                        .position(nuevoPunto)
                        .title("Nuevo Punto")
                        .draggable(true)
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_acertijo)));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(nuevoPunto, 17));
                indexMarker++;
            }
        });
        btn_nuevo.setVisibility(View.INVISIBLE);
        btn_empezar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lnl_busqueda.setVisibility(View.INVISIBLE);
                btn_nuevo.setVisibility(View.VISIBLE);
            }
        });

        btn_pregunta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btn_nuevo.setVisibility(View.VISIBLE);
                Intent Preguntas = new Intent(getApplicationContext(), PreguntaActivity.class);
                startActivity(Preguntas);
                lnl_tipo_acertijo.setVisibility(View.INVISIBLE);

            }
        });
        btn_rompe.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btn_nuevo.setVisibility(View.VISIBLE);
                Intent Imagen = new Intent(getApplicationContext(), ImagenActivity.class);
                startActivity(Imagen);
                lnl_tipo_acertijo.setVisibility(View.INVISIBLE);

            }
        });
        btn_cripto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btn_nuevo.setVisibility(View.VISIBLE);
                Intent Cripto = new Intent(getApplicationContext(), CriptoActivity.class);
                startActivity(Cripto);
                lnl_tipo_acertijo.setVisibility(View.INVISIBLE);


            }
        });
        btn_pista.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent pista = new Intent(getApplicationContext(), PistaActivity.class);
                startActivity(pista);



            }
        });
        Intent intent = getIntent();
        busqueda_disenador = (Busqueda) intent.getExtras().get("busqueda_diseñador");
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        miUbicacion();
        googleMap.setOnMarkerClickListener(this);
        googleMap.setOnMarkerDragListener(this);
        if (busqueda_disenador != null) {
            cargarAcertijos();
        }
    }

    private void cargarAcertijos() {
        if (busqueda_disenador != null) {
            double puntos[] = busqueda_disenador.getPuntos();
            int cantidad = busqueda_disenador.getPuntos().length;
            for (int i = 0; i < cantidad; i = i + 2) {
                LatLng coordenadas = new LatLng(puntos[i + 1], puntos[i]);
                mMap.addMarker(new MarkerOptions()
                        .position(coordenadas)
                        .title("Nuevo Punto")
                        .draggable(true)
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_acertijo)));
            }
        } else {


        }
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
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
    }

    @Override
    public void onMarkerDragStart(Marker marker) {


    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        lonmarker = marker.getPosition().longitude;
        latmarker = marker.getPosition().latitude;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        lonmarker = marker.getPosition().longitude;
        latmarker = marker.getPosition().latitude;
        lnl_tipo_acertijo.setVisibility(View.VISIBLE);
        btn_nuevo.setVisibility(View.INVISIBLE);
        return false;
    }

    private GoogleMap mMap;
    double longitud, latitud;
    double lonmarker, latmarker;
    boolean colocar;
    Button btn_pista;
    Button btn_empezar;
    Button btn_nuevo;
    Button btn_pregunta;
    Button btn_rompe;
    Button btn_cripto;
    LinearLayout lnl_busqueda;
    LinearLayout lnl_tipo_acertijo;
    float indexMarker;
    private Busqueda busqueda_disenador;
}
