package com.example.chuknorrisjokes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chuknorrisjokes.model.Joke
import com.example.chuknorrisjokes.model.MObject
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_jokes.*
import kotlinx.android.synthetic.main.fragment_web.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable
import java.lang.NumberFormatException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [JokesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class JokesFragment : Fragment() {
    var service: JokeService? = null
    var linearLayoutManager: LinearLayoutManager? = null
    var myUserAdapter: JokeAdapter? = null
    var jokeList: ArrayList<Joke>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jokes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        linearLayoutManager = LinearLayoutManager(context)
        recyclerJokeList.layoutManager = linearLayoutManager
        recyclerJokeList.adapter = myUserAdapter
        reloadBtn.setOnClickListener() {
            val count: String = countOfJokeText.text.toString()
            if (count != "") {
                try {
                    getJokesList(count.toInt().toString())
                } catch (e: NumberFormatException) {
                    Toast.makeText(context, "Please enter a number, not letters", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Please enter a number", Toast.LENGTH_SHORT).show()
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }


    private fun getJokesList(number: String) {
        service = Common().retrofitServices
        recyclerJokeList.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        recyclerJokeList.layoutManager = linearLayoutManager
        service!!.getJokes(number).enqueue(object : Callback<MObject> {
            override fun onResponse(call: Call<MObject>, response: Response<MObject>) {
                jokeList = response.body()!!.jokeList as ArrayList<Joke>
                myUserAdapter = JokeAdapter(jokeList!!)
                myUserAdapter!!.notifyDataSetChanged()
                recyclerJokeList.adapter = myUserAdapter
            }
            override fun onFailure(call: Call<MObject>, t: Throwable) {
                println(t)
            }

        })
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment JokesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            JokesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}