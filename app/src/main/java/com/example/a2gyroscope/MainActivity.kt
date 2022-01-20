package com.example.a2gyroscope

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), SensorEventListener {
    lateinit var sm: SensorManager
    lateinit var sensor: Sensor
    lateinit var x:TextView
    lateinit var y:TextView
    lateinit var z:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        x = findViewById(R.id.x)
        y = findViewById(R.id.y)
        z = findViewById(R.id.z)
        sm = getSystemService(Context.SENSOR_SERVICE) as SensorManager;
        sensor = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);


    }


    override fun onSensorChanged(event: SensorEvent) {
        x.text="x: "+String.format("%.2f",Math.toDegrees(event.values[0].toDouble()))
        y.text="y: "+String.format("%.2f",Math.toDegrees(event.values[1].toDouble()))
        z.text="z: "+String.format("%.2f",Math.toDegrees(event.values[2].toDouble()))
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onResume() {
        super.onResume()
        sm.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onStop() {
        super.onStop()
        sm.unregisterListener(this)
    }

}