# 🧠 Behavioral Biometrics Anomaly Detection System

This mobile application uses behavioral and sensor data from a user's interactions to detect potential anomalies like stress, fatigue, or erratic behavior patterns using a trained LSTM Autoencoder model deployed via TensorFlow Lite.

---

## 📱 Screenshots

| Main Form Page                  | Live Typing Data Logging            | Anomaly Alerts              |
|-<img width="1440" height="3120" alt="Screenshot_20250723_091327" src="https://github.com/user-attachments/assets/34918d45-db6e-4bbe-a24a-edb1dae83b59" />
<img width="1440" height="3120" alt="Screenshot_20250723_091252" src="https://github.com/user-attachments/assets/35fbff89-76c3-4bd8-a087-481f0084d29d" />
<img width="1440" height="3120" alt="Screenshot_20250723_091216" src="https://github.com/user-attachments/assets/f25de134-0a12-444d-ba08-cdad35601ebe" />
<img width="1440" height="3120" alt="Screenshot_20250723_091157" src="https://github.com/user-attachments/assets/1160b72a-4898-4d73-b3c2-67d4ae240c0e" />
<img width="1440" height="3120" alt="Screenshot_20250723_091139" src="https://github.com/user-attachments/assets/07278b53-4b90-4d52-926d-8fc01ce326bb" />
<img width="1440" height="3120" alt="Screenshot_20250723_091023" src="https://github.com/user-attachments/assets/c6fd6521-32c5-4393-9076-2e8f54f42cfe" />


---

## 🧠 How It Works

- The app continuously collects:
  - Touch gestures (tap, swipe, pressure, etc.)
  - Typing behavior (speed, backspace rate, typed characters)
  - Sensor data (accelerometer, gyroscope, rotation vectors, pressure, coordinates etc.)
- These are **normalized** using training bounds and passed into a trained **LSTM Autoencoder**.
- The model calculates **reconstruction error** to detect if the current behavior deviates significantly from typical patterns.
- If anomalies are found, alerts are shown and also logged in the **Dashboard** screen.

---

## 🚀 Features

- 📲 Logs detailed touch and typing behavior
- 📈 Uses TensorFlow Lite model for on-device anomaly detection
- ⚠️ Custom thresholds for typing vs swiping anomalies
- 📋 Dashboard view to inspect all anomalies from current session
- 🔐 User-friendly architecture that stores data under `users → userId → sessions → sessionId` in Firebase

---


🛠️ Built With
🧩 Jetpack Compose

🧠 TensorFlow Lite (LSTM Autoencoder)

🔥 Firebase Realtime Database

📱 Android SDK (Kotlin)

📊 Custom analytics dashboard

## 📦 Project Structure

```bash
├── MainActivity.kt            # Collects behavior and sensor data
├── Dashboard.kt              # Displays detected anomalies
├── assets/
│   ├── autoencoder.tflite    # Trained model (11-feature input)
│   ├── scaler.json           # Min-max normalization bounds
│   └── thresholds.json       # Thresholds for anomaly levels
├── README.md
└── screenshots/
    ├── main_screen.png
    ├── typing_demo.png
    └── alert_popup.png

