package com.example.tcp_ip

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.github.mikephil.charting.charts.Chart.LOG_TAG
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_chart.*
import kotlinx.android.synthetic.main.fragment_vand_i.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.IllegalStateException
import java.lang.NullPointerException
import kotlin.math.cos
import kotlin.math.sin

var recivedobj:info?=null
val mToastRunnablechart:Runnable?=null
var baseX:Float?=null
var baseY:Float?= null
var centerX:Float?=null
var centerY:Float?= null
val barchart=mainchart()
val phasorchart=phasor_frag()
var userObject:BarChartData?=null
var statuskey:String?=null
var timerbool:Boolean?=null
val mHandlerchart= Handler()
class chart : AppCompatActivity() {
    val loading = Loading(this)
    var statuskey : String? =null
    var val1=0.2f
    var val2=0.6f
    var val3=1.0f
//    var radius= (this.resources.displayMetrics.widthPixels*(0.40)/ kComparison!!).toFloat()
    override fun onCreate(savedInstanceState: Bundle?) {
        baseX=resources.displayMetrics.widthPixels.toFloat()
        baseY=resources.displayMetrics.heightPixels.toFloat()
        centerX=baseX!! /2
        centerY= baseY!! /3.5.toFloat()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)
        val toolbar = toolbarcharts
        setSupportActionBar(toolbar);
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
        getSupportActionBar()?.setTitle("")
//        val adres = "192.168.160.1"
//        val port = 80
        val adapter = MyViewPagerAdaptor(supportFragmentManager)
        adapter.addFragment(barchart, " Chart")
        adapter.addFragment(phasorchart,"Phasor")
        adapter.addFragment(waveformchart(),"waveform")
        viewpager1.adapter = adapter
        tabLayout1.setupWithViewPager(viewpager1)
        loading.startLoading()
        timerbool=true
        mHandlerchart.postDelayed(mToastRunnablechart, 500);



    }
    fun change_bool(bool:Boolean){
        timerbool=bool
    }
    override fun onDestroy() {
        mHandlerchart.removeCallbacks(mToastRunnablechart);
        super.onDestroy()
    }
    fun getuserobject(): BarChartData? {
        return userObject
    }


     private  val mToastRunnablechart: Runnable = object : Runnable {
        override fun run() {
            if (timerbool==true){
                CoroutineScope(Dispatchers.IO).launch {
                   // var stringtest="{\"VnnDates\":[\"0.6\",\"0.2\",\"0.3\",\"1.2\",\"1.3\",\"1.5\",\"1.1\",\"0.6\",\"0.2\",\"0.3\",\"1.2\",\"0.7\",\"1.6\",\"1.2\",\"0.2\",\"0.4\",\"0.8\",\"1.4\",\"1.6\",\"1.7\",\"1.0\"],\"VlnDates\":[\"0.6\",\"0.2\",\"0.3\",\"1.2\",\"0.3\",\"0.5\",\"1.3\",\"1.6\",\"1.2\",\"1.3\",\"1.8\",\"1.7\",\"0.6\",\"1.4\",\"1.2\",\"0.4\",\"0.8\",\"1.4\",\"1.6\",\"1.7\",\"1.0\"],\"IDates\":[\"0.9\",\"0.1\",\"1.3\",\"0.2\",\"1.7\",\"1.1\",\"1.5\",\"0.4\",\"1.2\",\"1.3\",\"0.2\",\"1.7\",\"0.6\",\"1.2\",\"0.2\",\"0.4\",\"0.8\",\"1.4\",\"1.6\",\"1.7\",\"1.0\"]}"
                    val data=dataexchange("192.168.160.1", 321,"chart")
//                Log.i(LOG_TAG)
                    val gson = Gson()
                    userObject = gson.fromJson(data, BarChartData::class.java)
                    val phas= userObject
                    loading.isDismiss()
//                val1=val1+0.1f
//                if(val1>=2)
//                {
//                    val1=0.2f
//                }
//                val2=val2+0.1f
//                if(val2>=2)
//                {
//                    val2=0.2f
//                }
//                val3=val3+0.1f
//                if(val3>=2)
//                {
//                    val3=0.2f
//                }

                    runOnUiThread{
                        statuskey = barchart.statuskey
                        Log.i(LOG_TAG,"you click button"+statuskey)
                        try {
                            barchart.setdata(userObject!!.VnnDates,userObject!!.VlnDates,userObject!!.IDates,userObject!!.thd,statuskey)
                        }
                        catch (e:NullPointerException){
                            Log.i(LOG_TAG,"null error chart")
                        }
                        try {
                            phasorchart.getinfo(phas!!)
                        }
                        catch (e:NullPointerException){
                            Log.i(LOG_TAG,"null error chart")
                        }


                    }




                }
            }

            mHandlerchart.postDelayed(this, 1500)
        }
    }
    fun starttimer(){
        mHandlerchart.postDelayed(mToastRunnablechart, 1);

    }

     fun stoptimer(){
         Log.i(LOG_TAG,"someone called stop timer")
        mHandlerchart.run { removeCallbacks(mToastRunnablechart) }

    }
}





class given(var size: Double?, var angle: Double?, var type:String)



//Turn degree to rad
fun degreetorad(degree: Double): Double? {
    val rad = degree?.times(0.0174)
    return rad
}
//Scale all the vectors to fit the screen
fun scale_and_return_with_x_and_y(sorted_array: List<vector>, biggest_size: Double, index:Int): List<vector_with_xandy> {
    val timesfactor:Double= (biggest_size/ sorted_array.maxBy { it.size }!!.size)
    var i=0
    while (i<index){
        sorted_array[i].size= (timesfactor*sorted_array[i].size)
        i++
    }
    val array_x_and_y= arrayListOf<vector_with_xandy>()
    i=0
    while (i<index){
        val x=sorted_array[i].size * cos(degreetorad(sorted_array[i].angle.toDouble())!!)
        val y= sorted_array[i].size * sin(degreetorad(sorted_array[i].angle.toDouble())!!)
        array_x_and_y.add(vector_with_xandy(sorted_array[i].size * cos(Math.toRadians(sorted_array[i].angle )!!)+ centerX!!,sorted_array[i].size * sin(Math.toRadians(sorted_array[i].angle )!!)+ centerY!!,"volt"))
        i++
    }
    return array_x_and_y
}

