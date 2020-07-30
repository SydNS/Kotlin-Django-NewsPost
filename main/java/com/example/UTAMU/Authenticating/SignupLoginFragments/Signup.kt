package com.example.UTAMU.Authenticating.SignupLoginFragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.UTAMU.R
import com.google.android.material.textfield.TextInputLayout
import org.json.JSONException
import org.json.JSONObject

class Signup : Fragment() {

    var signup_uname: TextInputLayout? = null
    var signup_uemail: TextInputLayout? = null
    var signup_upassword1: TextInputLayout? = null
    var signup_upassword2: TextInputLayout? = null
    var uname: String? = null
    var upassd: String? = null
    var upassd2: String? = null
    var uemail: String? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.signup, container, false)
        signup_uname = view.findViewById<View>(R.id.signup_uname) as TextInputLayout
        signup_uemail = view.findViewById<View>(R.id.signup_uemail) as TextInputLayout
        signup_upassword1 =
            view.findViewById<View>(R.id.signup_upassword1) as TextInputLayout
        signup_upassword2 =
            view.findViewById<View>(R.id.signup_upassword2) as TextInputLayout
        val signupButton =
            view.findViewById<View>(R.id.signupButton) as Button
        signupButton.setOnClickListener {
            //                if (upassd==upassd2){
            uname = signup_uname!!.editText!!.text.toString()
            upassd = signup_upassword1!!.editText!!.text.toString()
            upassd2 = signup_upassword2!!.editText!!.text.toString()
            uemail = signup_uemail!!.editText!!.text.toString()
            posting(uname!!, upassd!!, uemail!!)
            //                getChildFragmentManager().beginTransaction().replace(R.id.signuplayout,new Login()).commit();

//                    } else {
//                    Toast.makeText(getActivity(), "Passwords not matching", Toast.LENGTH_SHORT).show();
        }
        return view
    }

    private fun posting(
        uname: String,
        upasword: String,
        uemail: String
    ) {
        var requestQueue = Volley.newRequestQueue(activity)
        val parameters = JSONObject()
        try {
            parameters.put("username", uname)
            parameters.put("password", upasword)
            parameters.put("email", uemail)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val jsonObjReq =
            JsonObjectRequest(
                Request.Method.POST,
                ROOT_URL_POST,
                parameters,
                Response.Listener { response ->
                    Toast.makeText(activity, response.toString(), Toast.LENGTH_LONG).show()
                    //                        startActivity(new Intent(getActivity(), MainActivity.class));
                },
                Response.ErrorListener { })
        requestQueue.add(jsonObjReq)
    }

    companion object {
        private const val ROOT_URL_POST = "http://192.168.43.87:5000/WebIntApi/users/"
    }
}