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


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.hide();

        var is_connected = false
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        StartConnectionBtn.setOnClickListener(){



            //Checking to see if the device is connected to the right Wifi
            if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)
            {
                is_connected = true
                val target_IP ="192.168.160"
                val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
                val ipAddress: String = Formatter.formatIpAddress(wifiManager.connectionInfo.ipAddress)
                if (!ipAddress.contains(target_IP))
                {
                    is_connected=false
                }
            }
            else{
                is_connected=false
            }

   /*         if (is_connected==false){
                startActivity( Intent(Settings.ACTION_WIFI_SETTINGS));
            }*/
            is_connected=true
            if (is_connected==true){

                var intent = Intent(this,Menu::class.java)
                startActivity(intent)
            }

        }

    }
    private suspend fun sock(address: String,port:Int){
        val sock1 = Socket(address,port)
        var dos = DataOutputStream(sock1.getOutputStream())

            dos.writeBytes("GET http://198.168.160.1/test HTTP/1.1")
            val dos2 = BufferedReader(InputStreamReader(sock1.getInputStream()))
            var sss : String = " "
            var str=dos2.readLine()
            while (true){
                if (str==null){
                    break
                }
                else{
                    sss= sss+str
                    str=dos2.readLine()
                    continue
                }

            }
            val gson = Gson()
            val userObject:info = gson.fromJson(sss, info::class.java)

            runOnUiThread(){
                //textView.text = userObject.vp_a.toString()
                var intent = Intent(this,Menu::class.java)
                startActivity(intent)
                //textView2.text=userObject.va.toString()
            }



        dos.close()
        sock1.close()
      //  dos.close()
//        val addr = InetAddress.getByName("www.google.com")
//        val socket = Socket(addr, 80)
//        val PrintWriter  =  PrintWriter(socket.getOutputStream(), true);
//
//
//        val BufferedReader  =  BufferedReader( InputStreamReader(socket.getInputStream()))
//        // send an HTTP request to the web server
//        PrintWriter.println("GET / HTTP/1.1");
//        PrintWriter.println("Host: www.google.com:80");
//        PrintWriter.println("Connection: Close");
//        PrintWriter.println();
//
//        // read the response
//        var loop = true;
//        var StringBuilder  =  StringBuilder(8096);
//        while (loop) {
//            if (BufferedReader.ready()) {
//                var i :Char
//                i = 'w'
//                while (i != '\u0000') {
//                    i = BufferedReader.read().toChar();
//                    StringBuilder.append( i);
//                }
//                loop = false;
//            }
//            socket.close()
//            runOnUiThread(){
//                print(StringBuilder)
//            }
//        }
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
