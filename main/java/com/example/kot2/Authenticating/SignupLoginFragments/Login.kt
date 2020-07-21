package com.example.kot2.Authenticating.SignupLoginFragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.kot2.Activities.SignupLogin
import com.example.kot2.Posts.MainActivity
import com.example.kot2.R
import com.example.kot2.SharePreforproject.Uerdetails
import com.google.android.material.textfield.TextInputLayout
import org.json.JSONException
import org.json.JSONObject

class Login : Fragment() {

    var loginuname: TextInputLayout? = null
    var loginpasswd: TextInputLayout? = null
    var luname: String? = null
    var lpasswd: String? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.login, container, false)
        val login =
            view.findViewById<View>(R.id.loginButton) as Button
        loginuname = view.findViewById<View>(R.id.loginuname) as TextInputLayout
        loginpasswd = view.findViewById<View>(R.id.loginpasswd) as TextInputLayout
        val rememberme = view.findViewById<View>(R.id.rememberme) as CheckBox
        login.setOnClickListener {
            val uname = loginuname!!.editText!!.text.toString()
            val upasswd = loginpasswd!!.editText!!.text.toString()
            posting(uname, upasswd)
            var unamepref= activity?.let { it1 -> Uerdetails(it1) }
            unamepref?.save("Username",uname)
            if (rememberme.isChecked) {
                Toast.makeText(activity, "You'll be remembered", Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }

    private fun posting(uname: String, upasword: String) {
        var requestQueue = Volley.newRequestQueue(activity)
        val parameters = JSONObject()
        try {
            parameters.put("username", uname)
            parameters.put("password", upasword)
            //            parameters.put("email", uemail);
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val jsonObjReq =
            JsonObjectRequest(
                Request.Method.POST,
                ROOT_URL_POST,
                parameters,
                Response.Listener { response ->
                    var loggedin: String? = null
                    try {
                        loggedin = response.getString("Message") as String
                        if (loggedin == "logged in") {
                            Toast.makeText(activity, loggedin, Toast.LENGTH_SHORT).show()
                            startActivity(Intent(activity, MainActivity::class.java))
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener {
                    Toast.makeText(activity, "Invalid Credentials", Toast.LENGTH_SHORT)
                        .show()
                })

        requestQueue.add(jsonObjReq)
    }

    companion object {
        private const val ROOT_URL_POST = "http://192.168.43.87:5000/WebIntApi/login/"
    }
}