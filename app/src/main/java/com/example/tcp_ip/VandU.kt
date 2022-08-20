package com.example.tcp_ip
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.Chart.LOG_TAG
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_pa_and_qa.*
import kotlinx.android.synthetic.main.fragment_pf_and_i.*
import kotlinx.android.synthetic.main.fragment_vand_i.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalStateException
import java.net.Socket
import java.net.SocketException


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

var sock1: Socket?=null

/**
 * A simple [Fragment] subclass.
 * Use the [VandI.newInstance] factory method to
 * create an instance of this fragment.
 */
public var num:Int? =100


class VandU : Fragment() {
    var infobackupVandU:info?=null

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    fun settext(Str :info){
        infobackupVandU=Str
        va.text=Str.va.toString()
        vb.text=Str.vb.toString()
        vc.text=Str.vc.toString()
        vab.text=Str.vab.toString()
        vbc.text=Str.vbc.toString()
        vca.text=Str.vca.toString()
        uv.text=Str.uv.toString()
        f.text=Str.f.toString()
    }
    fun setunit(Str: info)
    {
        var VaInt:Double=Str.va.toString().toDouble()
        var VbInt:Double=Str.vb.toString().toDouble()
        var VcInt:Double=Str.vc.toString().toDouble()
        var VabInt:Double=Str.vab.toString().toDouble()
        var VcaInt:Double=Str.vca.toString().toDouble()
        var VbcInt:Double=Str.vbc.toString().toDouble()


        var paarray=unitInteger(VaInt,"V")
        var pbarray=unitInteger(VbInt,"V")
        var pcrray=unitInteger(VcInt,"V")
        var ptarray=unitInteger(VabInt,"V")
        var qaarray=unitInteger(VbcInt,"V")
        var qbarray=unitInteger(VcaInt,"V")
        //if (paarray[1]==2.0f) Log.i(Chart.LOG_TAG,"this is function13    ${paarray[1]} ${paarray[0]}")
        va.text=paarray[0]
        unitVa.text=paarray[1]
        vb.text=pbarray[0]
        unitVb.text=pbarray[1]
        vc.text=pcrray[0]
        unitVc.text=pcrray[1]
        vab.text=ptarray[0]
        unitab.text=ptarray[1]
        vbc.text=qaarray[0]
        unitbc.text=qaarray[1]
        vca.text=qbarray[0]
        unitca.text=qbarray[1]
    }
    fun unitInteger(num:Double,unit:String): Array<String> {
        var tempunit=unit
        var tempnum=num
        if(num<=1000 && num>=0)
        {
            tempunit=tempunit
            var arrayreturn= arrayOf(UnitFloat(tempnum.toFloat()).toString(),tempunit)
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



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(LOG_TAG,"on create first fragment")

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        Log.i(LOG_TAG,"on createvie first fragment")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vand_i, container, false)
    }
    private fun loadSavedPreferences() {
        val sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(activity)
        try {
            settext(infobackupVandU!!)
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
            savePreferences("vb", infobackupVandU!!.vb.toString())
        }
        catch (e:KotlinNullPointerException){

        }
    }
    override fun onDestroy() {
        //saveData()
        Log.i(LOG_TAG,"on destroy first fragment")

        super.onDestroy()
    }

    override fun onPause() {
        super.onPause()
        Log.i(LOG_TAG,"on pause first fragment")
    }


    override fun onStop() {
        saveData()

        super.onStop()
        Log.i(LOG_TAG,"on stop first fragment")
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i(LOG_TAG,"on viewcreated first fragment")
        var yay:String?=null
//

        loadSavedPreferences()
//    mHandler.postDelayed(mToastRunnable, 1500);



    }

    //    private val mToastRunnable: Runnable = object : Runnable {
//        override fun run() {
//            vtst1.text = num.toString()
//            num= num!! +52
//            if (num!! >1000)num=33
//            mHandler.postDelayed(this, 1000)
//        }
//    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment VandI.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                VandU().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
    private val mToastRunnable: Runnable = object : Runnable {
        override fun run() {
            CoroutineScope(Dispatchers.IO).launch {
                try{
                    val ststs=dataexchange("192.168.160.1", 321,"test")
                    val gson = Gson()
                    val userObject = gson.fromJson(ststs, info::class.java)

                    activity?.runOnUiThread{
                        try {
                            //infobackup= vb.text.toString()
                            vb.text=userObject.vb.toString()
                            vc.text=userObject.vc.toString()
                            vab.text=userObject.vab.toString()
                            vbc.text=userObject.vbc.toString()
                            vca.text=userObject.vca.toString()
                        }catch (e:IllegalStateException){
                            var x =3
                        }
                    }
                }catch (e:IllegalStateException){
                    var x=2

                }
                catch (e:SocketException){
                    var x=9
                }
                catch (e:KotlinNullPointerException){
                    println("jksdkj")
                }
            }
            mHandler.postDelayed(this, 1500)
        }
    }
}
