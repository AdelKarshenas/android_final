package com.example.tcp_ip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_menu.*

class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        supportActionBar?.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar?.setCustomView(R.layout.action_bar_layout)
        data.setOnClickListener(){
            var intent = Intent(this,Data::class.java)
            startActivity(intent)
        }
        runOnUiThread{

        }
        aboutusbtn.setOnClickListener(){
            var intent = Intent(this,abousus::class.java)
            startActivity(intent)
        }
        diagram.setOnClickListener(){
            var intent = Intent(this,chart::class.java)
            startActivity(intent)
        }
        settingbtnmenu.setOnClickListener(){
            var intent = Intent(this,settings::class.java)
            startActivity(intent)
        }
        deviceinfobtnmenu.setOnClickListener(){
            var intent = Intent(this,Device_Info::class.java)
            startActivity(intent)
        }
        loggingbtn.setOnClickListener(){
            var intent = Intent(this,Logging::class.java)
            startActivity(intent)
        }
    }
}