package com.kami.guess

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val secretNumber = com.kami.guess.secretNumber()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity", "Secret Number :" + secretNumber.secret)
    }

    fun check (view : View) {

        val n : Int = ed_number.text.toString().toInt()
//        Log.d("MainActivity", "number:" + n)
        val diff : Int = secretNumber.validate(n)
        var message : String = "Yes, you got it!"
        if(diff < 0){
            message = "Bigger"
        }else if(diff > 0){
            message = "smaller"
        }
//        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
         AlertDialog.Builder(this)
             .setTitle("Message")
             .setMessage(message)
             .setPositiveButton("OK", null)
             .show()
    }
}