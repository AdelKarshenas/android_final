package com.example.tcp_ip

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tcp_ip.R
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment1.*
import kotlinx.android.synthetic.main.fragment_chart1.*
import kotlinx.android.synthetic.main.fragment_pa_and_qa.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.Socket

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment1 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    val adres = "192.168.160.1"
    val port = 80
    fun settext(Str :Status_info){
        recordstext.text=Str.Records.toString()
        eventstext.text=Str.Event.toString()
        alarmstext.text=Str.Alarms.toString()
        wavefromtext.text=Str.Waveform.toString()
        batterytext.text=Str.Battery.toString()
        memorytext.text=Str.Memory.toString()
        timetext.text=Str.Time

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        }
    }






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
