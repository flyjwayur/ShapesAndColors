package com.example.shapesandcolors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.ar.sceneform.ux.ArFragment

class ArActivity : AppCompatActivity() {

    private lateinit var fragment: ArFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ar)
        fragment = supportFragmentManager.findFragmentById(R.id.arimage_fragment) as ArFragment
    }
}
