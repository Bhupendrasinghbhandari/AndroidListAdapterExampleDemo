package com.example.androidlistadapterexampledemo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidlistadapterexampledemo.databinding.FragmentProductBinding
import com.example.androidlistadapterexampledemo.utils.ProductListResponse
import com.google.gson.Gson

class ProductFragment : Fragment() {

    private var categoryName: String? = null
    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryName = arguments?.getString("category")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.categoryTitle.text = categoryName
    }

    companion object {
        fun newInstance(category: ProductListResponse.CategoryData): ProductFragment {
            return ProductFragment().apply {
                arguments = Bundle().apply {
                    putString("category", Gson().toJson(category))
                }
            }
        }
    }
}
