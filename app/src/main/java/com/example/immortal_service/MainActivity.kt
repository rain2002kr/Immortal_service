package com.example.immortal_service

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        button.setOnClickListener{
            val intent = Intent(applicationContext, MyService::class.java)
            intent.putExtra("command", "show")
            intent.putExtra("name", "name")
            startService(intent)

        }
        button2.setOnClickListener{
            val intent = Intent(applicationContext, MyService::class.java)
            stopService(intent)

        }
        button3.setOnClickListener{
            val intent = Intent(applicationContext, Main2Activity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_SINGLE_TOP or
                    Intent.FLAG_ACTIVITY_CLEAR_TOP
            )

            startActivity(intent)
        }


    }

    override fun onDestroy() {

        super.onDestroy()


    }
}
