package com.example.tcp_ip
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.Chart
import kotlinx.android.synthetic.main.fragment_eqand_ep.*
import kotlinx.android.synthetic.main.fragment_pa_and_qa.*
import kotlinx.android.synthetic.main.fragment_vand_i.*
import kotlinx.android.synthetic.main.fragment_vand_i.va

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PaAndQa.newInstance] factory method to
 * create an instance of this fragment.
 */
class PaAndQa : Fragment() {
    var infobackupPandQa:info?=null

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    fun settext(Str :info){
        infobackupPandQa=Str
        pa.text=Str.pa.toString()
        pb.text=Str.pb.toString()
        pc.text=Str.pc.toString()
        pt.text=Str.pt.toString()
        qa.text=Str.qa.toString()
        qb.text=Str.qb.toString()
        qc.text=Str.qc.toString()
        qt.text=Str.qt.toString()
    }
    fun setunit(Str: info)
    {
        var paInt:Double=Str.pa.toString().toDouble()
        var pbInt:Double=Str.pb.toString().toDouble()
        var pcInt:Double=Str.pc.toString().toDouble()
        var ptInt:Double=Str.pt.toString().toDouble()
        var qaInt:Double=Str.qa.toString().toDouble()
        var qbInt:Double=Str.qb.toString().toDouble()
        var qcInt:Double=Str.qc.toString().toDouble()
        var qtInt:Double=Str.qt.toString().toDouble()
        var paarray=unitInteger(paInt,"W")
        var pbarray=unitInteger(pbInt,"W")
        var pcrray=unitInteger(pcInt,"W")
        var ptarray=unitInteger(ptInt,"W")
        var qaarray=unitInteger(qaInt,"Var")
        var qbarray=unitInteger(qbInt,"Var")
        var qcarray=unitInteger(qcInt,"Var")
        var qtarray=unitInteger(qtInt,"Var")
        P_Q_unitpa.text=paarray[1]
        pa.text=paarray[0]
        P_Q_unitpb.text=pbarray[1]
        pb.text=pbarray[0]
        P_Q_unitpc.text=pcrray[1]
        pc.text=pcrray[0]
        P_Q_unitpt.text=ptarray[1]
        pt.text=ptarray[0]
        qa.text=qaarray[0]
        P_Q_unitqa.text=qaarray[1]
        qb.text=qbarray[0]
        P_Q_unitqb.text=qbarray[1]
        qc.text=qcarray[0]
        P_Q_unitqc.text=qcarray[1]
        qt.text=qtarray[0]
        P_Q_unitqt.text=qtarray[1]


//
//        if (qaarray[1]==1.0f)
//        {
//            P_Q_unitqa.text="Var"
//            qa.text=qaarray[0].toString()
//        }
//
//        if (qaarray[1]==2.0f)
//        {
//            P_Q_unitqa.text="kVar"
//            qa.text=qaarray[0].toString()
//        }
//
//        if (qbarray[1]==1.0f)
//        {
//            P_Q_unitqb.text="Var"
//            qb.text=qbarray[0].toString()
//        }
//        if (qbarray[1]==2.0f)
//        {
//            P_Q_unitqb.text="kVar"
//            qb.text=qbarray[0].toString()
//        }
//        if (qcarray[1]==1.0f)
//        {
//            P_Q_unitqc.text="Var"
//            qc.text=qcarray[0].toString()
//        }
//        if (qcarray[1]==2.0f)
//        {
//            P_Q_unitqc.text="KVar"
//            qc.text=qcarray[0].toString()
//        }
//        if (qtarray[1]==1.0f)
//        {
//            P_Q_unitqt.text="Var"
//            qt.text=qtarray[0].toString()
//        }
//        if (qtarray[1]==2.0f)
//        {
//            P_Q_unitqt.text="KVar"
//            qt.text=qtarray[0].toString()
//        }
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
            var arrayreturn= arrayOf(UnitFloat(num.toFloat()/1000).toString(),tempunit)
            return arrayreturn
        }
        else if(num<1000000000 && num>1000000)
        {
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


    private fun loadSavedPreferences() {
        val sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(activity)
        try {
            settext(infobackupPandQa!!)
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
            savePreferences("vb", infobackupPandQa!!.vb.toString())
        }
        catch (e:KotlinNullPointerException){

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadSavedPreferences()
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
        return inflater.inflate(R.layout.fragment_pa_and_qa, container, false)
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
         * @return A new instance of fragment PaAndQa.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                PaAndQa().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}