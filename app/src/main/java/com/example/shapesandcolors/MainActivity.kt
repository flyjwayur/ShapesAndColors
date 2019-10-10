package com.example.shapesandcolors

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shapesandcolors.game.FetchImageActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var actList = arrayOf("Ar", "FetchImage", "Play")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        circle_menu.setMainMenu(Color.parseColor("#A2E3DD"), R.drawable.play, R.drawable.stop)
            .addSubMenu(Color.parseColor("#FFB49D"), R.drawable.main_menu_ar)
            .addSubMenu(Color.parseColor("#F4D849"), R.drawable.main_menu_game)
            .addSubMenu(Color.parseColor("#A2E3DD"), R.drawable.main_menu_sensor3)
            .setOnMenuSelectedListener {
                when(actList[it]) {
                    "Ar" -> {
                        val intent = Intent(this, ArActivity :: class.java)
                        startActivity(intent)
                    }
                    "FetchImage" -> {
                        val intent = Intent(this, FetchImageActivity:: class.java)
                        startActivity(intent)
                    }
                    "Play" -> {
                        val intent = Intent(this, PlayActivity :: class.java)
                        startActivity(intent)
                    }
                }
            }

    }
}
