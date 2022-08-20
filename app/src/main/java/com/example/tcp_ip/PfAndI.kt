package com.example.tcp_ip

import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.Chart
import kotlinx.android.synthetic.main.fragment_pa_and_qa.*
import kotlinx.android.synthetic.main.fragment_pf_and_i.*
import kotlinx.android.synthetic.main.fragment_vand_i.*
import java.net.Socket

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
var sock2: Socket?=null

/**
 * A simple [Fragment] subclass.
 * Use the [PfAndU.newInstance] factory method to
 * create an instance of this fragment.
 */
class PfAndI: Fragment() {
    var infobackupPfandi:info?=null

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    fun settext(Str :info){
        infobackupPfandi=Str
        ia.text=Str.ia.toString()
        ib.text=Str.ib.toString()
        ic.text=Str.ic.toString()
        In.text=Str.In.toString()
        pfa.text=Str.pfa.toString()
        pfb.text=Str.pfb.toString()
        pfc.text=Str.pfc.toString()
        ui.text=Str.ui.toString()
    }
    fun setunit(Str: info)
    {
        var IaInt:Float=Str.ia.toString().toFloat()
        var IbInt:Float=Str.ib.toString().toFloat()
        var IcInt:Float=Str.ic.toString().toFloat()
        var ItInt:Float=Str.In.toString().toFloat()
        var PFaInt:Float=Str.pfa.toString().toFloat()
        var PFbInt:Float=Str.pfb.toString().toFloat()
        var PFcInt:Float=Str.pfc.toString().toFloat()
        var UInt:Float=Str.ui.toString().toFloat()
        var paarray=unitInteger(IaInt.toLong(),"A")
        var pbarray=unitInteger(IbInt.toLong(),"A")
        var pcrray=unitInteger(IcInt.toLong(),"A")
        var ptarray=unitInteger(ItInt.toLong(),"A")
        //ia.text=paarray[0]
        unitIa.text=paarray[1]
        //ib.text=pbarray[0]
        unitIb.text=pbarray[1]
        //ic.text=pcrray[0]
        unitIc.text=pcrray[1]
        //In.text=ptarray[0]
        unitIn.text=ptarray[1]
        //if (paarray[1]==2.0f) Log.i(Chart.LOG_TAG,"this is function13    ${paarray[1]} ${paarray[0]}")

    }
    fun unitInteger(num:Long,unit:String): Array<String> {
        var tempunit=unit
        var tempnum=num
        if(num<=1000 && num>=0)
        {
            tempunit=tempunit
            var arrayreturn= arrayOf(UnitFloat(num.toFloat()).toString(),tempunit)
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
            Log.i(Chart.LOG_TAG,"in mega loop")
            tempunit="M"+tempunit
            tempnum=tempnum/1000
            var arrayreturn= arrayOf(UnitFloat(tempnum.toFloat()/1000).toString(),tempunit)
            return arrayreturn
        }
        else if(num<1000000000000 && num>1000000000)
        {
            tempunit="G"+tempunit
            tempnum=tempnum/1000000
            var arrayreturn= arrayOf((tempnum.toFloat()/1000).toString(),tempunit)
            return arrayreturn
        }
        else
            return  arrayOf(tempnum.toString(),tempunit)
    }

    private fun loadSavedPreferences() {
        val sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(activity)
        try {
            settext(infobackupPfandi!!)
        }
        catch (e:KotlinNullPointerException){}

        Log.i(Chart.LOG_TAG,"load function called")
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
            savePreferences("vb", infobackupPfandi!!.vb.toString())
        }
        catch (e:KotlinNullPointerException){

        }
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
        return inflater.inflate(R.layout.fragment_pf_and_i, container, false)
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
         * @return A new instance of fragment PfAndU.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                PfAndI().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}