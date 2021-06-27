package com.example.retrofit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.retrofit.adapters.RvImageAdapter
import com.example.retrofit.databinding.FragmentCategoryBinding
import java.io.Serializable

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Category.newInstance] factory method to
 * create an instance of this fragment.
 */
class Category : Fragment(), Serializable {
    // TODO: Rename and change types of parameters
    private var category: com.example.retrofit.models.Category? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            category = it.getSerializable(ARG_PARAM1) as com.example.retrofit.models.Category?
        }
    }

    lateinit var binding: FragmentCategoryBinding
    lateinit var rvImageAdapter: RvImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)

        rvImageAdapter = RvImageAdapter(category!!.imgList, category!!.name, object : RvImageAdapter.OnItemClickListener{
            override fun onClick(string: String, categoryName: String) {
                var bundle = Bundle()
                bundle.putString("image", string)
                bundle.putString("name", categoryName)
                findNavController().navigate(R.id.image2, bundle)
            }
        })

        binding.rv.adapter = rvImageAdapter

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Category.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: com.example.retrofit.models.Category) =
            Category().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }
}