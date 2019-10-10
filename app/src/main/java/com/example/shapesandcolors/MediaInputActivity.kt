package com.example.shapesandcolors

import android.app.PendingIntent.getActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_media_input.*
import kotlinx.android.synthetic.main.activity_media_input.view.*
import org.jetbrains.anko.toast
import java.io.File
import java.io.FileInputStream
import java.io.IOException

class MediaInputActivity : AppCompatActivity() {

    lateinit var audioFile: File
    private lateinit var recordTheVoice: RecordAudio
    private var valueString: String? = null
    private lateinit var textFILE: String
    private lateinit var recFileName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_input)

        valueString = intent.getStringExtra("shapeSoundName")

        requestPermissions(arrayOf(android.Manifest.permission.RECORD_AUDIO),1)

        textFILE = "$valueString.txt"
        recFileName = "$valueString.raw"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_MUSIC)
        try {
            audioFile = File(storageDir.toString() + "/"+ recFileName)
        } catch (ex: IOException) {
            toast("File doesn't exist in directory")
        }
        recordTheVoice = RecordAudio(this, this, audioFile)

        // play audio file if it exists
        playBtn.setOnClickListener {
            try {
                val inputStream = FileInputStream(audioFile)
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
                val txtfile = File(dir, textFILE)
                val inputText = textInputEditText.text
                if(inputText!!.isNotBlank()) {
                    // checks that we get atleast 3 characters for the name before writing file
                    if (inputText.length >= 3) {
                        txtfile.writeText("$inputText")
                        toast("Save successful!")
                        onBackPressed()
                    } else {
                        toast("Give at least 3 characters")
                    }
                }
                textInputEditText.text?.clear()
            } catch (ex: IOException){
                toast("Failed saving file.")
            }
        }
    }

    private fun onCancelMedia(v: View?) {
        // Delete shape's label text
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            try {
                val dir = applicationContext.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
                File(dir, textFILE).delete()
            } catch (ex: IOException){
                toast("Deleting text file failed.")
            }
        }
        // Delete shape's Audio File
        try {
            val storageDir = getExternalFilesDir(Environment.DIRECTORY_MUSIC)
            val deleteAudioFile = File(storageDir.toString() + "/"+ recFileName)
            deleteAudioFile.delete()
        } catch (ex: IOException){
            Log.d("DBG", "No audio file to delete")
            toast("Deleting Audio file failed.")
        }
        toast("Shape's Defaults Restored")
        onBackPressed()
    }
}
