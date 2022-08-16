package com.example.tcp_ip

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import kotlinx.android.synthetic.main.fragment1.*
import kotlinx.android.synthetic.main.fragment_chart1.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [chart1.newInstance] factory method to
 * create an instance of this fragment.
 */
class chart1 : Fragment() {
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
    var chartvarlist = mutableListOf<Chartvar>()
    var list= mutableListOf<BarEntry>()
    var Labellist = mutableListOf<String>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chart1, container, false)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //CoroutineScope(Dispatchers.IO).launch {
        //  var userObject=sock(adres,port)
        //vb.text="12"
//            va.text= userObject!!.va.toString()
//            vc.text=userObject.vc.toString()
//            ib.text=userObject.ib.toString()
//            ia.text=userObject.ia.toString()
//            ic.text=userObject.ic.toString()
//            vbc.text=userObject.vbc.toString()
//            vab.text=userObject.vab.toString()
//            vca.text=userObject.vca.toString()

        var name1: String? = ""
        var values : Float ?= 0f
        fillval()
        for(i in 0..8)
        {
            name1= chartvarlist.get(i).name
            values= chartvarlist.get(i).value1
            if (values != null) {
                list.add(BarEntry(i.toFloat(),values.toFloat()))
            }
            if (name1 != null) {
                Labellist.add(name1)
            }

        }
        val MY_COLORS = intArrayOf(Color.rgb(192, 0, 0), Color.rgb(255, 0, 0), Color.rgb(255, 192, 0),
                Color.rgb(127, 127, 127), Color.rgb(146, 208, 80), Color.rgb(0, 176, 80), Color.rgb(79, 129, 189))
        val colors = ArrayList<Int>()

        for (c in MY_COLORS) colors.add(c)


        var barDataset2=BarDataSet(list,"Values")
        barDataset2.setColors(colors)
        chart123.getDescription().setText("Description of my chart")
        var Data=BarData(barDataset2)
        chart123.data=Data
        var xAsis=chart123.xAxis
        xAsis.setValueFormatter(IndexAxisValueFormatter(Labellist))
        xAsis.position= XAxis.XAxisPosition.TOP
        xAsis.setDrawAxisLine(false)
        xAsis.setDrawGridLines(false)
        xAsis.granularity=1f
        xAsis.setLabelCount(Labellist.size)
        chart123.animateY(2000)
        chart123.invalidate()



    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment chart1.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            chart1().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    public fun fillval(){
        chartvarlist.clear()
        chartvarlist.add(Chartvar("Va",252f))
        chartvarlist.add(Chartvar("Vb",200f))
        chartvarlist.add(Chartvar("Vc",300f))
        chartvarlist.add(Chartvar("Vd",190f))
        chartvarlist.add(Chartvar("Vf",30f))
        chartvarlist.add(Chartvar("Ve",270f))
        chartvarlist.add(Chartvar("Vg",240f))
        chartvarlist.add(Chartvar("Vh",210f))
        chartvarlist.add(Chartvar("Vi",200f))


    }
}




class Chartvar {
    constructor(name1: String, value2: Float) {
        name = name1
        value1 = value2
    }

    public
    var name: String? = null
    var value1: Float? = null
}