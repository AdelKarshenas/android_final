import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.tcp_ip.*
import com.google.gson.Gson
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_settings.view.*
import kotlinx.android.synthetic.main.fragment_eventsfrag.*
import kotlinx.android.synthetic.main.fragment_records_fragment.*
import kotlinx.android.synthetic.main.fragment_records_fragment.view.*
import kotlinx.android.synthetic.main.log_row.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import kotlin.random.Random
import kotlinx.android.synthetic.main.log_row.view.download_row as download_row1
import kotlinx.android.synthetic.main.log_row.view.end_date as end_date1

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Records_fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Records_fragment : Fragment() {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        class log_item(val log_rec:logging_data): Item<ViewHolder>(){

            override fun bind(viewHolder: ViewHolder, position: Int) {
                val randomValues = List(10) { Random.nextInt(0, 100) }
                var namefolder="/Myfile${randomValues[1]}"
                var namefile="Mymessage${randomValues[1]}.txt"
                var newstring="j"
                viewHolder.itemView.download_row.setOnClickListener(){
                    var idnum:Int= viewHolder.item.id.toInt()
                    idnum=idnum*-1
                    var textinput="bytenum$idnum"
                    if (idnum==2){
                        textinput="jklop"
                    }
                    CoroutineScope(Dispatchers.IO).launch{
                        var sts =sockcreation(Ip, port,"testtcp")
                        newstring="qq"
                        newstring=newstring+ sts!![0].toInt().toString()
                        newstring=newstring+ sts!![1].toInt().toString()
                        newstring=newstring+ sts!![2].toInt().toString()
                        newstring=newstring+ sts!![3].toInt().toString()
                        Log.i("nono","this is the value of buffer $newstring")
                        var string = newstring
                        var state = Environment.getExternalStorageState()
                        if (Environment.MEDIA_MOUNTED.equals(state)) {
                            val root: File? = requireActivity().getExternalFilesDir(null)
                            //      var root: File? =Environment.getExternalStorageDirectory()
                            var Dir: File = File(root?.absolutePath +namefolder)
                            if (Dir != null) {
                                if (!Dir.exists()) {

                                    Dir.mkdir()

                                }
                            }
                            if (Dir != null) {
                                if (Dir.exists()) {

                                    //   Dir.mkdir()
                                }
                            }
                            var file = File(Dir, namefile)
                            if (file.exists()) {

                                //   Dir.mkdir()
                            }
                            var fos = FileOutputStream(file)
                            fos.write(string.toByteArray())
                            fos.close()
                            //Log.i(Chart.LOG_TAG, "we are good")
                        } else {
                            //Log.i(Chart.LOG_TAG, "we are done!")
                        }
                        activity?.runOnUiThread{
                            Toast.makeText(activity, "file is created", Toast.LENGTH_SHORT).show()
                        }
                    }


                    //viewHolder.itemView.textView6.text="kkk"
                    viewHolder.itemView.sharerow.setTextColor(Color.parseColor("#000000"))
                    viewHolder.itemView.sharerow.setBackgroundColor(Color.parseColor("#86DEF8"))

                }
                viewHolder.itemView.sharerow.setOnClickListener(){

                        val root: File? = requireActivity().getExternalFilesDir(null)
                        var Dir: File = File(root?.absolutePath + namefolder)
                        var file = File(Dir, namefile)
                        if(file.exists())
                        {
                            Toast.makeText(activity, "the file is there", Toast.LENGTH_SHORT).show()
                            val intentShare = Intent(Intent.ACTION_SEND)
                            intentShare.type = "application/txt"
                            intentShare.putExtra(Intent.EXTRA_STREAM, Uri.parse(file.toString()))
                            startActivity(Intent.createChooser(intentShare, "Share the file ..."));
                        }
                        if(!file.exists()) Toast.makeText(activity, "the file is not there", Toast.LENGTH_SHORT).show()

                }
                viewHolder.itemView.strat_date.text=log_rec.start_date
                viewHolder.itemView.end_date.text=log_rec.end_date
                viewHolder.itemView.starttime.text=log_rec.start_time
                viewHolder.itemView.endtime.text=log_rec.end_time
                viewHolder.itemView.row_num.text=(position+1).toString()
                viewHolder.itemView.size.text=log_rec.size.toString()
                viewHolder.itemView.num_of_recs.text=log_rec.num_of_records.toString()
            }

            override fun getLayout(): Int {
                return R.layout.log_row
            }

        }




        val adapter= GroupAdapter<ViewHolder>()
        retrivelistbtn.setOnClickListener(){
            CoroutineScope(Dispatchers.IO).launch{
                val ststs=dataexchange(Ip, port,"loggingdata")
                val gson = Gson()
                val userObject = gson.fromJson(ststs, logging_json::class.java)
                val rec1=userObject.logs[0]
                val rec2=userObject.logs[1]
                activity?.runOnUiThread{
                    recycler_view_logging.adapter=adapter
                    adapter.clear()
                    adapter.add(log_item(rec1))
                    adapter.add(log_item(rec2))
                }
            }

        }




    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_records_fragment, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Records_fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Records_fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}




class log_record(val start_time:String,val end_time:String,val size:Int,val num_of_recs:Int)