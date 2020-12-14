package com.example.chuknorrisjokes

import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.chuknorrisjokes.model.Joke
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_web.*
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    private val TAG = "MAIN_ACTIVITY"
    private var jokesFragment: JokesFragment? = null
    private var webFragment: WebFragment? = null
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentManager: FragmentManager = supportFragmentManager
        println(savedInstanceState)
        jokesFragment = JokesFragment()
        webFragment = WebFragment()

        jokesButton.setOnClickListener {
            chooseFragment(fragmentManager, jokesFragment!!)
            jokesButton.setBackgroundResource(R.drawable.custom_button_pressed)
            webButton.setBackgroundResource(R.drawable.custom_button)
        }
        jokesButton.callOnClick()
        webButton.setOnClickListener {
            chooseFragment(fragmentManager, webFragment!!)
            jokesButton.setBackgroundResource(R.drawable.custom_button)
            webButton.setBackgroundResource(R.drawable.custom_button_pressed)
        }
    }


    private fun chooseFragment(fragmentManager: FragmentManager, fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        for (fragment in fragmentManager.fragments) {
            fragmentTransaction.hide(fragment)
        }
        if(!fragment.isAdded) {
            Log.d(TAG, "Add fragment - $fragment")
            fragmentTransaction.add(R.id.fragmentLayout, fragment)
        }else{
            Log.d(TAG, "Show fragment - $fragment")
            fragmentTransaction.show(fragment)
        }
        fragmentTransaction.commit()
    }


    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed()
        }
    }
}