package com.example.spotify2

import android.media.MediaPlayer
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.scheduleAtFixedRate

class MainActivity : AppCompatActivity() {

val TAG = "MainActivity"
var isFavorite : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myPlay()
        myFavorite()

    }
    fun myFavorite()
    {
        favoriteButton.setOnClickListener{
            isFavorite = !isFavorite
            if(isFavorite){
                favoriteButton.setBackgroundResource(R.drawable.heart)
            }else{
                favoriteButton.setBackgroundResource(R.drawable.heart2)
            }
        }

    }

    fun myPlay() {
        var mediaPlayer = MediaPlayer.create(this, R.raw.ticktock)

        PlaySeekbar.max = mediaPlayer.duration
        playButton.setOnClickListener {
          if  (mediaPlayer.isPlaying()){
              playButton.setBackgroundResource(R.drawable.play)
            Toast.makeText(this, "Pausa", Toast.LENGTH_LONG).show()
              mediaPlayer.pause() }

          else {
            mediaPlayer.start()
            playButton.setBackgroundResource(R.drawable.pause)
            Toast.makeText(this, "Reproducir", Toast.LENGTH_LONG).show()
        }

            PlaySeekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                    Log.i(TAG, "Valor : $progress")
                    if (fromUser)
                        mediaPlayer.seekTo(progress)
                }

                override fun onStopTrackingTouch(seekBar: SeekBar) {
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {

                }
            })

            Timer().scheduleAtFixedRate(0, 1000)
            {
                PlaySeekbar.progress = mediaPlayer.currentPosition

            }


        }
    }
}








