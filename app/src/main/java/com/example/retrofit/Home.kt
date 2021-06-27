package com.example.retrofit

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.retrofit.adapters.RvImageAdapter
import com.example.retrofit.adapters.ViewPagerAdapter
import com.example.retrofit.databinding.FragmentHomeBinding
import com.example.retrofit.databinding.ItemTabBinding
import com.example.retrofit.models.Category
import com.example.retrofit.models.ImageData
import com.example.retrofit.models.ImageData2
import com.example.retrofit.retrofit.ApiClient
import com.example.retrofit.retrofit.ApiService
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Home.newInstance] factory method to
 * create an instance of this fragment.
 */
class Home : Fragment() {
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

    lateinit var binding: FragmentHomeBinding
    lateinit var apiService: ApiService

    lateinit var categoryNameList: ArrayList<String>
    lateinit var categoryList: ArrayList<Category>

    lateinit var viewPagerAdapter: ViewPagerAdapter

    var TYPE = "photo"
    var KEY = "21502313-2d21ca91298451ec0ba0582ae"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        categoryList = ArrayList()

        categoryNameList = ArrayList()
        categoryNameList.add("food")
        categoryNameList.add("nature")
        categoryNameList.add("animals")
        categoryNameList.add("flowers")
        categoryNameList.add("buildings")
        categoryNameList.add("industry")
        categoryNameList.add("computer")
        categoryNameList.add("business")
        categoryNameList.add("education")

        apiService = ApiClient.getRetrofit().create(ApiService::class.java)

        for (i in 0 until 7){

            var categoryName = categoryNameList[i]
            var imageList: ArrayList<String> = ArrayList()

            apiService.getCategoryPhoto(categoryName, TYPE).enqueue(object : Callback<ImageData2>{
                override fun onFailure(call: Call<ImageData2>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(
                    call: Call<ImageData2>,
                    response: Response<ImageData2>
                ) {
                    if (response.isSuccessful){
                        for (i in response.body()?.hits!!.indices){
                            imageList.add(response.body()!!.hits[i].webformatURL)
                        }
                    }
                }
            })

            var category = Category(categoryName, imageList)
            categoryList.add(category)
        }

        viewPagerAdapter = ViewPagerAdapter(childFragmentManager, categoryList)
        binding.viewPager.adapter = viewPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        setTabs()

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val tabView = tab!!.customView
                var textView = tabView!!.findViewById<TextView>(R.id.tab_title)
                textView.setTextColor(Color.parseColor("#A3A3A3"))
                val linearLayout = tabView.findViewById<LinearLayout>(R.id.circle)
                linearLayout.visibility = View.INVISIBLE
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val tabView = tab!!.customView
                var textView = tabView!!.findViewById<TextView>(R.id.tab_title)
                textView.setTextColor(Color.parseColor("#FFFFFF"))
                val linearLayout = tabView.findViewById<LinearLayout>(R.id.circle)
                linearLayout.visibility = View.VISIBLE
            }

        })

        return binding.root
    }

    private fun setTabs() {
        val count: Int = binding.tabLayout.tabCount
        for (i in 0 until count) {
            var itemTabBinding = ItemTabBinding.inflate(layoutInflater)

            itemTabBinding.tabTitle.text = categoryList[i].name.toUpperCase()
            if (i == 0){
                itemTabBinding.circle.visibility = View.VISIBLE
                itemTabBinding.tabTitle.setTextColor(Color.parseColor("#FFFFFF"))
            }else{
                itemTabBinding.circle.visibility = View.INVISIBLE
            }

            binding.tabLayout.getTabAt(i)?.customView = itemTabBinding.root
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Home.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}