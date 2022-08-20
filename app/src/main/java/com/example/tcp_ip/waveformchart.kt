package com.example.tcp_ip


import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.Chart
import com.github.mikephil.charting.charts.Chart.LOG_TAG
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import kotlinx.android.synthetic.main.fragment_waveformchart.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.InputStream
import java.io.OutputStreamWriter
import java.io.PrintWriter
import java.net.Socket
import java.text.DecimalFormat

var loading:Loading?=null
var data2:LineData?=null
var data3:LineData?=null
var data:LineData?=null
var linechart:LineChart?=null
var linechart2:LineChart?=null
// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [waveformchart.newInstance] factory method to
 * create an instance of this fragment.
 */
class waveformchart : Fragment() {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        var i=1
        capturebutton.setOnClickListener(){
            val loading = Loading(requireActivity() )
            loading.startLoading()
            CoroutineScope(IO).launch{

                (activity as chart).change_bool(false)
                delay(1000)

                val str= sockcreation(Ip, port,"wave")!!
                activity?.runOnUiThread{

                    var x : Double = -5.0
                    var y : Double = 0.0
                    linechart = linechartview as LineChart
                    linechart2 =  linechartview2 as LineChart
                    val entries = ArrayList<Entry>()
                    val entries2 = ArrayList<Entry>()
                    val entries3= ArrayList<Entry>()
                    val entries4= ArrayList<Entry>()
                    val entries5= ArrayList<Entry>()
                    val entries6= ArrayList<Entry>()
                    val entries7= ArrayList<Entry>()
                    val entries8= ArrayList<Entry>()
                    val entries9= ArrayList<Entry>()
                    var result2= arrayListOf<Int>(250)
                    for (i in 0..240){
                        result2.add(1)
                    }
                    //VLL ab
                    for (i in 0..79){
                        var first= str!![i].toInt()
                        if (first<0){
                            first = 256+first

                        }
                        var second= str!![80+i].toInt()
                        if (second<0){
                            second = 256+second

                        }
                        val tmp1= (first-second)

                        result2[i]=tmp1

                    }
                    //VLL bc
                    for (i in 0..79){
                        var first= str!![80+i].toInt()
                        if (first<0){
                            first = 256+first

                        }
                        var second= str!![160+i].toInt()
                        if (second<0){
                            second = 256+second

                        }
                        val tmp1= (first-second)

                        result2[80+i]=tmp1

                    }
                    //VLL ca
                    for (i in 0..79){
                        var first= str!![160+i].toInt()
                        if (first<0){
                            first = 256+first

                        }
                        var second= str!![0+i].toInt()
                        if (second<0){
                            second = 256+second

                        }
                        val tmp1= (first-second)

                        result2[160+i]=tmp1

                    }

                    x=-5.0
                    y=0.0
                    for (i in 0..79) {
                        x = x + 0.1
//                        if (userObject != null) {
//                            entries.add(Entry(x.toFloat(), userObject.value[i].toFloat()))
//                        }
                        var tmp= result2!![i].toInt()

                        y = tmp.toDouble()
                        entries.add(Entry(x.toFloat(),y.toFloat()))
                    }
                    buttontst2.isChecked=true
                    x=-5.0
                    y=0.0
                    for (i in 0..79) {
                        x = x + 0.1
//                        if (userObject != null) {
//                            entries.add(Entry(x.toFloat(), userObject.value[i].toFloat()))
//                        }
                        var tmp= result2!![80+i].toInt()



                        y = tmp.toDouble()
                        entries2.add(Entry(x.toFloat(),y.toFloat()))
                    }
                    x=-5.0
                    y=0.0
                    for (i in 0..79) {
                        x = x + 0.1
//                        if (userObject != null) {
//                            entries.add(Entry(x.toFloat(), userObject.value[i].toFloat()))
//                        }
                        var tmp= result2!![160+i].toInt()

                        y = tmp.toDouble()
                        entries3.add(Entry(x.toFloat(),y.toFloat()))
                    }
                    x=-5.0
                    y=0.0
                    for (i in 0..79) {
                        x = x + 0.1
//                        if (userObject != null) {
//                            entries.add(Entry(x.toFloat(), userObject.value[i].toFloat()))
//                        }
                        var tmp= str!![0+i].toInt()
                        if (tmp<0){
                            tmp = 256+tmp

                        }
                        //tmp=tmp-127
                        y = tmp.toDouble()
                        entries4.add(Entry(x.toFloat(),y.toFloat()))
                    }
                    x=-5.0
                    y=0.0
                    for (i in 0..79) {
                        x = x + 0.1
//                        if (userObject != null) {
//                            entries.add(Entry(x.toFloat(), userObject.value[i].toFloat()))
//                        }
                        var tmp= str!![80+i].toInt()
                        if (tmp<0){
                            tmp = 256+tmp

                        }
                        //tmp=tmp-127
                        y = tmp.toDouble()
                        entries5.add(Entry(x.toFloat(),y.toFloat()))
                    }
                    x=-5.0
                    y=0.0
                    for (i in 0..79) {
                        x = x + 0.1
//                        if (userObject != null) {
//                            entries.add(Entry(x.toFloat(), userObject.value[i].toFloat()))
//                        }
                        var tmp= str!![160+i].toInt()
                        if (tmp<0){
                            tmp = 256+tmp

                        }
                        //tmp=tmp-127
                        y = tmp.toDouble()
                        entries6.add(Entry(x.toFloat(),y.toFloat()))
                    }
                    x=-5.0
                    y=0.0
                    for (i in 0..79) {
                        x = x + 0.1
//                        if (userObject != null) {
//                            entries.add(Entry(x.toFloat(), userObject.value[i].toFloat()))
//                        }
                        var tmp= str!![240+i].toInt()
                        if (tmp<0){
                            tmp = 256+tmp

                        }
                        //tmp=tmp-127
                        y = tmp.toDouble()
                        entries7.add(Entry(x.toFloat(),y.toFloat()))
                    }
                    x=-5.0
                    y=0.0
                    for (i in 0..79) {
                        x = x + 0.1
//                        if (userObject != null) {
//                            entries.add(Entry(x.toFloat(), userObject.value[i].toFloat()))
//                        }
                        var tmp= str!![320+i].toInt()
                        if (tmp<0){
                            tmp = 256+tmp

                        }
                        //tmp=tmp-127
                        y = tmp.toDouble()
                        entries8.add(Entry(x.toFloat(),y.toFloat()))
                    }
                    x=-5.0
                    y=0.0
                    for (i in 0..79) {
                        x = x + 0.1
//                        if (userObject != null) {
//                            entries.add(Entry(x.toFloat(), userObject.value[i].toFloat()))
//                        }
                        var tmp= str!![400+i].toInt()
                        if (tmp<0){
                            tmp = 256+tmp

                        }
                       // tmp=tmp-127
                        y = tmp.toDouble()
                        entries9.add(Entry(x.toFloat(),y.toFloat()))
                    }
                    var nnn=0
                    if(nnn==0){

                        var ooo=2
                    }
                    val dataSet = LineDataSet(entries, "ab")
                    val dataSet2 = LineDataSet(entries2, "bc")
                    val dataSet3 = LineDataSet(entries3, "ca")
                    val dataSet4 = LineDataSet(entries4, "a")
                    val dataSet5 = LineDataSet(entries5, "b")
                    val dataSet6 = LineDataSet(entries6, "c")
                    val dataSet7 = LineDataSet(entries7, "a")
                    val dataSet8 = LineDataSet(entries8, "b")
                    val dataSet9 = LineDataSet(entries9, "c")
                    dataSet.setColor(Color.parseColor("#ff0000"))
                    dataSet2.setColor(Color.parseColor("#0132ea"))
                    dataSet3.setColor(Color.parseColor("#019f08"))
                    dataSet4.setColor(Color.parseColor("#ff0000"))
                    dataSet5.setColor(Color.parseColor("#0132ea"))
                    dataSet6.setColor(Color.parseColor("#019f08"))
                    dataSet7.setColor(Color.parseColor("#ff0000"))
                    dataSet8.setColor(Color.parseColor("#0132ea"))
                    dataSet9.setColor(Color.parseColor("#019f08"))
                    dataSet.setDrawCircles(false)
                    dataSet2.setDrawCircles(false)
                    dataSet3.setDrawCircles(false)
                    dataSet4.setDrawCircles(false)
                    dataSet5.setDrawCircles(false)
                    dataSet6.setDrawCircles(false)
                    dataSet7.setDrawCircles(false)
                    dataSet8.setDrawCircles(false)
                    dataSet9.setDrawCircles(false)
//        dataSets.add(dataSet)
//        dataSets.add(dataSet2)
//        dataSets.add(dataSet3)
                    linechart2!!.setScaleEnabled(false)
                    linechartview.setScaleEnabled(false);

//        val rightAxis: YAxis = linechart.getAxisRight()
//        rightAxis.isEnabled = false
                    linechartview.getAxisRight().setDrawLabels(false);
//        linechart.getLegend().setEnabled(false);
                    linechart2!!.getAxisRight().setDrawLabels(false);
                    //      linechart2.getLegend().setEnabled(false);
                    val left=linechartview.getAxisLeft()
                    left.valueFormatter=MyValueFormatter()
                    //left.setValueFormatter(ClaimsYAxisValueFormatter())
                    val left2= linechart2!!.getAxisLeft()
                    left2.valueFormatter=MyValueFormatter2()
                    //left2.setValueFormatter(ClaimsYAxisValueFormatter2())
                    val xAxis: XAxis = linechartview.getXAxis()
                    xAxis.isEnabled = false
                    val xAxis2: XAxis = linechart2!!.getXAxis()
                    xAxis2.isEnabled = false
                    linechartview.getDescription().setEnabled(false);
                    linechartview.getAxisLeft().setDrawGridLines(false);
                    linechartview.getXAxis().setDrawGridLines(false);
                    linechart2!!.getDescription().setEnabled(false);
                    linechart2!!.getAxisLeft().setDrawGridLines(false);
                    linechart2!!.getXAxis().setDrawGridLines(false);

                     data = LineData(dataSet,dataSet2,dataSet3)

                     data3= LineData(dataSet7,dataSet8,dataSet9)
                     data2= LineData(dataSet4,dataSet5,dataSet6)
                    //

                    linechartview.setData(data)
                    linechartview.getData().setHighlightEnabled(false);
                    linechart2!!.setData(data3)
                    linechart2!!.getData().setHighlightEnabled(false);
                    linechartview.invalidate()
                    linechart2!!.invalidate()
                    linechartview.setData(data)
                    linechartview.getData().setHighlightEnabled(false);
                    linechart2!!.setData(data3)
                    linechart2!!.getData().setHighlightEnabled(false);


                }



                activity?.runOnUiThread{
                    loading!!.isDismiss()

                }
                (activity as chart).change_bool(true)
            }

            buttontst2.setOnClickListener(){
//            linechart.setData(data)
//            linechart.getData().setHighlightEnabled(false);
//            linechart.invalidate()
                buttontst3.isChecked=false
                linechartview.setData(data)
                linechartview.getData().setHighlightEnabled(false);
                linechartview2.setData(data3)
                linechartview2.getData().setHighlightEnabled(false);
                linechartview.invalidate()
                linechartview2.invalidate()
            }
            buttontst3.setOnClickListener(){
                buttontst2.isChecked=false
//            linechart.setData(data)
//            linechart.getData().setHighlightEnabled(false);
//            linechart.invalidate()
                linechartview.setData(data2)
                linechartview.getData().setHighlightEnabled(false);
                linechartview2.setData(data3)
                linechartview2.getData().setHighlightEnabled(false);
                linechartview.invalidate()
                linechartview2.invalidate()
            }









        }


//        mToastRunnablewave.run()
//        CoroutineScope(IO).launch{
//            val str=wave()
//            activity?.runOnUiThread{///////////////////
//                textView4.text= str.toString()
//            }
//
//            val x=3
//        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_waveformchart, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *`
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment waveformchart.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            waveformchart().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    // mHandler.postDelayed(this, 1500)


}
//class ClaimsYAxisValueFormatter : ValueFormatter() {
//    override fun getAxisLabel(value: Float, axis: AxisBase): String {
//        return value.toString() + "V"
//    }
//}
//class ClaimsYAxisValueFormatter2 : ValueFormatter() {
//    override fun getAxisLabel(value: Float, axis: AxisBase): String {
//        return value.toString() + "A"
//    }
//}
class MyValueFormatter : ValueFormatter() {
    private val format = DecimalFormat("###,##0.0")
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return format.format(value)+"V"
    }
}
class MyValueFormatter2 : ValueFormatter() {
    private val format = DecimalFormat("###,##0.0")
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return format.format(value)+"A"
    }
}
public suspend fun wave(): String {
    val str=dataexchange("192.168.160.1", 321, "wave")
    Log.i(LOG_TAG, "it workerd" + str)
    //  val gson = Gson()
    //  val userObject = gson.fromJson(str,Waveform_Data::class.java)
    return str
}




public suspend fun sockcreation(ip:String,port:Int, input:String): ByteArray? {
    //val clientSocket = Socket("192.168.160.1", 321)
    val clientSocket = Socket(ip, port)
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
    clientSocket!!.close()
    return buffer
    Log.i(Chart.LOG_TAG, "this is the end of function")
    // val `is` = clientSocket!!.g/etInputStream()


}


fun changeloading(){
    loading!!.isDismiss()
}