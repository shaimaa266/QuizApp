package com.example.quiz

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PlayerActivity : AppCompatActivity() {
    lateinit var playerName: EditText
    lateinit var playButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_player)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        playerName=findViewById(R.id.playerName)
        playButton=findViewById(R.id.playButton)

        playerName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {

            }

            override fun afterTextChanged(s: Editable?) {
//                if (playerName.text.isNotEmpty()){
                playButton.isEnabled=playerName.text.isNotEmpty()
//                }else{
//                    playButton.isEnabled=false
//                }
            }
        })
//        playerName.addTextChangedListener{
//            playButton.isEnabled=true
//        }
    }

    fun play(view: View) {
        val a= Intent(this, MainActivity::class.java)
        a.putExtra("name",playerName.text.toString())
        startActivity(a)
    }
}