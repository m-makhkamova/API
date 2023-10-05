package uz.itschool.api

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import uz.itschool.api.databinding.FragmentHomeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var list: MutableList<Item>
    private  lateinit var adapter:Adapter

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
        var binding = FragmentHomeBinding.inflate(inflater, container, false)
        list = mutableListOf()
        adapter = Adapter(list)
        binding.rv.adapter = adapter
        val url = "https://jsonplaceholder.org/posts"
        val requestQueue: RequestQueue = Volley.newRequestQueue(requireContext())
        val request = JsonArrayRequest(url, object :Response.Listener<JSONArray> {
            override fun onResponse(response: JSONArray?) {
                for (i in 0 until response!!.length()) {
                    val obj = response.getJSONObject(i)
                    val title = obj.getString("title")
                    val img = obj.getString("image")
                    val content = obj.getString("content")
                    val category = obj.getString("category")
                    val published = obj.getString("publishedAt")

                    val post = Item(category, content, img, published, title)
                    list.add(post)
                    adapter.notifyDataSetChanged()

                }
            }
        },
            object :Response.ErrorListener{
                override fun onErrorResponse(error: VolleyError?) {

                }
            })

        requestQueue.add(request)
        return binding.root

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}