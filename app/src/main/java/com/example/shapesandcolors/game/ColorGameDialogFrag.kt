package com.example.shapesandcolors.game


import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.example.shapesandcolors.game.gameModel.GameViewModel


/**
 * A simple [Fragment] subclass.
 */
class ColorGameDialogFrag : DialogFragment() {

    private lateinit var viewModel: GameViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var fetch_image_activity = activity as FetchImageActivity
        viewModel = ViewModelProviders.of(fetch_image_activity).get(GameViewModel::class.java)
        var builder = AlertDialog.Builder(activity)
        builder.setTitle("Yeahy! Wonderful!")
        builder.setMessage("Color is matching well :D. \n" +
                "Youre score will increase by 1.\n Now, it is ${viewModel.score}.")

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

                    viewModel.shuffleImage()
                    fetch_image_activity.updateImage()
                    fetch_image_activity.updateDescText()
                }
            }
        }
    }

}
