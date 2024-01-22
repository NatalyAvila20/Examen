package com.example.examen

import android.Manifest
import android.R
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.telecom.Call
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class MainActivity : AppCompatActivity() {
    private var exchangeRateService: ExchangeRateService? = null

    protected fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_lugar.xml)
        exchangeRateService = NetworkService.getInstance().exchangeRateService

        val call: Call<ExchangeRateResponse> = exchangeRateService.getExchangeRate()
        call.enqueue(object : Callback<ExchangeRateResponse?>() {
            fun onResponse(
                call: Call<ExchangeRateResponse?>?,
                response: Response<ExchangeRateResponse?>
            ) {
                if (response.isSuccessful()) {
                    val exchangeRate: ExchangeRateResponse = response.body()
                    if (exchangeRate != null) {
                        Log.d("ExchangeRate", "Dólar: " + exchangeRate.dolar)
                    }
                } else {
                    Log.e("NetworkError", "Error en la respuesta: " + response.code())
                }
            }

            fun onFailure(call: Call<ExchangeRateResponse?>?, t: Throwable) {
                Log.e("NetworkError", "Error en la solicitud: " + t.message)
            }
        })
    }

    private fun setupRecyclerView(lugares: List<Lugar>) {
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        val dataList = obtenerDatos(lugares)
        val adapter = MyAdapter(this, dataList)
        recyclerView.adapter = adapter
    }


    private fun obtenerDatos(lugares: List<Lugar>): List<String> {
        val dataList = mutableListOf<String>()
        for (lugar in lugares) {
            dataList.add("${lugar.nombre}, ${lugar.descripcion}")
        }
        return dataList
    }


    class MyAdapter
        (
        private val context: Context, private val dataList: List<String>
    ) :
        RecyclerView.Adapter<MyAdapter.ViewHolder?>() {


        fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view: View =
                LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
            return ViewHolder(view)
        }


        fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = dataList[position]
            holder.textView.text = item
        }


        fun getItemCount(): Int {
            return dataList.field
        }


        internal class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var textView: TextView

            init {
                textView = itemView.findViewById<TextView>(R.id.text_view)
            }
        }
    }
    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { result ->
        result?.let { viewModel.photos = viewModel.photos + it }
        getLocation()
    }

    @OptIn(ExperimentalPermissionsApi::class)
    private val locationPermissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

    private val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)

    private val requestCameraPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                startCamera()
            } else {
                showCameraPermissionDeniedDialog()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VacationApp(
                viewModel = viewModel,
                onCapturePhoto = { requestCameraPermission() },
                onOpenMap = { openMap() }
            )
        }

        requestLocationPermission()
    }

    private fun requestLocationPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                getLocation()
            }
            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) -> {
                showPermissionRationale()
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    private fun showPermissionRationale() {
        Toast.makeText(this, "Se necesitan permisos de ubicación para acceder a la ubicación actual.", Toast.LENGTH_LONG).show()
        requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                getLocation()
            } else {
                showPermissionDeniedDialog()
            }
        }

    private fun getLocation() {
        val context = LocalContext.current
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        try {
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                val locationCallback = object : LocationCallback() {
                    override fun onLocationResult(locationResult: LocationResult?) {
                        locationResult?.lastLocation?.let {
                            viewModel.location = it
                        }
                        locationManager.removeUpdates(this)
                    }
                }
                locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    0,
                    0f,
                    locationCallback,
                    context.mainLooper
                )
            } else {
                showLocationProviderDisabledDialog()
            }
        } catch (e: SecurityException) {
            Toast.makeText(this, "Error al obtener la ubicación. Asegúrate de tener los permisos necesarios.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showLocationProviderDisabledDialog() {
        Toast.makeText(this, "El proveedor de ubicación está desactivado. Habilita la ubicación desde la configuración del dispositivo.", Toast.LENGTH_LONG).show()
    }

    private fun openMap() {
        val location = viewModel.location

        if (location != null) {
            val uri = Uri.parse("geo:${location.latitude},${location.longitude}?z=15")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.setPackage("com.google.android.apps.maps")

            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {
                Toast.makeText(this, "No se encontró una aplicación de mapas instalada", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Ubicación no disponible", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showPermissionDeniedDialog() {
        Toast.makeText(this, "Los permisos de ubicación están desactivados. Habilítalos manualmente desde la configuración de la aplicación.", Toast.LENGTH_LONG).show()
        startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:$packageName")))
    }

    private fun showCameraPermissionDeniedDialog() {
        Toast.makeText(this, "Los permisos de la cámara están desactivados. Habilítalos manualmente desde la configuración de la aplicación.", Toast.LENGTH_LONG).show()
    }

    @Composable
    fun VacationApp(viewModel: VacacionViewModel, onCapturePhoto: () -> Unit, onOpenMap: () -> Unit) {
        val previewSurface = rememberUpdatedState(previewSurface) // Importante para asegurar la recreación de la vista previa al cambiar

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Registro de Vacaciones") },
                    actions = {
                        IconButton(
                            onClick = {
                                onCapturePhoto()
                            }
                        ) {
                            Icon(imageVector = Icons.Default.PhotoCamera, contentDescription = "Capturar Foto")
                        }
                    }
                )
            },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    OutlinedTextField(
                        value = viewModel.placeName,
                        onValueChange = { viewModel.placeName = it },
                        label = { Text("Nombre del lugar visitado") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                    LazyColumn {
                        items(viewModel.photos) { photoUri ->
                            PhotoThumbnail(imageName = "playa.png")
                            PhotoThumbnail(imageName = "termas.png")
                        }
                    }
                    viewModel.location?.let { location ->
                        Text("Ubicación actual: Latitud ${location.latitude}, Longitud ${location.longitude}")
                    } ?: run {
                        Text("Ubicación no disponible")
                    }
                    Button(
                        onClick = {
                            onOpenMap()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                    ) {
                        Text("Ver en el mapa")
                    }
                    CameraPreview(previewSurface.value)
                }
            }
        )
    }

    @Composable
    fun PhotoThumbnail(photoUri: Uri) {
        Image(
            painter = rememberImagePainter(photoUri),
            contentDescription = "photoUri",
            modifier = Modifier
                .size(80.dp)
                .padding(8.dp)
                .clickable {

                }
        )
    }
    @Composable
    fun CameraPreview(previewSurface: Preview.SurfaceProvider) {
        val context = LocalContext.current
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
        var camera: Camera? = null
        val cameraExecutor: ExecutorService = Executors.newSingleThreadExecutor()

        AndroidView(
            modifier = Modifier.fillMaxWidth().height(300.dp),
            factory = { context ->
                val view = PreviewView(context)
                val cameraProvider = cameraProviderFuture.get()
                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
                val preview = Preview.Builder()
                    .setTargetAspectRatio(AspectRatio.RATIO_4_3)
                    .build()
                preview.setSurfaceProvider(previewSurface)
                val imageCapture = ImageCapture.Builder()
                    .build()

                try {
                    camera = cameraProvider.bindToLifecycle(
                        context as LifecycleOwner,
                        cameraSelector,
                        preview,
                        imageCapture
                    )
                } catch (exc: Exception) {

                }

                view
            },
        )

        DisposableEffect(context) {
            onDispose {
                cameraExecutor.shutdown()
            }
        }

        Composable
        fun OsmdroidMap() {
            val context = LocalContext.current

            Configuration.getInstance().load(context, context.getSharedPreferences("osmdroid", 0))

            val mapView = remember { MapView(context) }
            mapView.setTileSource(TileSourceFactory.MAPNIK)

            Scaffold(
                content = {
                    AndroidView(factory = { mapView })
                }
            )
        }

        fun OsmdroidMap() {
            val context = LocalContext.current

            Configuration.getInstance().load(context, context.getSharedPreferences("osmdroid", 0))

            val mapView = remember { MapView(context) }
            mapView.setTileSource(TileSourceFactory.MAPNIK)

            // Obtener la ubicación actual del ViewModel (asumo que tienes una propiedad location en viewModel)
            val location = viewModel.location

            if (location != null) {
                mapView.controller.setCenter(GeoPoint(location.latitude, location.longitude))
                mapView.controller.setZoom(15.0)
            }

            AndroidView(factory = { mapView })
        }
    }


