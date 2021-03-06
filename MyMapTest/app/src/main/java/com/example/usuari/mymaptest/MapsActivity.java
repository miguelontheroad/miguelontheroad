package com.example.usuari.mymaptest;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    public Marker nuevoMarcador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
   @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);

        nuevoMarcador = mMap.addMarker(new MarkerOptions().position(sydney).title("Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }

    public void Buscar(View v){

       /*
        EditText edtLatitud = this.findViewById(R.id.edtLatitud);
        EditText edtLonguitud = this.findViewById(R.id.edtLonguitud);

        String latitud = String.valueOf(edtLatitud.getText());
        String longuitud = String.valueOf(edtLonguitud.getText());

        */

        nuevoMarcador.remove();

        EditText edtlugar = this.findViewById(R.id.edtAdress);
        String lugar = String.valueOf(edtlugar.getText());

        Geocoder geo = new Geocoder(this);
        int maxResultados = 1;
        List<Address> adress = null;
        try {
            adress = geo.getFromLocationName(lugar, maxResultados);
        } catch (IOException e) {
            e.printStackTrace();
        }
        LatLng latLng = new LatLng(adress.get(0).getLatitude(), adress.get(0).getLongitude());


        /*
        double latte = Double.parseDouble(latitud);
        double longe = Double.parseDouble(longuitud);

        LatLng direccion = new LatLng(latte, longe);
        */
        mMap.addMarker(new MarkerOptions().position(latLng).title(lugar));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

    }


}
