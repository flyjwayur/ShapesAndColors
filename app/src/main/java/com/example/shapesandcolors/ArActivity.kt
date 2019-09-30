package com.example.shapesandcolors

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.net.Uri
import android.widget.TextView
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.TransformableNode
import com.google.ar.sceneform.ux.ArFragment
import kotlinx.android.synthetic.main.activity_ar.*
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.ViewRenderable

class ArActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var arrayView: Array<View>
    internal var selected = 1 // chosen renderable by default
    private lateinit var fragment: ArFragment

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

        if (savedInstanceState != null) {
            fragment = getSupportFragmentManager().getFragment(
                savedInstanceState,
                "arFragment"
            ) as ArFragment
        }

        arrayView = arrayOf(
            imv_circle, imv_heart, imv_hexagon, imv_circle2, imv_heart2,
            imv_hexagon2, imv_circle3, imv_heart3, imv_hexagon3, imv_circle4
        )

        setUpClickListener()
        setUpModel()


        fragment = supportFragmentManager.findFragmentById(R.id.arimage_fragment) as ArFragment
        fragment.setOnTapArPlaneListener { hitResult, plane, motionEvent ->
            val anchor = hitResult.createAnchor()
            val anchorNode = AnchorNode(anchor)
            anchorNode.setParent(fragment.arSceneView.scene)

            createModel(anchorNode, selected)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //Save the fragment's instance
        getSupportFragmentManager().putFragment(outState, "arFragment", fragment)
    }

    private fun setUpClickListener() {
        for (i in arrayView.indices) {
            arrayView[i].setOnClickListener(this)
        }
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.imv_circle -> {
                selected = 1
                myBackground(view.id)
                // Toast.makeText(this, "Image $selected", Toast.LENGTH_SHORT).show()
            }
            R.id.imv_heart -> {
                selected = 2
                myBackground(view.id)
            }
            R.id.imv_hexagon -> {
                selected = 3
                myBackground(view.id)
            }
            R.id.imv_circle2 -> {
                selected = 4
                myBackground(view.id)
            }
            R.id.imv_heart2 -> {
                selected = 5
                myBackground(view.id)
            }
            R.id.imv_hexagon2 -> {
                selected = 6
                myBackground(view.id)
            }
            R.id.imv_circle3 -> {
                selected = 7
                myBackground(view.id)
            }
            R.id.imv_heart3 -> {
                selected = 8
                myBackground(view.id)
            }
            R.id.imv_hexagon3 -> {
                selected = 9
                myBackground(view.id)
            }
            R.id.imv_circle4 -> {
                selected = 10
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

    private fun setUpModel() {
        ModelRenderable.builder()
            .setSource(this, Uri.parse("Circle.sfb"))
            .build()
            .thenAccept { circleRenderable = it }
        ModelRenderable.builder()
            .setSource(this, Uri.parse("diamond.sfb"))
            .build()
            .thenAccept { diamondRenderable = it }
        ModelRenderable.builder()
            .setSource(this, Uri.parse("heart.sfb"))
            .build()
            .thenAccept { heartRenderable = it }
        ModelRenderable.builder()
            .setSource(this, Uri.parse("hexagon.sfb"))
            .build()
            .thenAccept { hexagonRenderable = it }
        ModelRenderable.builder()
            .setSource(this, Uri.parse("octagon.sfb"))
            .build()
            .thenAccept { octagonRenderable = it }
        ModelRenderable.builder()
            .setSource(this, Uri.parse("oval.sfb"))
            .build()
            .thenAccept { ovalRenderable = it }
        ModelRenderable.builder()
            .setSource(this, Uri.parse("rectangle.sfb"))
            .build()
            .thenAccept { rectangleRenderable = it }
        ModelRenderable.builder()
            .setSource(this, Uri.parse("square.sfb"))
            .build()
            .thenAccept { squareRenderable = it }
        ModelRenderable.builder()
            .setSource(this, Uri.parse("star.sfb"))
            .build()
            .thenAccept { starRenderable = it }
        ModelRenderable.builder()
            .setSource(this, Uri.parse("triangle.sfb"))
            .build()
            .thenAccept { triangleRenderable = it }
    }

    private fun createModel(anchorNode: AnchorNode, selected: Int) {
        when (selected) {
            1 -> {
                val circle = TransformableNode(fragment.transformationSystem)
                circle.scaleController.maxScale = 0.3f
                circle.scaleController.minScale = 0.2f
                circle.setParent(anchorNode)
                circle.renderable = circleRenderable
                circle.select()

                addShapeLabel(anchorNode, circle, "Tap to name")
            }
            2 -> {
                val diamond = TransformableNode(fragment.transformationSystem)
                diamond.scaleController.maxScale = 0.5f
                diamond.scaleController.minScale = 0.4f
                diamond.setParent(anchorNode)
                diamond.renderable = diamondRenderable
                diamond.select()

                addShapeLabel(anchorNode, diamond, "Tap to name")
            }
            3 -> {
                val heart = TransformableNode(fragment.transformationSystem)
                heart.scaleController.maxScale = 0.3f
                heart.scaleController.minScale = 0.2f
                heart.setParent(anchorNode)
                heart.renderable = heartRenderable
                heart.select()

                addShapeLabel(anchorNode, heart, "Tap to name")
            }
            4 -> {
                val hexagon = TransformableNode(fragment.transformationSystem)
                hexagon.scaleController.maxScale = 0.3f
                hexagon.scaleController.minScale = 0.2f
                hexagon.setParent(anchorNode)
                hexagon.renderable = hexagonRenderable
                hexagon.select()

                addShapeLabel(anchorNode, hexagon, "Tap to name")
            }
            5 -> {
                val octagon = TransformableNode(fragment.transformationSystem)
                octagon.scaleController.maxScale = 0.3f
                octagon.scaleController.minScale = 0.2f
                octagon.setParent(anchorNode)
                octagon.renderable = octagonRenderable
                octagon.select()

                addShapeLabel(anchorNode, octagon, "Tap to name")
            }
            6 -> {
                val oval = TransformableNode(fragment.transformationSystem)
                oval.scaleController.maxScale = 0.3f
                oval.scaleController.minScale = 0.2f
                oval.setParent(anchorNode)
                oval.renderable = ovalRenderable
                oval.select()

                addShapeLabel(anchorNode, oval, "Tap to name")
            }
            7 -> {
                val rectangle = TransformableNode(fragment.transformationSystem)
                rectangle.scaleController.maxScale = 0.5f
                rectangle.scaleController.minScale = 0.4f
                rectangle.setParent(anchorNode)
                rectangle.renderable = rectangleRenderable
                rectangle.select()

                addShapeLabel(anchorNode, rectangle, "Tap to name")
            }
            8 -> {
                val square = TransformableNode(fragment.transformationSystem)
                square.scaleController.maxScale = 0.5f
                square.scaleController.minScale = 0.4f
                square.setParent(anchorNode)
                square.renderable = squareRenderable
                square.select()

                addShapeLabel(anchorNode, square, "Tap to name")
            }
            9 -> {
                val star = TransformableNode(fragment.transformationSystem)
                star.scaleController.maxScale = 0.3f
                star.scaleController.minScale = 0.2f
                star.setParent(anchorNode)
                star.renderable = starRenderable
                star.select()

                addShapeLabel(anchorNode, star, "Tap to name")
            }
            10 -> {
                val triangle = TransformableNode(fragment.transformationSystem)
                triangle.scaleController.maxScale = 0.3f
                triangle.scaleController.minScale = 0.2f
                triangle.translationController
                triangle.setParent(anchorNode)
                triangle.renderable = triangleRenderable
                triangle.select()

                addShapeLabel(anchorNode, triangle, "Tap to name")

            }
        }
    }

    private fun addShapeLabel(anchorNode: AnchorNode, node: TransformableNode, label: String) {
        ViewRenderable.builder().setView(this, R.layout.label_layout)
            .build()
            .thenAccept { viewRenderable ->
                val labelView = TransformableNode(fragment.transformationSystem)
                labelView.localPosition = Vector3(0f, node.localPosition.y + 0.7f, 0f)
                labelView.setParent(anchorNode)
                labelView.renderable = viewRenderable
                labelView.select()

                val textLabel = viewRenderable.view as TextView
                textLabel.text = label // set's the text according to the label parsed
                textLabel.setOnClickListener {
                    anchorNode.setParent(null)
                }
            }
    }
}
