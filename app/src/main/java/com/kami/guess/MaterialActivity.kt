package com.kami.guess

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.ed_number
import kotlinx.android.synthetic.main.content_material.*

class MaterialActivity : AppCompatActivity() {
    private val REQUEST_RECORD: Int = 100
    val secretNumber = com.kami.guess.secretNumber()
    val TAG : String = MaterialActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            replyGame()
        }
        count_show.setText(secretNumber.count.toString())
        Log.d(TAG, "onCreate: " + secretNumber.secret)
        val count = getSharedPreferences("guess", MODE_PRIVATE)
            .getInt("count", -1)
        val nick = getSharedPreferences("guess", MODE_PRIVATE)
            .getString("nick", null)
        Log.d(TAG, "onCreate_share: nick=>" + nick + " count=>" + count)
    }

    private fun replyGame() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.Reset_Game))
            .setMessage(getString(R.string.Are_you_sure))
            .setPositiveButton(getString(R.string.OK), { dialog, which ->
                secretNumber.reset()
                count_show.setText(secretNumber.count.toString())
                ed_number.setText("")
            })
            .setNegativeButton(getString(R.string.cancel), null)
            .show()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ( requestCode == REQUEST_RECORD ){
            if(resultCode == RESULT_OK){
                val nickname = data?.getStringExtra("nick")
                Log.d(TAG, "onActivityResult: " +  nickname)
                replyGame()
            }
        }

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
        count_show.setText(secretNumber.count.toString())
//        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.AlertDialog_title))
            .setMessage(message)
            .setPositiveButton(getString(R.string.OK), {dialog, which ->
                if(diff == 0){
                    val intent = Intent(this, RecordActivity::class.java)
                        .putExtra("count", secretNumber.count)
//                    startActivity(intent)
                    startActivityForResult(intent, REQUEST_RECORD)
                }
            })
            .show()
    }
}