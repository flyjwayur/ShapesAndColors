package com.example.shapesandcolors

import android.app.PendingIntent.getActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import kotlinx.android.synthetic.main.activity_media_input.*
import kotlinx.android.synthetic.main.activity_media_input.view.*
import java.io.File
import java.io.FileInputStream
import java.io.IOException

class MediaInputActivity : AppCompatActivity() {

    lateinit var file: File
    lateinit var recordTheVoice: RecordAudio
    var objID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_input)

        objID = intent.getIntExtra("shape", 0)
        tv_shapeId.text = objID.toString()

        requestPermissions(arrayOf(android.Manifest.permission.RECORD_AUDIO),1)

        val recFileName = "testRecorded.raw"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_MUSIC)
        try {
            file = File(storageDir.toString() + "/"+ recFileName)
        } catch (ex: IOException) {

        }
        recordTheVoice = RecordAudio(this, this, file)

        playBtn.setOnClickListener {
            val inputStream = FileInputStream(file)
            val myRunnable = PlayAudio(inputStream)
            val myThread = Thread(myRunnable)
            myThread.start()
        }

        recordBtn.setOnClickListener {
            recordSound()
        }

        btn_stopRecord.setOnClickListener {
            stopRecording()
        }

        btn_save.setOnClickListener { onSaveMedia(it) }
        btn_cancel.setOnClickListener { onCancelMedia(it) }
    }

    private fun recordSound() {
        val myThread2 =Thread(recordTheVoice)
        myThread2.start()
    }

    private fun stopRecording() {
        recordTheVoice.recRunning =false
    }

    private fun onSaveMedia(v: View?) {
        // code here
    }

    private fun onCancelMedia(v: View?) {
        // code here
        onBackPressed()
    }
}
