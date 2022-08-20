package com.example.tcp_ip

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.Chart.LOG_TAG
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import kotlinx.android.synthetic.main.fragment_barchart.*
import java.lang.IllegalStateException
import java.lang.NullPointerException
import java.math.RoundingMode
import java.net.Socket
import java.text.DecimalFormat

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [mainchart.newInstance] factory method to
 * create an instance of this fragment.
 */
class mainchart : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    val yVals1 = ArrayList<BarEntry>()    /// yVals1 is power
    val yVals2 = ArrayList<BarEntry>()    /// yvals2 is current
    val yVals3 = ArrayList<BarEntry>()
    var statuskey=""
    val groupCount = 7
    var tempval = 0f
    val barWidth = 0.17f
    val barSpace = 0.05f
    val groupSpace = 0.34f
    var valueVnns= arrayOf<Float>()
    var valueVls=arrayOf<Float>()
    var valueIs=arrayOf<Float>()
    var x = 0f
    var userobject:BarChartData?=null
    val mHandler = Handler()
    var sock1: Socket? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_barchart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        val chart = chart as BarChart
        /// yvals3 is voltage
        ///// default datasets
//        yVals1.add(BarEntry(3f, 1f))
//        yVals2.add(BarEntry(3f, 1.5f))
//        yVals3.add(BarEntry(3f, 0.5f))
//        yVals1.add(BarEntry(5f, 1.5f))
//        yVals2.add(BarEntry(5f, 1f))
//        yVals3.add(BarEntry(5f, 0.5f))
//        yVals1.add(BarEntry(7f, 0.5f))
//        yVals2.add(BarEntry(7f, 1.5f))
//        yVals3.add(BarEntry(7f, 1f))
//        yVals1.add(BarEntry(9f, 1f))
//        yVals2.add(BarEntry(9f, 1.5f))
//        yVals3.add(BarEntry(9f, 0.5f))
//        yVals1.add(BarEntry(11f, 1.5f))
//        yVals2.add(BarEntry(11f, 1f))
//        yVals3.add(BarEntry(11f, 0.5f))
//        yVals1.add(BarEntry(13f, 1.5f))
//        yVals2.add(BarEntry(13f, 1f))
//        yVals3.add(BarEntry(13f, 1.2f))
//        yVals1.add(BarEntry(15f, 1f))
//        yVals2.add(BarEntry(15f, 1.5f))
//        yVals3.add(BarEntry(15f, 0.5f))
        //////
        Log.i(LOG_TAG,"test"+"begining of program"+statuskey)
        thd1.text = "20%"
        thd2.text = "50%"
        thd3.text = "10%"
        tda.text = "THDVa"
        tdb.text = "THDVb"
        tdc.text = "THDVc"
        var stringtest="{\"VnnDates\":[\"0.6\",\"0.2\"],\"VlnDates\":[\"0.2\",],\"IDates\":[\"0.4\"]}"
        if (Vnn.isChecked == true) {
            statuskey="vnn"
            Log.i(LOG_TAG,"primary key is vnn")
//            mHandler.postDelayed(mToastRunnable, 1)

            groupbtn.addOnButtonCheckedListener()
            { Toggle_button, checkedId: Int, isChecked: Boolean ->
                if (isChecked) {
                    when (checkedId) {
                        R.id.current -> {
                            statuskey = "i"
                            tda.text="THDIa"
                            tdb.text="THDIb"
                            tdc.text="THDIc"
                            Log.i(LOG_TAG, "test" + "I is clicked" + statuskey)
                            //   mHandler.postDelayed(mToastRunnable, 1)
                            userobject= (activity as chart).getuserobject()
                            yVals1.clear()
                            yVals2.clear()
                            yVals3.clear()
                            chart.clear()
                            setdata(userobject!!.VnnDates,userobject!!.VlnDates,userobject!!.IDates,userobject!!.thd,"i", userobject!!)
//                            yVals1.add(BarEntry(3f, 0.5f))
//                            yVals2.add(BarEntry(3f, 1f))
//                            yVals3.add(BarEntry(3f, 0.5f))
//                            yVals1.add(BarEntry(5f, 1.5f))
//                            yVals2.add(BarEntry(5f, 1f))
//                            yVals3.add(BarEntry(5f, 0.5f))
//                            yVals1.add(BarEntry(7f, 1.5f))
//                            yVals2.add(BarEntry(7f, 1.7f))
//                            yVals3.add(BarEntry(7f, 1f))
//                            yVals1.add(BarEntry(9f, 1f))
//                            yVals2.add(BarEntry(9f, 1.5f))
//                            yVals3.add(BarEntry(9f, 1.3f))
//                            yVals1.add(BarEntry(11f, 1f))
//                            yVals2.add(BarEntry(11f, 0.5f))
//                            yVals3.add(BarEntry(11f, 1.5f))
//                            yVals1.add(BarEntry(13f, 1.5f))
//                            yVals2.add(BarEntry(13f, 1f))
//                            yVals3.add(BarEntry(13f, 1.2f))
//                            yVals1.add(BarEntry(15f, 1f))
//                            yVals2.add(BarEntry(15f, 0.5f))
//                            yVals3.add(BarEntry(15f, 1.5f))
//                            chartcreatation(chart,yVals1,yVals2,yVals3)
//                            }
                        }
                        R.id.Vnn -> {
                            statuskey="vnn"
                            tda.text="THDVa"
                            tdb.text="THDVb"
                            tdc.text="THDVc"
//                            Log.i(LOG_TAG,"test2"+statuskey)
                            userobject= (activity as chart).getuserobject()
                            yVals1.clear()
                            yVals2.clear()
                            yVals3.clear()
                            chart.clear()
                            try {
                                setdata(userobject!!.VnnDates,userobject!!.VlnDates,userobject!!.IDates, userobject!!.thd,"vnn", userobject!!)
                            }catch (e:KotlinNullPointerException){

                            }

                        }
                        R.id.vl -> {
                            Log.i(LOG_TAG,"test3"+statuskey)
                            statuskey="vl"
                            tda.text="THDVab"
                            tdb.text="THDVbc"
                            tdc.text="THDVca"
                            Log.i(LOG_TAG,"test3"+statuskey)
                            userobject= (activity as chart).getuserobject()
                            yVals1.clear()
                            yVals2.clear()
                            yVals3.clear()
                            chart.clear()
                            setdata(userobject!!.VnnDates,userobject!!.VlnDates,userobject!!.IDates, userobject!!.thd,"vl", userobject!!)

                        }
                    }
                }
            }
        }
        Log.i(LOG_TAG,"test4"+statuskey)
    }


    private val mToastRunnable: Runnable = object : Runnable {
        override fun run() {
            tempval= tempval+0.1f

        }

    }

    fun setscaledata(array:Array<Float>, arraysize:Int):Array<Float>
    {
        var arraysize1=arraysize-1
        var arrayfinal= arrayOf<Float>()
        var maxvalue=array.max()
        Log.i(LOG_TAG,"min is ${array.min()}")
        if (maxvalue!!<=2)return array       /// numbers less than 2 are not working
        var scalevalue:Float= maxvalue!! /2
        for (i in 0 until  arraysize1+1)
        {
            // if(array[i]==0f)array[i]=0.2f
            array[i]=((array[i]-array.min()!!)*(1.8f)/(array.max()!!- array.min()!!))+0.2f
        }
        return array
    }
    fun setdata(VnnDates:Array<Float>?,VlnDates:Array<Float>?,IDates:Array<Float>?,thd:Array<Double>?,statuskeyfun:String?,allofit:BarChartData)
    {
        try {
            thd1.text=allofit.thd[0].toString()
            thd2.text=allofit.thd[1].toString()
            thd3.text=allofit.thd[2].toString()
        }
        catch ( e:IllegalStateException){

        }

        Log.i(LOG_TAG,"the 2000f is  " + IDates!![1].toString())
        valueVnns= setscaledata(VnnDates!!,21)
        valueVls = setscaledata(VlnDates!!,21)
        valueIs= setscaledata(IDates!!,21)


        if (statuskeyfun=="vnn"){
            Log.i(LOG_TAG,"globalVnn = "+valueVnns[0])
            try {
                thd1.text= thd!![0].toString()+"%"
                thd2.text= thd!![1].toString()+"%"
                thd3.text= thd!![2].toString()+"%"
            }
            catch (e: IllegalStateException){
                Log.i(LOG_TAG,"null error chart")
            }

            yVals1.clear()
            yVals2.clear()
            yVals3.clear()
            chart.clear()
            yVals1.add(BarEntry(3f, ((valueVnns[0].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals2.add(BarEntry(3f, ((valueVnns[1].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals3.add(BarEntry(3f, ((valueVnns[2].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals1.add(BarEntry(5f, ((valueVnns[3].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals2.add(BarEntry(5f, ((valueVnns[4].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals3.add(BarEntry(5f, ((valueVnns[5].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals1.add(BarEntry(7f, ((valueVnns[6].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals2.add(BarEntry(7f, ((valueVnns[7].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals3.add(BarEntry(7f, ((valueVnns[8].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals1.add(BarEntry(9f, ((valueVnns[9].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals2.add(BarEntry(9f, ((valueVnns[10].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals3.add(BarEntry(9f, ((valueVnns[11].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals1.add(BarEntry(11f, ((valueVnns[12].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals2.add(BarEntry(11f, ((valueVnns[13].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals3.add(BarEntry(11f, ((valueVnns[14].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals1.add(BarEntry(13f, ((valueVnns[15].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals2.add(BarEntry(13f, ((valueVnns[16].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals3.add(BarEntry(13f, ((valueVnns[17].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals1.add(BarEntry(15f, ((valueVnns[18].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals2.add(BarEntry(15f, ((valueVnns[19].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals3.add(BarEntry(15f, ((valueVnns[20].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            chartcreatation(chart,yVals1,yVals2,yVals3)
        }
        if (statuskeyfun=="vl"){
            try {
                thd1.text= thd!![3].toString()+"%"
                thd2.text= thd!![4].toString()+"%"
                thd3.text= thd!![5].toString()+"%"
            }
            catch (e: IllegalStateException){
                Log.i(LOG_TAG,"null error chart")
            }


            Log.i(LOG_TAG,"globalVl = "+valueVls[0])
            yVals1.clear()
            yVals2.clear()
            yVals3.clear()
            chart.clear()
            yVals1.add(BarEntry(3f,((valueVls[0].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals2.add(BarEntry(3f, ((valueVls[1].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals3.add(BarEntry(3f, ((valueVls[2].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals1.add(BarEntry(5f, (valueVls[3].toBigDecimal().setScale(1, RoundingMode.UP).toFloat())))
            yVals2.add(BarEntry(5f, ((valueVls[4].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals3.add(BarEntry(5f, ((valueVls[5].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals1.add(BarEntry(7f, ((valueVls[6].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals2.add(BarEntry(7f, ((valueVls[7].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals3.add(BarEntry(7f,((valueVls[8].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals1.add(BarEntry(9f, ((valueVls[9].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals2.add(BarEntry(9f, ((valueVls[10].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals3.add(BarEntry(9f, ((valueVls[11].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals1.add(BarEntry(11f, ((valueVls[12].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals2.add(BarEntry(11f, ((valueVls[13].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals3.add(BarEntry(11f, ((valueVls[14].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals1.add(BarEntry(13f, ((valueVls[15].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals2.add(BarEntry(13f, ((valueVls[16].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals3.add(BarEntry(13f, ((valueVls[17].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals1.add(BarEntry(15f, ((valueVls[18].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals2.add(BarEntry(15f, ((valueVls[19].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals3.add(BarEntry(15f, ((valueVls[20].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            chartcreatation(chart,yVals1,yVals2,yVals3)

        }
        if (statuskeyfun=="i"){

            try {
                thd1.text= thd!![6].toString()+"%"
                thd2.text= thd!![7].toString()+"%"
                thd3.text= thd!![8].toString()+"%"
            }
            catch (e: IllegalStateException){
                Log.i(LOG_TAG,"null error chart")
            }
//          Log.i(LOG_TAG,"globalI = "+valueIs[0])
            yVals1.clear()
            yVals2.clear()
            yVals3.clear()
            chart.clear()
            yVals1.add(BarEntry(3f, ((valueIs[0].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals2.add(BarEntry(3f, ((valueIs[1].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals3.add(BarEntry(3f, ((valueIs[2].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals1.add(BarEntry(5f, ((valueIs[3].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals2.add(BarEntry(5f, ((valueIs[4].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals3.add(BarEntry(5f, ((valueIs[5].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals1.add(BarEntry(7f, ((valueIs[6].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals2.add(BarEntry(7f, ((valueIs[7].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals3.add(BarEntry(7f,((valueIs[8].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals1.add(BarEntry(9f, ((valueIs[9].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals2.add(BarEntry(9f, ((valueIs[10].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals3.add(BarEntry(9f, ((valueIs[11].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals1.add(BarEntry(11f, ((valueIs[12].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals2.add(BarEntry(11f, ((valueIs[13].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals3.add(BarEntry(11f, ((valueIs[14].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals1.add(BarEntry(13f, ((valueIs[15].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals2.add(BarEntry(13f, ((valueIs[16].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals3.add(BarEntry(13f, ((valueIs[17].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals1.add(BarEntry(15f, ((valueIs[18].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals2.add(BarEntry(15f, ((valueIs[19].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            yVals3.add(BarEntry(15f, ((valueIs[20].toBigDecimal().setScale(1, RoundingMode.UP).toFloat()))))
            chartcreatation(chart,yVals1,yVals2,yVals3)
        }
    }
    fun chartcreatation (chart: BarChart, yVals1: ArrayList<BarEntry>, yVals2: ArrayList<BarEntry>, yVals3: ArrayList<BarEntry>){
        val xValsOriginalMillis = ArrayList<String>()
        xValsOriginalMillis.add("3")
        xValsOriginalMillis.add("5")
        xValsOriginalMillis.add("7")
        xValsOriginalMillis.add("9")
        xValsOriginalMillis.add("11")
        xValsOriginalMillis.add("13")
        xValsOriginalMillis.add("15")
        val dataSet1 = BarDataSet(yVals1.toMutableList(), "a")
        dataSet1.color = Color.parseColor("#FF3200")
        dataSet1.setAxisDependency(YAxis.AxisDependency.LEFT);
        val dataSet2 = BarDataSet(yVals2.toMutableList(), "b")
        dataSet2.color = Color.parseColor("#0399FD")
        dataSet2.setAxisDependency(YAxis.AxisDependency.LEFT);
        val dataSet3 = BarDataSet(yVals3.toMutableList(), "c")
        dataSet3.color = Color.parseColor("#81EB47")
        dataSet3.setAxisDependency(YAxis.AxisDependency.LEFT);
        val lineData = BarData(dataSet1, dataSet2, dataSet3)
        lineData.setValueFormatter(MyYAxisValueFormatter())
        lineData.barWidth = barWidth
        chart.data = lineData
        chart.isScaleXEnabled = false /// nmitavan zoom kard X-mehvar
        chart.isScaleYEnabled = false /// nmitavan zoom kard Y-mehvar
        chart.isDragEnabled = true
        chart.isDoubleTapToZoomEnabled = false
        chart.isHighlightPerDragEnabled = false  ///highlight while draging
        chart.isHighlightPerTapEnabled = false   ///highlight while taping
        chart.setTouchEnabled(false)
        chart.isEnabled = true //// dont know what it is
        chart.axisLeft.setDrawLimitLinesBehindData(true)
        chart.setDrawValueAboveBar(true)
        chart.setDrawBarShadow(false)
        chart.axisRight.setDrawAxisLine(false)
        chart.axisRight.setDrawLabels(false)
        chart.axisRight.setDrawGridLines(false)
        chart.description.text = "chart is awsome"
        //chart.description.setPosition(120f,12f)
        chart.description.isEnabled = false
        chart.setBackgroundColor(Color.WHITE)
        chart.setDrawGridBackground(true)
        chart.setGridBackgroundColor(Color.parseColor("#F2F2F2"))
        chart.setDrawBorders(false)
        chart.groupBars(0f, groupSpace, barSpace)
        chart.xAxis.axisMaximum = 0 + chart.barData.getGroupWidth(groupSpace, barSpace) * groupCount
        var xAxis: XAxis = chart.xAxis //// val xaxis
        xAxis.isEnabled = true   /// no label and no line on xaxis
        xAxis.setDrawLabels(true) /// no diffrence
//        xAxis.setDrawAxisLine(false)   // dont know
        xAxis.setCenterAxisLabels(true)
        xAxis.setDrawGridLines(false)
//        xAxis.axisMaximum= 0 + chart.barData.getGroupWidth(groupSpace, barSpace) * groupCount /// max Xs
        xAxis.axisMaximum = 7f
        //   xAxis.resetAxisMaximum()  // reset max
        xAxis.axisMinimum = 0f // min Xs
        //  xAxis.resetAxisMinimum() // reset min
        //     xAxis.spaceMax=10f  // it is not working as it should be
        // xAxis.setLabelCount(7,true) /// number of axis labels
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.valueFormatter = IndexAxisValueFormatter(xValsOriginalMillis)
        //   xAxis.textColor=R.color.design_default_color_primary /// color of axis
        // xAxis.textSize=10f    /// size of the axis labels
        xAxis.typeface = Typeface.DEFAULT// bold , type
        //    xAxis.addLimitLine(LimitLine(3f))
        //    chart.axisLeft.setDrawAxisLine(false)
        val yAxis: YAxis = chart.axisLeft
        yAxis.setDrawGridLines(false)
        yAxis.setDrawAxisLine(true)
        yAxis.axisMaximum = 2.3f
//    yAxis.valueFormatter=MyYAxisValueFormatter()
        yAxis.valueFormatter=ClaimsYAxisValueFormatter1()
        val legend = chart.legend
        legend.isEnabled = true
        legend.textColor = Color.BLACK
        legend.typeface = Typeface.DEFAULT_BOLD
        legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        legend.orientation = Legend.LegendOrientation.HORIZONTAL
        legend.setDrawInside(false)
        legend.formSize = 10f
        legend.textSize = 12f
        chart.invalidate()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment mainchart.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                mainchart().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}


class MyYAxisValueFormatter :  ValueFormatter() {
    private val mFormat: DecimalFormat
    fun getFormattedValue(value: Float, yAxis: YAxis?): String {

        return mFormat.format(value).toString() + " $"
    }

    init {
        mFormat = DecimalFormat("###,###,##0.0") // use one decimal
    }
}
class ClaimsYAxisValueFormatter1 : ValueFormatter() {
    private val mFormat: DecimalFormat
    override fun getAxisLabel(value: Float, axis: AxisBase): String {
        // use one decimal
        return mFormat.format(value).toString() + "%"
    }
    init {
        mFormat = DecimalFormat("###,###,##0.0") // use one decimal
    }

}