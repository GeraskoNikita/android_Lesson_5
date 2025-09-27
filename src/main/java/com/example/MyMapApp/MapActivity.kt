package com.example.MyMapApp

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.MyMapApp.databinding.ActivityMapBinding
import com.google.android.gms.location.*
import ru.dgis.sdk.map.MyLocationMapObjectSource
import ru.dgis.sdk.navigation.NavigationManager
import ru.dgis.sdk.navigation.NavigationView

class MapActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMapBinding
    private lateinit var navigationManager: NavigationManager
    private lateinit var fusedClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private var locationCallback: LocationCallback? = null



    // Запрос разрешений
    private val requestLocationPermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
            val granted = (result[Manifest.permission.ACCESS_FINE_LOCATION] == true) ||
                    (result[Manifest.permission.ACCESS_COARSE_LOCATION] == true)
            if (granted) {
                onLocationPermissionGranted()
//                ensureLocationPermission()
            } // иначе — просто ничего не делаем
        }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

       

        // 1) Инициализируем клиента и request
        fusedClient = LocationServices.getFusedLocationProviderClient(this)
        locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,   // интервал, мс
            3_000L
        )
            .setMinUpdateIntervalMillis(1_500L)
            .setWaitForAccurateLocation(false)
            .build()

        // 2) MapView подписываем на lifecycle
        lifecycle.addObserver(binding.mapView)

        // 3) Navigation
        val sdkContext = (application as App).sdkContext
        navigationManager = NavigationManager(sdkContext)


        binding.mapView.getMapAsync { map ->
            navigationManager.mapManager.addMap(map)
            map.addSource(MyLocationMapObjectSource(sdkContext))
        }
        binding.navigationView.navigationManager = navigationManager

        // 4) Проверяем разрешения и стартуем всё нужное
        ensureLocationPermission()
    }

    private fun ensureLocationPermission() {
        val fine = ContextCompat.checkSelfPermission(
            this, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val coarse = ContextCompat.checkSelfPermission(
            this, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (fine || coarse) {
            onLocationPermissionGranted()
        } else {
            requestLocationPermissions.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    /** Вызываем, когда разрешения уже есть */
    private fun onLocationPermissionGranted() {
        // Добавляем «синюю точку» на карту
        val sdkContext = (application as App).sdkContext
        binding.mapView.getMapAsync { map ->
            map.addSource(MyLocationMapObjectSource(sdkContext))
        }
        // И запускаем обновления геопозиции
        startLocationUpdates()
    }

    @SuppressLint("MissingPermission") // мы проверили разрешения выше
    private fun startLocationUpdates() {
        if (locationCallback == null) {
            locationCallback = object : LocationCallback() {
                override fun onLocationResult(result: LocationResult) {
                    val loc = result.lastLocation ?: return
                    // здесь можно двигать камеру/логировать
                    // println("Обновление: ${loc.latitude}, ${loc.longitude}")
                }
            }
        }
        fusedClient.requestLocationUpdates(
            locationRequest,
            locationCallback!!,
            Looper.getMainLooper()
        )

        // Однократно получить lastLocation (может быть null)
        fusedClient.lastLocation.addOnSuccessListener { loc ->
            // при желании — центрируйте карту
        }
    }

    private fun stopLocationUpdates() {
        locationCallback?.let { fusedClient.removeLocationUpdates(it) }
    }

    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }

    override fun onResume() {
        super.onResume()
        // Перезапускаем только если разрешения уже даны
        val fine = ContextCompat.checkSelfPermission(
            this, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val coarse = ContextCompat.checkSelfPermission(
            this, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        if (fine || coarse) startLocationUpdates()
    }
}
