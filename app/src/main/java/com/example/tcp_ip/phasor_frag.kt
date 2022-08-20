package com.example.tcp_ip
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.data.BarEntry
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_phasor_frag.*
import kotlinx.android.synthetic.main.fragment_phasor_frag.current
import kotlinx.android.synthetic.main.fragment_phasor_frag.text123
import kotlinx.android.synthetic.main.fragment_phasor_frag.txta
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.cos
import kotlin.math.sin

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [phasor_frag.newInstance] factory method to
 * create an instance of this fragment.
 */

var kComparison:Float?=null
var mLineView:phasorclass?=null
var radius_size:Float?=null
var radius:Float?=null

class phasor_frag : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    //    gets the data from the board and updates the screen
    fun getinfo(data_recived :BarChartData){
        vlna_text.text=data_recived.vlna_s.toString()+"∠"+data_recived.vlna_a.toString()
        vlnb_text.text=data_recived.vlnb_s.toString()+"∠"+data_recived.vlnb_a.toString()
        vlnc_text.text=data_recived.vlnc_s.toString()+"∠"+data_recived.vlnc_a.toString()
        vlla_text.text=data_recived.vlla_s.toString()+"∠"+data_recived.vlla_a.toString()
        vllb_text.text=data_recived.vllb_s.toString()+"∠"+data_recived.vllb_a.toString()
        vllc_text.text=data_recived.vllc_s.toString()+"∠"+data_recived.vllc_a.toString()
        ia_text.text=data_recived.ia_s.toString()+"∠"+data_recived.ia_a.toString()
        ib_text.text=data_recived.ib_s.toString()+"∠"+data_recived.ib_a.toString()
        ic_text.text=data_recived.ic_s.toString()+"∠"+data_recived.ic_a.toString()
        val phasor_array= arrayListOf<vector>()
        phasor_array.add(vector(data_recived.vlna_s,data_recived.vlna_a,"vln"))
        phasor_array.add(vector(data_recived.vlnb_s,data_recived.vlnb_a,"vln"))
        phasor_array.add(vector(data_recived.vlnc_s,data_recived.vlnc_a,"vln"))
        phasor_array.add(vector(data_recived.vlla_s,data_recived.vlla_a,"vll"))
        phasor_array.add(vector(data_recived.vllb_s,data_recived.vllb_a,"vll"))
        phasor_array.add(vector(data_recived.vllc_s,data_recived.vllc_a,"vll"))
        phasor_array.add(vector(data_recived.ia_s,data_recived.ia_a,"i"))
        phasor_array.add(vector(data_recived.ib_s,data_recived.ib_a,"i"))
        phasor_array.add(vector(data_recived.ic_s,data_recived.ic_a,"i"))
        val max_list=phasor_array.maxBy { it.size }
        val final_array = scale_and_return_with_x_and_y(phasor_array, radius!!.toDouble(),9)
        mLineView!!.setPointA(final_array as ArrayList<vector_with_xandy>)
        mLineView!!.draw()
    }

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
        return inflater.inflate(R.layout.fragment_phasor_frag, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        var axix: Array<Double> = emptyArray()
        val axixfloat = mutableListOf<Float>()
        var axixfinal: Array<Float> = emptyArray()
        var axixy: Array<Double> = emptyArray()
        val axixyfloat = mutableListOf<Float>()
        var axixyfinal: Array<Float> = emptyArray()
        val listY: MutableList<Double> = axixy.toMutableList()
        val listX: MutableList<Double> = axix.toMutableList()
        var listclass= arrayListOf<vector_with_xandy>()

        val voltage_array= arrayListOf<vector>()
        voltage_array.add(vector(30.0,30.0,"vll"))
        voltage_array.add(vector(30.0,150.0,"vll"))
        voltage_array.add(vector(30.0,270.0,"vll"))
        voltage_array.add(vector(29.0,330.0,"vll"))
        voltage_array.add(vector(30.0,210.0,"vll"))
        voltage_array.add(vector(30.0,90.0,"vll"))
        voltage_array.add(vector(12.0,45.0,"vll"))
        voltage_array.add(vector(15.0,135.0,"vll"))
        voltage_array.add(vector(19.0,225.0,"vll"))

        var k= (baseX!! / baseY!!).toFloat()
        kComparison = (k/0.49).toFloat()
        radius= (this.resources.displayMetrics.widthPixels*(0.40)/ kComparison!!).toFloat()
        val funarray= scale_and_return_with_x_and_y(voltage_array, radius!!.toDouble(),9)
        radius_size=radius
//        var kComparison = 1f


        listclass.add(vector_with_xandy(radius!! * cos(Math.toRadians(30.0)!!)+ centerX!!, radius!! * sin(Math.toRadians(30.0)!!)+ centerY!!,"volt"))
        listclass.add(vector_with_xandy(radius!! * cos(Math.toRadians(150.0)!!)+ centerX!!, radius!! * sin(Math.toRadians(150.0)!!)+ centerY!!,"volt"))
        listclass.add(vector_with_xandy(radius!! * cos(Math.toRadians(270.0)!!)+ centerX!!, radius!! * sin(Math.toRadians(270.0)!!)+ centerY!!,"volt"))
        listclass.add(vector_with_xandy(radius!! * cos(Math.toRadians(90.0)!!)+ centerX!!, radius!! * sin(Math.toRadians(90.0)!!)+ centerY!!,"volt"))
        listclass.add(vector_with_xandy(radius!! * cos(Math.toRadians(210.0)!!)+ centerX!!, radius!! * sin(Math.toRadians(210.0)!!)+ centerY!!,"volt"))
        listclass.add(vector_with_xandy(radius!! * cos(Math.toRadians(330.0)!!)+ centerX!!, radius!! * sin(Math.toRadians(330.0)!!)+ centerY!!,"volt"))
        val radius_two_third= radius!! /3*2
        listclass.add(vector_with_xandy(radius_two_third* cos(Math.toRadians(30.0)!!)+ centerX!!,radius_two_third* sin(Math.toRadians(30.0)!!)+ centerY!!,"current"))
        listclass.add(vector_with_xandy(radius_two_third* cos(Math.toRadians(60.0)!!)+ centerX!!,radius_two_third* sin(Math.toRadians(60.0)!!)+ centerY!!,"current"))
        listclass.add(vector_with_xandy(radius_two_third* cos(Math.toRadians(90.0)!!)+ centerX!!,radius_two_third* sin(Math.toRadians(90.0)!!)+ centerY!!,"current"))

        txta.setVisibility(View.VISIBLE);
        val m1 = (this.resources.displayMetrics.widthPixels / 2).toFloat()
        val m2 = (this.resources.displayMetrics.heightPixels / 3.5).toFloat()
        run {
            var x:Int
            var y:Int


            val tmp = m1 + radius!!
            txta.text = "0°"
            txta.measure(0, 0);
            x =txta.getMeasuredWidth();
            y=txta.getMeasuredHeight();
            txta.x = tmp
            txta.y = m2-y/2


            txtb.text="-90°"
            txtb.measure(0, 0);
            x =txtb.getMeasuredWidth();
            y=txtb.getMeasuredHeight();
            txtb.x= (m1+ radius!! * cos(Math.toRadians(90.toDouble()))).toFloat()-x/2
            txtb.y=(m2+ radius!! * sin(Math.toRadians(90.toDouble()))).toFloat()


            txtc.text="-30°"
            txtc.measure(0, 0);
            x =txtc.getMeasuredWidth();
            y=txtc.getMeasuredHeight();
            txtc.x= (m1+ radius!! * cos(Math.toRadians(30.toDouble()))).toFloat()
            txtc.y=(m2+ radius!! * sin(Math.toRadians(30.toDouble()))).toFloat()


            txtd.x= (m1+ radius!! * cos(Math.toRadians(60.toDouble()))).toFloat()
            txtd.y=(m2+ radius!! * sin(Math.toRadians(60.toDouble()))).toFloat()
            txtd.text="-60°"

            txte.text="-120°"
            txte.measure(0, 0);
            x =txte.getMeasuredWidth();
            y=txte.getMeasuredHeight();
            txte.x= (m1+ radius!! * cos(Math.toRadians(120.toDouble()))).toFloat()-2*x/3
            txte.y=(m2+ radius!! * sin(Math.toRadians(120.toDouble()))).toFloat()+y/5


            txtf.text="-150°"
            txtf.measure(0, 0);
            x =txtf.getMeasuredWidth();
            y=txtf.getMeasuredHeight();
            txtf.x= (m1+ radius!! * cos(Math.toRadians(150.toDouble()))).toFloat()-x
            txtf.y=(m2+ radius!! * sin(Math.toRadians(150.toDouble()))).toFloat()


            txtg.text="-180°"
            txtg.measure(0, 0);
            x =txtg.getMeasuredWidth();
            y=txtg.getMeasuredHeight();
            val temp=(m1+ radius!! * cos(Math.toRadians(180.toDouble()))).toFloat()
            //txtg.text=heightoftext1.toString()
            txtg.x= temp-x
            txtg.y=(m2+ radius!! * sin(Math.toRadians(180.toDouble()))).toFloat()-y/2

            txth.text="30°"
            txth.measure(0, 0);
            x =txth.getMeasuredWidth();
            y=txth.getMeasuredHeight();
            txth.x= (m1+ radius!! * cos(Math.toRadians(-30.toDouble()))).toFloat()+0.26f*x
            txth.y=(m2+ radius!! * sin(Math.toRadians(-30.toDouble()))).toFloat()-y/2

            txti.text="60°"
            txth.measure(0, 0);
            x =txth.getMeasuredWidth();
            y=txth.getMeasuredHeight();
            txti.x= (m1+ radius!! * cos(Math.toRadians(-60.toDouble()))).toFloat()
            txti.y=(m2+ radius!! * sin(Math.toRadians(-60.toDouble()))).toFloat()-y


            txtj.text="90°"
            txtj.measure(0, 0);
            x =txtj.getMeasuredWidth();
            y=txtj.getMeasuredHeight();
            txtj.x= (m1+ radius!! * cos(Math.toRadians(-90.toDouble()))).toFloat()-x/2
            txtj.y=(m2+ radius!! * sin(Math.toRadians(-90.toDouble()))).toFloat()-y

            txtk.text="120°"
            txtk.measure(0, 0);
            x =txtk.getMeasuredWidth();
            y=txtk.getMeasuredHeight();
            txtk.x= (m1+radius!!* cos(Math.toRadians(-120.toDouble()))).toFloat()-2*x/3
            txtk.y=(m2+ radius!! * sin(Math.toRadians(-120.toDouble()))).toFloat()-y


            txtl.text="150°"
            txtl.measure(0, 0);
            x =txtl.getMeasuredWidth();
            y=txtl.getMeasuredHeight();
            txtl.x= (m1+radius!!* cos(Math.toRadians(-150.toDouble()))).toFloat()-x
            txtl.y=(m2+radius!!* sin(Math.toRadians(-150.toDouble()))).toFloat()-y

        }
        axix=listX.toTypedArray()

        for (x in axix)
        {
            axixfloat.add(x.toFloat())
        }

        axixyfinal=axixyfloat.toTypedArray()
        //  axix= arrayOf(950f,200f,800f,200f,1000f,1000f,950f,200f,800f)
        //axixy= arrayOf(1000f,1000f,1400f,800f,1000f,1200f,1000f,1000f,1400f)
        var colorarray= arrayOf("#E53935","#3949AB","#007500","#E53935","#3949AB","#007500","#E53935","#3949AB","#007500")
        mLineView = text123 as phasorclass
        val CurrentBtn = current
        val VoltageBtn = voltage
        val PowerBtn = power

        mLineView!!.setPointA(funarray as ArrayList<vector_with_xandy>)
        //mLineView!!.setPointB(axixyfinal)
        mLineView!!.setColor(colorarray)
        PowerBtn.isChecked= true
        PowerBtn.setTextColor(Color.GREEN)
        Group.addOnButtonCheckedListener { ToggleButton, checkedId: Int, isChecked: Boolean ->
            if (isChecked) {
                when (checkedId) {
                    R.id.current -> {
                        CurrentBtn.setTextColor(Color.GREEN)

//                        PowerBtn.setTextColor(Color.parseColor("#606060"))
//                        VoltageBtn.setTextColor(Color.parseColor("#606060"))
                        if (mLineView!!.getstatus2()!=1){
                            mLineView!!.setStatus2(1)
                            CurrentBtn.setTextColor(Color.GREEN)
                        }
//                        else if(mLineView.getstatus2()==1){
//                            CurrentBtn.setTextColor(Color.parseColor("#606060"))
//                            mLineView!!.setStatus2(-1)
//                        }

//                        mLineView!!.setStatus2(3)

////                        mLineView!!.setBackgroundColor(Color.WHITE)
                        mLineView!!.draw()
//                        text1.text="200"
//                        text2.text="500"
//                        text3.text="100"
//                        text6.text="254"
//                        text7.text="742"
//                        text8.text="110"
//                        text10.text="140"
//                        text12.text="85"
//                        text15.text="144"
                    }

                    R.id.voltage -> {
                        PowerBtn.isChecked=false

                        VoltageBtn.setTextColor(Color.GREEN)
                        PowerBtn.setTextColor(Color.parseColor("#606060"))
                        //CurrentBtn.setTextColor(Color.parseColor("#606060"))
                        //     PowerBtn.color
                        mLineView!!.setStatus(3)
                        mLineView!!.draw()


                        ////                        mLineView!!.setBackgroundColor(Color.WHITE)
//                        text1.text="210"
//                        text2.text="540"
//                        text3.text="200"
//                        text6.text="234"
//                        text7.text="762"
//                        text8.text="10"
//                        text10.text="440"
//                        text12.text="850"
//                        text15.text="190"


                    }
                    R.id.power -> {
                        VoltageBtn.isChecked=false
                        PowerBtn.setTextColor(Color.GREEN)
                        VoltageBtn.setTextColor(Color.parseColor("#606060"))
                        //CurrentBtn.setTextColor(Color.parseColor("#606060"))
                        mLineView!!.setStatus(2)
                        mLineView!!.draw()



                    }
                }
            }
            else{
                when (checkedId){
                    R.id.current -> {
                        CurrentBtn.setTextColor(Color.parseColor("#606060"))
                        mLineView!!.setStatus2(-1)
                        mLineView!!.draw()


                    }
                    R.id.voltage -> {
                        VoltageBtn.setTextColor(Color.parseColor("#606060"))
                        mLineView!!.setStatus(-1)
                        mLineView!!.draw()

                    }
                    R.id.power -> {
                        PowerBtn.setTextColor(Color.parseColor("#606060"))
                        mLineView!!.setStatus(-1)
                        mLineView!!.draw()


                    }

                }
            }
        }
    }

    private val mToastRunnable1: Runnable = object : Runnable {
        var x3=10.0
        override fun run() {
            val voltage_array= arrayListOf<vector>()
            voltage_array.add(vector(30.0,30.0+x3,"vll"))
            voltage_array.add(vector(30.0,150.0,"vll"))
            voltage_array.add(vector(30.0,270.0,"vll"))
            voltage_array.add(vector(29.0,330.0,"vll"))
            voltage_array.add(vector(30.0,210.0,"vll"))
            voltage_array.add(vector(30.0,90.0,"vll"))
            voltage_array.add(vector(12.0,45.0,"vll"))
            voltage_array.add(vector(15.0,135.0,"vll"))
            voltage_array.add(vector(19.0,225.0,"vll"))
            val funarray= scale_and_return_with_x_and_y(voltage_array, radius_size!!.toDouble(),9)
            mLineView!!.setPointA(funarray as ArrayList<vector_with_xandy>)
            mLineView!!.draw()
            x3=x3+5.0
            mHandler.postDelayed(this, 1500)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment phasor_frag.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            phasor_frag().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}