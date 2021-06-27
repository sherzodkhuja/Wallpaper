package com.example.retrofit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.retrofit.databinding.FragmentImageBinding
import com.squareup.picasso.Picasso

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "image"
private const val ARG_PARAM2 = "name"

class Image : Fragment() {

    private var image: String? = null
    private var name: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            image = it.getString(ARG_PARAM1)
            name = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding: FragmentImageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImageBinding.inflate(inflater, container, false)
        Picasso.get().load(image).into(binding.image)
        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.categoryName.text = name?.toUpperCase()
        return binding.root
    }
}