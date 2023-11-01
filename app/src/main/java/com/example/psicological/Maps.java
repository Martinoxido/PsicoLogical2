package com.example.psicological;



import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.preference.PreferenceManager;
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
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;


public class Maps extends AppCompatActivity {
    private MapView mapView;
    private MyLocationNewOverlay myLocationOverlay;
    private FusedLocationProviderClient fusedLocationClient;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    Marker MiMarker;
    IMapController mapController;
    Polyline polyline = new Polyline();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        TextView txt = (TextView) findViewById(R.id.distanceTextView);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);



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

        ;


        //Coordenadas de Santiago, Chile
        double santiagoLatitude = -33.44915;
        double santiagoLongitude = -70.66242;

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
        santiagoMarker.setTitle("IP/CFT Santo Tomás");
        santiagoMarker.setSnippet("Santiago");

        //Agregar marcadores al mapa
        mapView.getOverlays().add(santiagoMarker);
        // Verificar y solicitar permisos si es necesario
        if (checkLocationPermission()) {
            requestLocation();
        }


        //Crear y agregar la línea entre Santiago y Valparaíso


        mapView.getOverlayManager().add(polyline);
        //Calcular la distancia entre Santiago y Valparaíso
        double distance = CalcularDistancia.CalcularDistancia(santiagoPoint, valparaisoPoint);
        TextView distanceTextView = findViewById(R.id.distanceTextView);
        distanceTextView.setText("Distancia entre Instuto y Tu Ubicacion: " +
                String.format("%.2f", distance) + " km");
        // Centrar el mapa en Santiago, Chile
        mapController = mapView.getController();
        mapController.setCenter(santiagoPoint);
        mapController.setZoom(14);
         // Puedes ajustar el nivel de zoom según sea necesario
    }

    private boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Si no se tienen permisos, solicitarlos en tiempo de ejecución
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                requestLocation(); // Si se otorgan los permisos, obtener la ubicación
            }
        }
    }

    private void requestLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();
                            GeoPoint MiPoint = new GeoPoint(latitude,longitude);
                            MiMarker = new Marker(mapView);
                            MiMarker.setPosition(MiPoint);
                            MiMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                            MiMarker.setTitle("Mi Ubicacion");
                            MiMarker.setSnippet("estas aquí");
                            mapView.getOverlays().add(MiMarker);
                            mapController.setCenter(MiPoint);
                            mapController.setZoom(18);
                            TextView distanceTextView = findViewById(R.id.distanceTextView);
                            double santiagoLatitude = -33.44915;
                            double santiagoLongitude = -70.66242;

                            GeoPoint santiagoPoint1 = new GeoPoint(santiagoLatitude, santiagoLongitude);
                            double distance = CalcularDistancia.CalcularDistancia(santiagoPoint1,MiPoint);
                            distanceTextView.setText("Distancia entre el Instituto y Tu Ubicacion: " +
                                    String.format("%.2f", distance) + " km");
                            polyline.addPoint(santiagoPoint1);
                            polyline.addPoint(MiPoint);
                            polyline.setColor(0xFF0000FF); // Color de la línea (azul en formato ARGB)
                            polyline.setWidth(5);
                            // Haz lo que quieras con las coordenadas aquí
                        }
                    }
                });
    }
}