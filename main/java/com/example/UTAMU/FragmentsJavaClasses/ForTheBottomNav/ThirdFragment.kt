package com.example.UTAMU.FragmentsJavaClasses.ForTheBottomNav
//import com.google.firebase.auth.FirebaseAuth;
import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.UTAMU.AdaptersJavaClasses.RestApiRCVA
import com.example.UTAMU.DataObjects.ForRest
import com.example.UTAMU.R
import kotlinx.android.synthetic.main.frag3.*
import org.json.JSONException
import java.util.*

class ThirdFragment : Fragment() {
    var db: SQLiteDatabase? = null

    //
    private val INTERNET_PERMISSION_CODE = 1
//    var requestQueue: RequestQueue? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
    var requestQueue: RequestQueue? = null

    val view: View = inflater.inflate(R.layout.frag3, container, false)
        val forRestArrayList: ArrayList<ForRest> = ArrayList<ForRest>()
        //        final TextView profName = (TextView) view.findViewById(R.id.profName);
//        final Button showData = (Button) view.findViewById(R.id.showData);
        val recyclerView =
            view.findViewById<View>(R.id.profName) as RecyclerView

        val schoolslayoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        requestQueue = Volley.newRequestQueue(context)
        val jsonObjectRequest =
            JsonArrayRequest(
                Request.Method.GET,
                ROOT_URL,
                null,
                Response.Listener { response ->
                    try {

//                        val jsonArray = response.getJSONArray("List of Posts")
                        for (i in 0 until response.length()) {
                            val jsonObject = response.getJSONObject(i)
                            val author =
                                jsonObject.getString("post_title") as String
                            val title =
                                jsonObject.getString("post_body") as String
                            val post =
                                jsonObject.getString("posting_date") as String
                            forRestArrayList.add(ForRest(title, post, author))
                            val restApiRCVA =
                                activity?.let { RestApiRCVA(it, forRestArrayList) }
                            recyclerView.adapter = restApiRCVA
                            recyclerView.layoutManager = schoolslayoutManager
                        }
                        Toast.makeText(activity, "response.length()", Toast.LENGTH_SHORT)
                            .show()
//                        tvutamuapi.text= jsonArray.toString()
                    } catch (e: JSONException) {
//                                    e.printStackTrace();
//                                    profName.setText(""+e);
                        Toast.makeText(activity, "error ocurred $e", Toast.LENGTH_SHORT)
                            .show()
                        println(e)
                    }
                },
                Response.ErrorListener { error ->
                    Toast.makeText(
                        context,
                        "got $error",
                        Toast.LENGTH_SHORT
                    ).show()
                })

        requestQueue.add(jsonObjectRequest)
        return view
    }

    private fun internetRequestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                activity!!,
                Manifest.permission.INTERNET
            )
        ) {
            AlertDialog.Builder(activity)
                .setTitle("need internet")
                .setMessage("connection i required")
                .setPositiveButton("OK") { dialog, which ->
                    ActivityCompat.requestPermissions(
                        activity!!,
                        arrayOf(Manifest.permission.INTERNET),
                        INTERNET_PERMISSION_CODE
                    )
                }
                .setNegativeButton("REJECT") { dialog, which -> dialog.dismiss() }
                .create().show()
        } else {
            ActivityCompat.requestPermissions(
                activity!!,
                arrayOf(Manifest.permission.INTERNET),
                INTERNET_PERMISSION_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == INTERNET_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(activity, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity, "Permission Granted", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val ROOT_URL = "http://192.168.43.87:5000/utamuapi/newsposts"
//        private const val ROOT_URL = "http://192.168.43.87:5000/api_posts"
//        private const val REGISTER = "http://192.168.43.87/Android/v1/user.php"
    }
}