package com.mzheng9.kotlin_fleamarket.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mzheng9.kotlin_fleamarket.R
import com.mzheng9.kotlin_fleamarket.database.Product
import com.mzheng9.kotlin_fleamarket.databinding.FragmentDataEntryBinding


class DataEntryFragment : Fragment() {
    private val sharedViewModel: ProductDisplayViewModel by activityViewModels()
    private var binding: FragmentDataEntryBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataEntryBinding = FragmentDataEntryBinding.inflate(inflater, container, false)
        binding = dataEntryBinding
        binding?.apply {
            buttonAdd.setOnClickListener {
                val product = Product()
                product.productName = editProductName.text.toString()
                product.productPrice = editProductArtist.text.toString()
                product.productDate = editProductRelease.text.toString()
                product.productImgLink = editProductCoverLink.text.toString()
                product.productDescription = editProductComment.text.toString()
                sharedViewModel.addProduct(product)
                findNavController().navigate(R.id.action_dataEntryFragment_to_productDisplayFragment)
                context?.hideKeyboard(it)
            }
            buttonCancel.setOnClickListener {
                context?.toast(getString(R.string.did_not_add))
                findNavController().navigate(R.id.action_dataEntryFragment_to_productDisplayFragment)
                context?.hideKeyboard(it)
            }
        }
        return dataEntryBinding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}

fun Context.hideKeyboard(view: View) {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}
