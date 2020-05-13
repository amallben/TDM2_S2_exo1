package com.example.dictionnaire



import android.net.Uri
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_listdata.*
import java.util.*


class activity_listdata : AppCompatActivity() {
    //var listdata: TextView? = null
    private var mTTS: TextToSpeech? = null
    //var imageView: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listdata)
        //listdata = findViewById(R.id.listdata)
        //imageView = findViewById(R.id.imageView)
        val intent = intent
        val receivedName = intent.getStringExtra("title")
        val receivedImage = intent.getIntExtra("image", 0)
        listdata.text=receivedName
        if(receivedName=="Pen")
        {
            imageView.visibility=View.INVISIBLE
            video_view.visibility=View.VISIBLE
            button_speak.visibility=View.INVISIBLE
            val videoPath =
                "android.resource://" + packageName + "/" + R.raw.penvideo
            val uri: Uri = Uri.parse(videoPath)
            video_view.setVideoURI(uri)
            val mediaController = MediaController(this)
            video_view.setMediaController(mediaController)
            mediaController.setAnchorView(video_view)
        }
        else{
            video_view.visibility=View.INVISIBLE
            button_speak.visibility=View.VISIBLE
            imageView.visibility=View.VISIBLE
        imageView.setImageResource(receivedImage)
        //enable back Button
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                mTTS = TextToSpeech(this, TextToSpeech.OnInitListener { status ->
                    if (status == TextToSpeech.SUCCESS) {
                        val result = mTTS!!.setLanguage(Locale.ENGLISH)
                        if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED
                        ) {
                            Log.e("TTS", "Language not supported")
                        } else {
                            button_speak.setEnabled(true)
                        }
                    } else {
                        Log.e("TTS", "Initialization failed")
                    }
                })
        button_speak.setOnClickListener(View.OnClickListener { speak() })}

    }
    private fun speak() {
        val text = listdata!!.text.toString()
        var pitch = seek_bar_pitch!!.progress.toFloat() / 50
        if (pitch < 0.1) pitch = 0.1f
        var speed = seek_bar_speed!!.progress.toFloat() / 50
        if (speed < 0.1) speed = 0.1f
        mTTS!!.setPitch(pitch)
        mTTS!!.setSpeechRate(speed)
        mTTS!!.speak(text, TextToSpeech.QUEUE_FLUSH, null)
    }

    override fun onDestroy() {
        if (mTTS != null) {
            mTTS!!.stop()
            mTTS!!.shutdown()
        }
        super.onDestroy()
    }


    //getting back to listview
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() === R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
