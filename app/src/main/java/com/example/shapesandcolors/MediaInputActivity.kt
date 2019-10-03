package com.example.shapesandcolors

import android.app.PendingIntent.getActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import kotlinx.android.synthetic.main.activity_media_input.*
import kotlinx.android.synthetic.main.activity_media_input.view.*
import org.jetbrains.anko.toast
import java.io.File
import java.io.FileInputStream
import java.io.IOException

class MediaInputActivity : AppCompatActivity() {

    lateinit var file: File
    lateinit var recordTheVoice: RecordAudio
    private var valueString: String? = null
    private var textFILE: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_input)

        valueString = intent.getStringExtra("shapeSoundName")

        requestPermissions(arrayOf(android.Manifest.permission.RECORD_AUDIO),1)

        textFILE = "$valueString.txt"
        val recFileName = "$valueString.raw"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_MUSIC)
        try {
            file = File(storageDir.toString() + "/"+ recFileName)
        } catch (ex: IOException) {
            toast("File doesn't exist in directory")
        }
        recordTheVoice = RecordAudio(this, this, file)

        playBtn.setOnClickListener {
            try {
                val inputStream = FileInputStream(file)
                val myRunnable = PlayAudio(inputStream)
                val myThread = Thread(myRunnable)
                myThread.start()
            } catch (ex: IOException) {
                toast("Audio file not found")
            }
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
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            try {
                val dir = applicationContext.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
                val file = File(dir, textFILE)
                val inputText = textInputEditText.text
                file.writeText("$inputText")
                textInputEditText.text?.clear()

                toast("Save successful!")

            } catch (ex: IOException){
                toast("Failed saving file.")
            }
        }
    }

    private fun onCancelMedia(v: View?) {
        onBackPressed()
    }
}
