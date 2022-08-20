package com.example.tcp_ip
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.Chart.LOG_TAG
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_eqand_ep.*
import kotlinx.android.synthetic.main.fragment_pa_and_qa.*
import kotlinx.android.synthetic.main.fragment_pa_and_qa.pa
import kotlinx.android.synthetic.main.fragment_vand_i.*
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
 * Use the [EqandEp.newInstance] factory method to
 * create an instance of this fragment.
 */
class EqandEp : Fragment() {
    var infobackupPandQa:info?=null

    fun settext(Str :info){
        infobackupPandQa=Str
        epa.text=Str.epa.toString()
        epb.text=Str.epb.toString()
        epc.text=Str.epc.toString()
        ept.text=Str.ept.toString()
        eqa.text=Str.eqa.toString()
        eqb.text=Str.eqb.toString()
        eqc.text=Str.eqc.toString()
        eqt.text=Str.eqt.toString()
    }
    fun setunit(Str: info)
    {
        var epaarray=unitInteger(Str.epa,"Wh")
        var epbarray=unitInteger(Str.epb,"Wh")
        var epcrray=unitInteger(Str.epc,"Wh")
        var eptarray=unitInteger(Str.ept,"Wh")
        var eqaarray=unitInteger(Str.eqa,"Varh")
        var eqbarray=unitInteger(Str.eqb,"Varh")
        var eqcarray=unitInteger(Str.eqc,"Varh")
        var eqtarray=unitInteger(Str.eqt,"Varh")
        epa.text=epaarray[0]
        EQ_EP_pa.text=epaarray[1]
        epb.text=epbarray[0]
        EQ_EP_pb.text=epbarray[1]
        epc.text=epcrray[0]
        EQ_EP_pc.text=epcrray[1]
        ept.text=eptarray[0]
        EQ_EP_pt.text=eptarray[1]
        eqa.text=eqaarray[0]
        EQ_EP_eqa.text=eqaarray[1]
        eqb.text=eqbarray[0]
        EQ_EP_eqb.text=eqbarray[1]
        eqc.text=eqcarray[0]
        EQ_EP_eqc.text=eqcarray[1]
        eqt.text=eqtarray[0]
        EQ_EP_eqt.text=eqtarray[1]

//        if (epaarray[1]==2.0f)Log.i(LOG_TAG,"this is function13    ${epaarray[1]} ${epaarray[0]}")
//        if (epaarray[1]==1.0f)
//        {
//          //  Log.i(LOG_TAG,"i am in if")
//            EQ_EP_pa.text="Wh"
//            epa.text= epaarray[0].toString()
//        }
//        if (epaarray[1]==2.0f)
//        {
//            Log.i(LOG_TAG,"i am in if that is Kwh")
//            EQ_EP_pa.text="KWh"
//            epa.text= epaarray[0].toString()
//        }
//        if (epbarray[1]==1.0f)
//        {
//            EQ_EP_pb.text="Wh"
//            epb.text= epbarray[0].toString()
//        }
//        if (epbarray[1]==2.0f)
//        {
//            EQ_EP_pb.text="KWh"
//            epb.text= epbarray[0].toString()
//        }
//        if (epcrray[1]==1.0f)
//        {
//            EQ_EP_pc.text="Wh"
//            epc.text= epcrray[0].toString()
//        }
//        if (epcrray[1]==2.0f)
//        {
//            EQ_EP_pc.text="kWh"
//            epc.text= epcrray[0].toString()
//        }
//        if (eptarray[1]==1.0f)
//        {
//            EQ_EP_pt.text="Wh"
//            ept.text= eptarray[0].toString()
//        }
//        if (eptarray[1]==2.0f)
//        {
//            EQ_EP_pt.text="kWh"
//            ept.text=eptarray[0].toString()
//        }
//
//        if (eqaarray[1]==1.0f)
//        {
//            EQ_EP_eqa.text="Var"
//            eqa.text=eqaarray[0].toString()
//        }
//
//        if (eqaarray[1]==2.0f)
//        {
//            EQ_EP_eqa.text="kVar"
//            eqa.text=eqaarray[0].toString()
//        }
//
//        if (eqbarray[1]==1.0f)
//        {
//            EQ_EP_eqb.text="Var"
//            eqb.text=eqbarray[0].toString()
//        }
//        if (eqbarray[1]==2.0f)
//        {
//            EQ_EP_eqb.text="kVar"
//            eqb.text=eqbarray[0].toString()
//        }
//        if (eqcarray[1]==1.0f)
//        {
//            EQ_EP_eqc1.text="Var"
//            eqc.text=eqcarray[0].toString()
//        }
//        if (eqcarray[1]==2.0f)
//        {
//            EQ_EP_eqc1.text="kVar"
//            eqc.text=eqcarray[0].toString()
//        }
//        if (eqtarray[1]==1.0f)
//        {
//            EQ_EP_eqt.text="Var"
//            eqt.text=eqtarray[0].toString()
//        }
//        if (eqtarray[1]==2.0f)
//        {
//            EQ_EP_eqt.text="kVar"
//            eqt.text=eqtarray[0].toString()
//        }
    }
    fun unitInteger(num:Double,unit:String): Array<String> {
        var tempunit=unit
        var tempnum=num
        if(num<=1000 && num>=0)
        {
            tempunit=tempunit
            var arrayreturn= arrayOf(tempnum.toString(),tempunit)
            return arrayreturn

        }
        else if(num<1000000 && num>1000)
        {
            tempunit="K"+tempunit
            Log.i("test num","num is ${(num.toFloat()/1000)}")
            var arrayreturn= arrayOf(UnitFloat(num.toFloat()/1000).toString(),tempunit)
            return arrayreturn
        }
        else if(num<1000000000 && num>1000000)
        {
            Log.i(LOG_TAG,"in mega loop")
            tempunit="M"+tempunit
            tempnum=tempnum/1000
            var arrayreturn= arrayOf(UnitFloat(tempnum.toFloat()/1000).toString(),tempunit)
            return arrayreturn
        }
        else if(num<1000000000000 && num>1000000000)
        {
            tempunit="G"+tempunit
            tempnum=tempnum/1000000
            var arrayreturn= arrayOf(UnitFloat(tempnum.toFloat()/1000).toString(),tempunit)
            return arrayreturn
        }
        else
            return  arrayOf(tempnum.toString(),tempunit)
    }
//    fun UnitFloat(num:Float): Float {
//        var num1:Double= 0.0
//        var num2:Float=0f
//        if(num>0 && num<10)
//        {
//            num1=Math.round(num*1000.0)/1000.0
//            num2=num1.toFloat()
//            return num2
//        }
//        if(num>10 && num<100)
//        {
//            num1=Math.round(num*100.0)/100.0
//            num2=num1.toFloat()
//            return  num2
//        }
//        if(num>100 && num<1000)
//        {
//            num1=Math.round(num*10.0)/10.0
//            num2=num1.toFloat()
//            return num2
//        }
//            return num2
//
//    }



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

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_eqand_ep, container, false)
    }
    private fun loadSavedPreferences() {
        val sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(activity)
        try {
            settext(infobackupPandQa!!)
        }
        catch (e:KotlinNullPointerException){}

        Log.i(LOG_TAG,"load function called")
    }
    private fun savePreferences(key: String, value: String) {
        val sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(activity)
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.commit()
    }
    fun saveData() {
        try {
            savePreferences("vb", infobackupPandQa!!.vb.toString())
        }
        catch (e:KotlinNullPointerException){

        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        loadSavedPreferences()
    }

    override fun onStop() {
        saveData()
        super.onStop()
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EqandEp.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                EqandEp().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}