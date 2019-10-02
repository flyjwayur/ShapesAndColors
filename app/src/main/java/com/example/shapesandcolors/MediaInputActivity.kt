package com.example.shapesandcolors

import android.app.PendingIntent.getActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_media_input.*
import kotlinx.android.synthetic.main.activity_media_input.view.*

class MediaInputActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_input)

        btn_save.setOnClickListener { onSaveMedia(it) }
        btn_cancel.setOnClickListener { onCancelMedia(it) }
    }

    private fun onSaveMedia(v: View?) {
        // code here
    }

    private fun onCancelMedia(v: View?) {
        // code here
        onBackPressed()
    }
}
