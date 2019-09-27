package com.example.shapesandcolors

import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import kotlinx.android.synthetic.main.activity_ar.*


class ArActivity : AppCompatActivity(), View.OnClickListener {
/* ******** Orriginal code  ****************

    private lateinit var arFragment: ArFragment
    lateinit var arrayView : Array<View>
    private lateinit var modelUri: Uri
    internal var selected = 1 // chosen renderable by default

    // Models
    private var circleRenderable: ModelRenderable? = null
    private var diamondRenderable: ModelRenderable? = null
    private var heartRenderable: ModelRenderable? = null
    private var hexagonRenderable: ModelRenderable? = null
    private var octagonRenderable: ModelRenderable? = null
    private var ovalRenderable: ModelRenderable? = null
    private var rectangleRenderable: ModelRenderable? = null
    private var squareRenderable: ModelRenderable? = null
    private var starRenderable: ModelRenderable? = null
    private var triangleRenderable: ModelRenderable? = null

    // Collections
    /*
    val stringList = listOf("circle.sfb", "diamond.sfb", "heart.sfb", "hexagon.sfb", "octagon.sfb",
        "oval.sfb", "rectangle.sfb", "square.sfb", "star.sfb", "triangle.sfb")
     */
    val stringList = listOf("circle.sfb", "diamond.sfb")
    val modelList = mutableListOf(circleRenderable, diamondRenderable)

    val renderables = mutableMapOf<String, ModelRenderable>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ar)

        arFragment = supportFragmentManager.findFragmentById(R.id.sceneform_fragment) as ArFragment
        arFragment.setOnTapArPlaneListener { hitResult, plane, motionEvent ->
            val anchor = hitResult.createAnchor()
            val anchorNode = AnchorNode(anchor)
            anchorNode.setParent(arFragment.arSceneView.scene)

            createModel(anchorNode, selected)
        }

        /*
        arrayView = arrayOf(
            imv_circle, imv_diamond, imv_heart, imv_hexagon, imv_octagon,
            imv_oval, imv_rectangle, imv_square, imv_star, imv_triangle)

         */
        arrayView = arrayOf(imv_circle, imv_diamond)

        setupImgViewClickListener()

        for(i in stringList.indices){
            setUpModels(i, stringList[i])
        }
    }

    private fun createModel(anchorNode: AnchorNode, selected: Int) {
        when(selected) {
            1 -> {
                val circle = TransformableNode(arFragment.transformationSystem)
                circle.setParent(anchorNode)
                circle.renderable = circleRenderable
                circle.select()
            }
        }
    }

    private fun setupImgViewClickListener() {
        for(i in arrayView.indices) {
            arrayView[i].setOnClickListener(this)
        }
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.imv_circle -> {
                selected = 1
                myBackground(view.id)
            }
            R.id.imv_diamond -> {
                selected = 2
                myBackground(view.id)
            }
        }
    }

    private fun myBackground(myid: Int) {
        for (i in arrayView.indices) {
            if (arrayView[i].id == myid) {
                arrayView[i].setBackgroundColor(Color.parseColor("#80333639"))
            } else {
                arrayView[i].setBackgroundColor(Color.TRANSPARENT)
            }
        }
    }

    private fun setUpModels( index: Int, str: String) {
        modelUri = Uri.parse(str)
        // val model = modelList[index]

        ModelRenderable.builder()
            .setSource(this, modelUri)
            .build()
            .thenAccept{ model ->  modelList[index] = model}
    }


***************** ********************* *************** */

    /* 2nd part

    private lateinit var arFragment: ArFragment
    lateinit var arrayView : Array<View>
    internal var selected = 1 // chosen renderable by default


    // Models
    private var circleRenderable: ModelRenderable? = null
    private var diamondRenderable: ModelRenderable? = null
    private var heartRenderable: ModelRenderable? = null
    private var hexagonRenderable: ModelRenderable? = null
    private var octagonRenderable: ModelRenderable? = null
    private var ovalRenderable: ModelRenderable? = null
    private var rectangleRenderable: ModelRenderable? = null
    private var squareRenderable: ModelRenderable? = null
    private var starRenderable: ModelRenderable? = null
    private var triangleRenderable: ModelRenderable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ar)
        setUpArray()
        setUpClickListener()
        // setUpModel()

        /*
        arFragment = supportFragmentManager.findFragmentById(R.id.sceneform_fragment) as ArFragment
        arFragment.setOnTapArPlaneListener { hitResult, plane, motionEvent ->
            val anchor = hitResult.createAnchor()
            val anchorNode = AnchorNode(anchor)
            anchorNode.setParent(arFragment.arSceneView.scene)

            createModel(anchorNode, selected)
        }

         */
    }

    private fun setUpArray() {
        arrayView = arrayOf(
            imv_circle, imv_diamond, imv_heart, imv_hexagon, imv_octagon,
            imv_oval, imv_rectangle, imv_square, imv_star, imv_triangle)
    }

    private fun setUpClickListener() {
        for (i in arrayView.indices) {
            arrayView[i].setOnClickListener(this)
        }
    }

    private fun setUpModel() {
        ModelRenderable.builder()
            .setSource(this, Uri.parse("circle.sfb"))
            .build()
            .thenAccept{ circleRenderable = it }
        ModelRenderable.builder()
            .setSource(this, Uri.parse("diamond.sfb"))
            .build()
            .thenAccept{ diamondRenderable = it }
        ModelRenderable.builder()
            .setSource(this, Uri.parse("heart.sfb"))
            .build()
            .thenAccept{ heartRenderable = it }
        ModelRenderable.builder()
            .setSource(this, Uri.parse("hexagon.sfb"))
            .build()
            .thenAccept{ hexagonRenderable = it }
        ModelRenderable.builder()
            .setSource(this, Uri.parse("octagon.sfb"))
            .build()
            .thenAccept{ octagonRenderable = it }
        ModelRenderable.builder()
            .setSource(this, Uri.parse("oval.sfb"))
            .build()
            .thenAccept{ ovalRenderable = it }
        ModelRenderable.builder()
            .setSource(this, Uri.parse("rectangle.sfb"))
            .build()
            .thenAccept{ rectangleRenderable = it }
        ModelRenderable.builder()
            .setSource(this, Uri.parse("square.sfb"))
            .build()
            .thenAccept{ squareRenderable = it }
        ModelRenderable.builder()
            .setSource(this, Uri.parse("star.sfb"))
            .build()
            .thenAccept{ starRenderable = it }
        ModelRenderable.builder()
            .setSource(this, Uri.parse("triangle.sfb"))
            .build()
            .thenAccept{ triangleRenderable = it }
    }

    override fun onClick(view: View) {
        if (view.id == R.id.imv_circle) {
            selected = 1
            myBackground(view.id)
            Log.d(Tag, "selected $selected")
        } else if (view.id == R.id.imv_diamond) {
            selected = 2
            myBackground(view.id)
            Log.d(Tag, "selected $selected")
        } else if (view.id == R.id.imv_heart) {
            selected = 3
            myBackground(view.id)
            Log.d(Tag, "selected $selected")
        } else if (view.id == R.id.imv_hexagon) {
            selected = 4
            myBackground(view.id)
            Log.d(Tag, "selected $selected")
        } else if (view.id == R.id.imv_octagon) {
            selected = 5
            myBackground(view.id)
            Log.d(Tag, "selected $selected")
        } else if (view.id == R.id.imv_oval) {
            selected = 6
            myBackground(view.id)
            Log.d(Tag, "selected $selected")
        } else if (view.id == R.id.imv_rectangle) {
            selected = 7
            myBackground(view.id)
            Log.d(Tag, "selected $selected")
        } else if (view.id == R.id.imv_square) {
            selected = 8
            myBackground(view.id)
            Log.d(Tag, "selected $selected")
        } else if (view.id == R.id.imv_star) {
            selected = 9
            myBackground(view.id)
            Log.d(Tag, "selected $selected")
        } else if (view.id == R.id.imv_triangle) {
            selected = 10
            myBackground(view.id)
            Log.d(Tag, "selected $selected")
        }
    }

    private fun myBackground(myID: Int) {
        for (i in arrayView.indices){
            if (arrayView[i].id == myID)
                arrayView[i].setBackgroundColor(Color.parseColor("#80333639"))
            else
                arrayView[i].setBackgroundColor(Color.TRANSPARENT)
        }
    }

    private fun createModel(anchorNode: AnchorNode, selected: Int) {
        if (selected == 1){
            val circle = TransformableNode(arFragment.transformationSystem)
            circle.setParent(anchorNode)
            circle.renderable = circleRenderable
            circle.select()
        } else if (selected == 2){
            val diamond = TransformableNode(arFragment.transformationSystem)
            diamond.setParent(anchorNode)
            diamond.renderable = diamondRenderable
            diamond.select()
        } else if (selected == 3){
            val heart = TransformableNode(arFragment.transformationSystem)
            heart.setParent(anchorNode)
            heart.renderable = heartRenderable
            heart.select()
        } else if (selected == 4){
            val hexagon = TransformableNode(arFragment.transformationSystem)
            hexagon.setParent(anchorNode)
            hexagon.renderable = hexagonRenderable
            hexagon.select()
        } else if (selected == 5){
            val octagon = TransformableNode(arFragment.transformationSystem)
            octagon.setParent(anchorNode)
            octagon.renderable = octagonRenderable
            octagon.select()
        } else if (selected == 6){
            val oval = TransformableNode(arFragment.transformationSystem)
            oval.setParent(anchorNode)
            oval.renderable = ovalRenderable
            oval.select()
        } else if (selected == 7){
            val rectangle = TransformableNode(arFragment.transformationSystem)
            rectangle.setParent(anchorNode)
            rectangle.renderable = rectangleRenderable
            rectangle.select()
        } else if (selected == 8){
            val square = TransformableNode(arFragment.transformationSystem)
            square.setParent(anchorNode)
            square.renderable = squareRenderable
            square.select()
        } else if (selected == 9){
            val star = TransformableNode(arFragment.transformationSystem)
            star.setParent(anchorNode)
            star.renderable = starRenderable
            star.select()
        } else if (selected == 10){
            val triangle = TransformableNode(arFragment.transformationSystem)
            triangle.setParent(anchorNode)
            triangle.renderable = triangleRenderable
            triangle.select()
        }
    }
***************** ********************* *************** */

    lateinit var arrayView : Array<View>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ar)

        arrayView = arrayOf(
            imv_circle, imv_diamond, imv_heart, imv_hexagon, imv_octagon,
            imv_oval, imv_rectangle, imv_square, imv_star, imv_triangle)
    }

    override fun onClick(view: View) {

    }
}
