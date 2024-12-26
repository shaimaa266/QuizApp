package com.example.quiz

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var questionText: TextView
    lateinit var helloText: TextView

    lateinit var questionCount: TextView
    lateinit var answerText: Spinner
    lateinit var startButton: Button
    lateinit var nextButton: Button
    val countries= arrayOf("egypt","usa","france","uk","morocco")
    val cities= arrayOf("cairo","ws","paris","london","rabat")
   var score = 0
    var index=0
  var questions= countries.toMutableList()
    private var items=mutableListOf("please select",
        "cairo",
        "baghdad",
        "beijing",
        "ws",
        "toronto",
        "paris",
        "khartoum",
        "damascus",
        "london",
        "rabat",
        "ryad",
        "berlin")

    val itemsMaster=items.toMutableList()

    var player: MediaPlayer?=null

    lateinit var countryAdapter: ArrayAdapter<String>
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        questionText= findViewById(R.id.questionText)
        questionCount= findViewById(R.id.questionCount)
        answerText=findViewById(R.id.answerText)
        helloText=findViewById(R.id.helloText)
        startButton=findViewById(R.id.startButton)
        nextButton=findViewById(R.id.nextButton)


        countryAdapter= ArrayAdapter(this,android.R.layout.simple_list_item_1,items)
        answerText.adapter=countryAdapter


        val name=intent.getStringExtra("name")
        helloText.text="hello $name"

    }

    override fun onDestroy() {
//        if(player!=null){
        player?.stop()
//        }
        super.onDestroy()
    }

    @SuppressLint("SetTextI18n")
    fun start(view: View) {
        player?.stop()
        resetItems()
//        countryAdapter.notifyDataSetChanged()

        score=0
        index=0
        questions.subList(0,countries.size).shuffle()
        questionText.text="what is the capital of ${questions.first()}"
        questionCount.text="Question 1 of ${questions.size}"
        nextButton.isEnabled=true
        answerText.isEnabled=true
    }

    fun next(view: View) {
        val answer=answerText.selectedItem.toString().lowercase()
        if(answerText.selectedItemPosition==0){
            Toast.makeText(this, "please answer", Toast.LENGTH_SHORT).show()
            return
        }
        if(answer==cities[index]) {
            score++
            items.remove(answer)
        }

        index++
        if(index<questions.size){

            questionText.text="what is the capital of ${questions[index]}"
            questionCount.text="Question ${index+1} of ${questions.size}"
        }else{
            Toast.makeText(this, "score=$score", Toast.LENGTH_LONG).show()
            nextButton.isEnabled=false
           // if (score>countries.size/2){
              //  player= MediaPlayer.create(this,R.raw.success)
               // player?.start()
           // }else{
               // player= MediaPlayer.create(this,R.raw.fail)
                //player?.start()
            //}
        }

        answerText.setSelection(0)

        //solution1
//        items.remove("please select") // o(n)
//        items.shuffle()
//        items.add(0,"please select") // o(n)

        //solution2
//        items.shuffle()
//        val index=items.indexOf("please select") //o(n)
//        Collections.swap(items,0,index) // o(1)

        //solution3

        items.subList(1,items.size).shuffle()

//        answerText.setText("")
    }

    private fun resetItems() {
        items.clear()
        items.addAll(itemsMaster)
    }
}
