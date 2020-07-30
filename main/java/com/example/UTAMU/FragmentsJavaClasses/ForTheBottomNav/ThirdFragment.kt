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
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.UTAMU.AdaptersJavaClasses.RestApiRCVA
import com.example.UTAMU.DataObjects.ForRest
import com.example.UTAMU.R
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
        //        sqLiteDatabaseClass.readData();
//        profName.append("wsaswsws");
//        profName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (ContextCompat.checkSelfPermission(getActivity(),
//                        Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(getActivity(), "Granted already", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getActivity(), "Denied ", Toast.LENGTH_SHORT).show();
//                    internetRequestPermission();
//                }
//            }
//        });
        val schoolslayoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        requestQueue = Volley.newRequestQueue(context)
        val jsonObjectRequest =
            JsonObjectRequest(
                Request.Method.GET,
                ROOT_URL,
                null,
                Response.Listener { response ->
                    try {
                        val jsonArray = response.getJSONArray("List of Posts")
                        for (i in 0 until jsonArray.length()) {
                            val jsonObject = jsonArray.getJSONObject(i)
                            val author =
                                jsonObject.getString("author") as String
                            val title =
                                jsonObject.getString("title") as String
                            val post =
                                jsonObject.getString("subtitle") as String
                            forRestArrayList.add(ForRest(title, post, author))
                            val restApiRCVA =
                                activity?.let { RestApiRCVA(it, forRestArrayList) }
                            recyclerView.adapter = restApiRCVA
                            recyclerView.layoutManager = schoolslayoutManager
                        }
                    } catch (e: JSONException) {
//                                    e.printStackTrace();
//                                    profName.setText(""+e);
                        Toast.makeText(activity, "error ocurred$e", Toast.LENGTH_SHORT)
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
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(activity, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity, "Permission Granted", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val ROOT_URL = "http://192.168.43.87:5000/WebIntApi/allposts/"
//        private const val ROOT_URL = "http://192.168.43.87:5000/api_posts"
        private const val REGISTER = "http://192.168.43.87/Android/v1/user.php"
    }
}