package com.example.kot2.Posts

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.kot2.R
import org.json.JSONException

class PostDetails : AppCompatActivity() {
    var requestQueue: RequestQueue? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)
        val detes = findViewById<View>(R.id.detes) as TextView
        val intentWithPostId = intent
        val ROOT_URL =
            "http://192.168.43.87:5000/WebIntApi/allposts/${intentWithPostId.extras!!.getInt("postId")}"
        var requestQueue = Volley.newRequestQueue(applicationContext)
        val jsonObjectRequestt =
            JsonObjectRequest(
                Request.Method.GET, ROOT_URL, null,
                Response.Listener { response ->
                    try {
                        val author =
                            response.getString("author") as String
                        val title = response.getString("title") as String
                        val post = response.getString("post") as String
                        val creationDate =
                            response.getString("creationDate") as String
                        detes.append("${response.getInt("id")}  $author $title $post $creationDate")
                    } catch (ex: JSONException) {
                        ex.printStackTrace()
                    }
                },
                Response.ErrorListener { error ->
                    Toast.makeText(applicationContext, "Sorry$error", Toast.LENGTH_SHORT)
                        .show()
                })
        requestQueue.add(jsonObjectRequestt)
    }
}