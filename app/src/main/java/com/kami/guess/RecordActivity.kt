package com.kami.guess

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_record.*

class RecordActivity : AppCompatActivity() {
    val TAG = RecordActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)
        val count : Int = intent.getIntExtra("count", -1)
        record_count_show.setText(count.toString())

        save_btn.setOnClickListener { v: View? ->
            val nick = ed_nickname.text.toString()
            getSharedPreferences("guess", MODE_PRIVATE)
                .edit()
                .putInt("count", count)
                .putString("nick", nick)
                .commit()
            var intent = Intent()
            intent.putExtra("nick", nick)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}