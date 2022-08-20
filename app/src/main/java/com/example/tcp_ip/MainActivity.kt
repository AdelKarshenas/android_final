package com.example.tcp_ip

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.os.Bundle
import android.provider.Settings
import android.text.format.Formatter
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.lang.IllegalStateException
import java.net.Socket

public var Ip:String="192.168.93.59"
public var port:Int=5000
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.hide();
        StartConnectionBtn.setOnClickListener(){



            //Checking to see if the device is connected to the right Wifi

            val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            if (Is_Connected("192.168.93",5000,connectivityManager)==false){
                startActivity( Intent(Settings.ACTION_WIFI_SETTINGS));
            }
            else {

                var intent = Intent(this,Menu::class.java)
                startActivity(intent)
            }

        }



    }
    fun Is_Connected(ip:String,port:Int,connectivityManager:ConnectivityManager): Boolean {
        var is_connected=false
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)
        {
            is_connected = true
            val target_IP =ip
            val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            val ipAddress: String = Formatter.formatIpAddress(wifiManager.connectionInfo.ipAddress)
            if (!ipAddress.contains(target_IP))
            {
                is_connected=false
                return is_connected
            }
            return is_connected
        }
        else{
            is_connected=false
            return is_connected
        }
    }


}

public suspend fun dataexchange(address: String, port: Int,message:String): String {
    val socktst = Socket(address, port)
    val output = DataOutputStream(socktst!!.getOutputStream())
    //Sending the message to Board
    output!!.write(message.toByteArray())
    val Input = BufferedReader(InputStreamReader(socktst!!.getInputStream()))
    var return_message="\n"
    try {
        return_message= Input.readLine()
    }catch (e: IllegalStateException){
    }
    Input.close()
    socktst!!.close()
    return return_message
}


abstract class adel{

    var name:String="adel"
}