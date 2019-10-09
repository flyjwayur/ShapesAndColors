package com.example.shapesandcolors.game


import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.DialogFragment
import com.example.shapesandcolors.game.FetchImageActivity
import kotlinx.android.synthetic.main.activity_fetch_image.*

/**
 * A simple [Fragment] subclass.
 */
class ColorGameDialogFrag() : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var builder = AlertDialog.Builder(activity)
        builder.setTitle("Yeahy! Wonderful!")
        builder.setMessage("Color is matching well :D")

        var listener = DialogListener()
        builder.setPositiveButton("Play more", listener)

        var alert = builder.create()
        return alert
    }

    inner class DialogListener : DialogInterface.OnClickListener{
        override fun onClick(dialog: DialogInterface?, which: Int) {
            var fetch_image_activity = activity as FetchImageActivity

            when(which){
                DialogInterface.BUTTON_POSITIVE -> {
                    fetch_image_activity.textV_colorGameDesc2.text = "Awesome! Let's find a color of the image"
                    fetch_image_activity.shuffleImage()
                }
            }
        }
    }

}
