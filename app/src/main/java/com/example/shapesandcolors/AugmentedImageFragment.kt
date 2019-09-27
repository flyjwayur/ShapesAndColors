package com.example.shapesandcolors

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.ar.core.AugmentedImageDatabase
import com.google.ar.core.Config
import com.google.ar.core.Session
import com.google.ar.sceneform.ux.ArFragment

class AugmentedImageFragment : ArFragment() {
    private val SAMPLE_IMAGE_DATABASE = "myimages.imgdb"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:
        Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        planeDiscoveryController.hide()
        planeDiscoveryController.setInstructionView(null)
        arSceneView.planeRenderer.isEnabled = false
        return view
    }

    override fun getSessionConfiguration(session: Session?): Config {
        val config = super.getSessionConfiguration(session)
        config.focusMode = Config.FocusMode.AUTO
        setupAugmentedImageDatabase(config, session)

        return config
    }

    private fun setupAugmentedImageDatabase(config: Config, session: Session?) {
        val augmentedImageDb: AugmentedImageDatabase
        val assetManager = context!!.assets
        val inputStream1 = assetManager.open("watermelon.jpg")
        val augmentedImageBitmap1 = BitmapFactory.decodeStream(inputStream1)
        val inputStream2 = assetManager.open("book.jpg")
        val augmentedImageBitmap2 = BitmapFactory.decodeStream(inputStream2)

        augmentedImageDb = AugmentedImageDatabase.deserialize(session, context!!.resources.assets.open(SAMPLE_IMAGE_DATABASE))
        augmentedImageDb.addImage("watermelon", augmentedImageBitmap1)
        augmentedImageDb.addImage("book", augmentedImageBitmap2)
        config.augmentedImageDatabase = augmentedImageDb
    }
}