package com.example.tcp_ip

import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.Chart
import kotlinx.android.synthetic.main.activity_abousus.*
import kotlinx.android.synthetic.main.activity_logging.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStream
import java.io.OutputStreamWriter
import java.io.PrintWriter
import java.net.Socket

class abousus : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_abousus)

        val toolbar = toolbaraboutus
        setSupportActionBar(toolbar);
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
        getSupportActionBar()?.setTitle("")
        button.setOnClickListener(){
            CoroutineScope(Dispatchers.IO).launch{
                var str=esockcreation(Ip, port,"testtcp")
                var i =3
                runOnUiThread {
                    textView2.text= str.toString()
                }
            }


        }

    }
}
var clientSocket:Socket?=null
var i=0
public suspend fun esockcreation(ip:String,port:Int, input:String): String? {
    //val clientSocket = Socket("192.168.160.1", 321)
    if (i==0){
        clientSocket= Socket(ip, port)
        i++
    }

    val outToServer: PrintWriter = PrintWriter(OutputStreamWriter(clientSocket!!.getOutputStream()))
    var buffer:ByteArray?=null
    var output =""
    Log.i(Chart.LOG_TAG, "this is before loop")
    while (true){
        outToServer.print(input)
        outToServer.flush()

        Log.i("were in ", "maybe problem")
//            val dos2 = BufferedReader(InputStreamReader(clientSocket!!.getInputStream()))
//            var sss: String = " "
//            var str = dos2.readLine()
//            Log.i(LOG_TAG,"this is after readline and value is: $str")
//            while (true) {
//                Log.i(LOG_TAG,"this is the error1")
//                if (str == null) {
//                    break
//                } else {
//                    sss = sss + str
//                    str = dos2.readLine()
//                    continue
//                }
//                Log.i(LOG_TAG,"this is the error2")       //   Log.i(LOG_TAG,"this is inside loop: $sss")
//            }
//          //  Log.i(LOG_TAG,"this is inside loop: $sss")
//            runOnUiThread{
//                Servermsg.text="hadi1"
//            }
        val `is`: InputStream = clientSocket!!.getInputStream()
        buffer = ByteArray(500)
        var read: Int

        while (`is`.read(buffer).also { read = it } != -1) {
            output = String(buffer, 0, read)
//            byteToInt(buffer)
            Log.i(Chart.LOG_TAG,"this is the value of buffer $output")

            // delay(1000)
            System.out.flush()
            break
        }
        //delay(1000)
        //   Log.i(LOG_TAG,"this is out of while")
        break
    }
    //clientSocket!!.close()
    return output
    Log.i(Chart.LOG_TAG, "this is the end of function")
    // val `is` = clientSocket!!.getInputStream()


}
