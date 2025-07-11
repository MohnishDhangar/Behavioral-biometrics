package com.example.bank_app

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bank_app.ui.theme.BankAppTheme
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.Transaction
import com.google.firebase.database.database

class MainActivity : ComponentActivity() {

    // Firebase Realtime Database reference
    private lateinit var database: DatabaseReference

    // Unique session ID generated at app launch
    private val sessionId: String = System.currentTimeMillis().toString()

    // Firebase user ID placeholder (replace with actual user ID if using auth)
    private val userId: String = "test_user"  // Replace with FirebaseAuth.getInstance().currentUser?.uid

    // Android Sensor manager and listener
    private lateinit var sensorManager: SensorManager
    private val sensorListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {
            val sensorType = event.sensor.type
            val timestamp = System.currentTimeMillis()
            val values = event.values.map { it }
            sensorDataBatch.add(SensorRecord(sensorType, values, timestamp))
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
    }

    // Data class for sensor records
    data class SensorRecord(val type: Int, val values: List<Float>, val timestamp: Long)
    private val sensorDataBatch = mutableListOf<SensorRecord>()

    // Upload handler and interval
    private val uploadHandler = Handler(Looper.getMainLooper())
    private val uploadInterval: Long = 10_000  // 10 seconds

    private val uploadRunnable = object : Runnable {
        override fun run() {
            if (sensorDataBatch.isNotEmpty()) {
                val batchData = sensorDataBatch.map {
                    mapOf(
                        "type" to it.type,
                        "values" to it.values,
                        "timestamp" to it.timestamp
                    )
                }

                // Push under user > session > autoBatchKey
                val sessionRef = database
                    .child("users")
                    .child(userId)
                    .child("sessions")
                    .child(sessionId)

                val batchKey = sessionRef.push().key
                if (batchKey != null) {
                    sessionRef.child(batchKey).setValue(batchData)
                }

                sensorDataBatch.clear()
            }

            uploadHandler.postDelayed(this, uploadInterval)
        }
    }

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize Firebase
        database = Firebase.database.reference

        // Save session metadata
        val sessionMillis = sessionId.removePrefix("session_").toLong()

        val readableTime = java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            .apply { timeZone = java.util.TimeZone.getDefault() }
            .format(java.util.Date(sessionMillis))

        val metadata = mapOf(
            "sessionId" to sessionId,
            "startTime" to readableTime,
            "device" to Build.MODEL,
            "androidVersion" to Build.VERSION.SDK_INT
        )

        database
            .child("users")
            .child(userId)
            .child("sessions")
            .child(sessionId)
            .child("metadata")
            .setValue(metadata)

        // Set up sensor manager and listeners
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        val essentialSensorTypes = listOf(
            Sensor.TYPE_ACCELEROMETER,
            Sensor.TYPE_GYROSCOPE,
            Sensor.TYPE_MAGNETIC_FIELD,
            Sensor.TYPE_ROTATION_VECTOR,
            Sensor.TYPE_PRESSURE,
            Sensor.TYPE_GRAVITY,
            Sensor.TYPE_LINEAR_ACCELERATION
        )

        val sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL)
        for (sensor in sensorList) {
            if (sensor.type in essentialSensorTypes) {
                sensorManager.registerListener(sensorListener, sensor, SensorManager.SENSOR_DELAY_NORMAL)
            }
        }

        // Start periodic upload
        uploadHandler.postDelayed(uploadRunnable, uploadInterval)

        setContent {
            BankApp()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        sensorManager.unregisterListener(sensorListener)
        uploadHandler.removeCallbacks(uploadRunnable)
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Preview(showBackground = true)
@Composable
fun BankApp() {
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = "intro_screen") {
        composable("intro_screen") {
            IntroScreen(navController = navController)
        }

        composable("password_screen") {
            PasswordScreen(navController = navController)
        }

        composable("home_screen") {
            HomeScreen(navController = navController)
        }

        composable("payment_screen") {
            PaymentsPage(navController = navController)
        }

        composable("register_screen") {
            RegisterScreen(navController = navController)
        }

        composable("pin_screen") {
            SetPinScreen(navController = navController)
        }

        composable("phonebook_screen") {
            ContactsScreen(navController = navController)
        }

        composable("pay_contacts") {
            PayToContact(navController = navController)
        }

        // Add more destinations here
    }
}