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
    val TAG : String = MainActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "Secret Number :" + secretNumber.secret)
    }

    fun check (view : View) {

        val n : Int = ed_number.text.toString().toInt()
//        Log.d(TAG, "number:" + n)
        val diff : Int = secretNumber.validate(n)
        var message : String = getString(R.string.Yes_you_got_it)
        if(diff < 0){
            message = getString(R.string.Bigger)
        }else if(diff > 0){
            message = getString(R.string.smaller)
        }
//        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
         AlertDialog.Builder(this)
             .setTitle(getString(R.string.AlertDialog_title))
             .setMessage(message)
             .setPositiveButton(getString(R.string.OK), null)
             .show()
    }
}