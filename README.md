# 🧠 Behavioral Anomaly Detection App

This Android app uses behavioral and sensor data from a user's interactions to detect potential anomalies like stress, fatigue, or erratic behavior patterns using a trained LSTM Autoencoder model deployed via TensorFlow Lite.

---

## 📱 Screenshots

| Main Form Page                  | Live Typing Data Logging            | Anomaly Alerts              |
|-------------------------------|-------------------------------------|-----------------------------|
| ![Main Screen](screenshots/main_screen.png) | ![Typing Demo](screenshots/typing_demo.png) | ![Alert](screenshots/alert_popup.png) |

---

## 🧠 How It Works

- The app continuously collects:
  - Touch gestures (tap, swipe, pressure, etc.)
  - Typing behavior (speed, backspace rate, typed characters)
  - Sensor data (accelerometer, gyroscope, etc.)
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
