package com.example.shapesandcolors

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment

class ArActivity : AppCompatActivity() {

    private lateinit var fragment: ArFragment
    private lateinit var modelUri: Uri
    private lateinit var fitToScanImageView : ImageView
    private var circleRenderable: ModelRenderable? = null
    private var heartRenderable: ModelRenderable? = null
    private var hexagonRenderable: ModelRenderable? = null
    private var selectedShape:Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        fragment = supportFragmentManager.findFragmentById(R.id.arimage_fragment) as ArFragment
    }
}
