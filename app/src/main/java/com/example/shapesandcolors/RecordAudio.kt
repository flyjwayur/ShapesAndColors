package com.example.shapesandcolors

import android.content.Context
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.os.Environment
import java.io.*


class RecordAudio(val context: Context, val activity: MainActivity, var recFile: File): Runnable{
    var recRunning =true

    override fun run() {

        val recFileName = "testRecorded.raw"
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_MUSIC)
        try {
            val recFile = File(storageDir.toString() + "/"+ recFileName)
        } catch (ex: IOException) {
            // Error occurred while creating the File
        }

        try{
            val outputStream = FileOutputStream(recFile)
            val bufferedOutputStream = BufferedOutputStream(outputStream)
            val dataOutputStream = DataOutputStream(bufferedOutputStream)

            val minBufferSize = AudioRecord.getMinBufferSize(44100,
                AudioFormat.CHANNEL_OUT_STEREO,
                AudioFormat.ENCODING_PCM_16BIT)

            val aFormat = AudioFormat.Builder()
                .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                .setSampleRate(44100)
                .setChannelMask(AudioFormat.CHANNEL_OUT_STEREO)
                .build()
            val recorder = AudioRecord.Builder()
                .setAudioSource(MediaRecorder.AudioSource.MIC)
                .setAudioFormat(aFormat)
                .setBufferSizeInBytes(minBufferSize)
                .build()
            val audioData = ByteArray(minBufferSize)
            recorder.startRecording()
            while (recRunning) {
                val numofBytes = recorder.read(audioData, 0, minBufferSize)

                if(numofBytes>0) {
                    dataOutputStream.write(audioData)
                }
            }
            recorder.stop()
            dataOutputStream.close()

        }catch (ex: IOException){
            ex.printStackTrace()
        }

    }

}