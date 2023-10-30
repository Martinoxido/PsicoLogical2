package com.example.psicological;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;
import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;
import android.Manifest;

public class Maps extends AppCompatActivity {
    private MapView mapView;
    private MyLocationNewOverlay myLocationOverlay;
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso concedido, ahora puedes realizar la lógica para obtener la ubicación
                // Puedes iniciar la obtención de la ubicación aquí
            } else {
                // Permiso denegado, puedes mostrar un mensaje al usuario o realizar alguna acción en caso de denegación
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Configuration.getInstance().load(getApplicationContext(),
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));

        Configuration.getInstance().load(getApplicationContext(),
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        mapView = findViewById(R.id.mapView);
        Marker pos = new Marker(mapView);
        // Configura el mapa
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);

        // Verifica si la capa de ubicación ya se ha agregado
        if (myLocationOverlay == null) {
            // Si no se ha agregado, crea una nueva instancia de MyLocationNewOverlay
            myLocationOverlay = new MyLocationNewOverlay(mapView);
            myLocationOverlay.enableFollowLocation();
            // Agrega la capa al mapa

        }

        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {

                GeoPoint santiagoPoint = new GeoPoint(location.getLatitude(), location.getLongitude());
                // Agrega la capa al mapa
                mapView.getOverlays().add(myLocationOverlay);
                Log.d("Ubicación", "Latitud: " + location.getLatitude() + ", Longitud: " + location.getLongitude());
                pos.setPosition(santiagoPoint);
                pos.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                pos.setTitle("Santiago, Chile");
                pos.setSnippet("Capital de Chile");
                mapView.getOverlays().add(pos);

            }
        };


        /*MapView mapView = findViewById(R.id.mapView);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);
        */

        //Coordenadas de Santiago, Chile
        double santiagoLatitude = -33.4489;
        double santiagoLongitude = -70.6693;
        //Coordenadas de Valparaíso, Chile
        double valparaisoLatitude = -33.4989778;
        double valparaisoLongitude = -70.6180072;
        //Crear objetos GeoPoint para los marcadores
        GeoPoint santiagoPoint = new GeoPoint(santiagoLatitude, santiagoLongitude);
        GeoPoint valparaisoPoint = new GeoPoint(valparaisoLatitude, valparaisoLongitude);
        //Crear marcadores con títulos y descripciones
        Marker santiagoMarker = new Marker(mapView);
        santiagoMarker.setPosition(santiagoPoint);
        santiagoMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        santiagoMarker.setTitle("Santiago, Chile");
        santiagoMarker.setSnippet("Capital de Chile");
        Marker valparaisoMarker = new Marker(mapView);
        valparaisoMarker.setPosition(valparaisoPoint);
        valparaisoMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        valparaisoMarker.setTitle("Valparaíso, Chile");
        valparaisoMarker.setSnippet("Puerto principal de Chile");
        //Agregar marcadores al mapa
        mapView.getOverlays().add(santiagoMarker);
        mapView.getOverlays().add(valparaisoMarker);


        //Crear y agregar la línea entre Santiago y Valparaíso
        Polyline polyline = new Polyline();
        polyline.addPoint(santiagoPoint);
        polyline.addPoint(valparaisoPoint);
        polyline.setColor(0xFF0000FF); // Color de la línea (azul en formato ARGB)
        polyline.setWidth(5); // Ancho de la línea en píxeles
        //Agrega la Linea al Mapa
        mapView.getOverlayManager().add(polyline);
        //Calcular la distancia entre Santiago y Valparaíso
        double distance = CalcularDistancia.CalcularDistancia(santiagoPoint, valparaisoPoint);
        TextView distanceTextView = findViewById(R.id.distanceTextView);
        distanceTextView.setText("Distancia entre Santiago y Valparaíso: " +
                String.format("%.2f", distance) + " km");
        // Centrar el mapa en Santiago, Chile
        IMapController mapController = mapView.getController();
        mapController.setCenter(santiagoPoint);
        mapController.setZoom(14); // Puedes ajustar el nivel de zoom según sea necesario




    }
}