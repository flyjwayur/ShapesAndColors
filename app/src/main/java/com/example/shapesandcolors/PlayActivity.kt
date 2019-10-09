package com.example.shapesandcolors

import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_play.*

class PlayActivity : AppCompatActivity(), SensorEventListener {

    private var sManager: SensorManager? = null
    private var color = false

    override fun onAccuracyChanged(s: Sensor?, i: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event!!.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        sManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    private fun getAccelerometer(event: SensorEvent) {
        // Movement
        val xVal = event.values[0]
        val yVal = event.values[1]
        val zVal = event.values[2]
        tv_X.text = "X Value: ".plus(xVal.toString())
        tv_Y.text = "Y Value: ".plus(yVal.toString())
        tv_Z.text = "Z Value: ".plus(zVal.toString())

        val accelerationSquareRoot =
            (xVal * xVal + yVal * yVal + zVal * zVal) / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH)

        if (accelerationSquareRoot >= 3) {
            if (color) {
                relative.setBackgroundColor(resources.getColor(R.color.aqua))
            } else {
                relative.setBackgroundColor(resources.getColor((R.color.orange)))
            }
            color = !color
        }
    }

    override fun onResume() {
        super.onResume()
        sManager!!.registerListener(
            this,
            sManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    override fun onPause() {
        super.onPause()
        sManager!!.unregisterListener(this)
    }
}
