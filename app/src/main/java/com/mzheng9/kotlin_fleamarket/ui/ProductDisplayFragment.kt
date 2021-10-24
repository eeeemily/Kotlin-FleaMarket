package com.mzheng9.kotlin_fleamarket.ui

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mzheng9.kotlin_fleamarket.MainActivity.Companion.EFFECT_SELECTION
import com.mzheng9.kotlin_fleamarket.MainActivity.Companion.SHOW_NOW_IMAGE
import com.mzheng9.kotlin_fleamarket.R
import com.mzheng9.kotlin_fleamarket.database.Product
import com.mzheng9.kotlin_fleamarket.databinding.ProductDisplayFragmentBinding


class ProductDisplayFragment : Fragment(), SharedPreferences.OnSharedPreferenceChangeListener {

    private val sharedViewModel: ProductDisplayViewModel by activityViewModels()
    private var binding: ProductDisplayFragmentBinding? = null
    private val productAdapter = ProductAdapter()
    private val prefs: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(activity)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bindingMain = ProductDisplayFragmentBinding.inflate(inflater, container, false)
        binding = bindingMain
        binding?.apply {
            addProductBtn.setOnClickListener {
                findNavController().navigate(R.id.action_productDisplayFragment_to_dataEntryFragment)
            }
            productsRecyclerview.run {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = productAdapter

            }
        }
        return bindingMain.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setImage()
        setBG()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sharedViewModel.products.observe(viewLifecycleOwner, {
            productAdapter.updateProducts(it)
        })
        binding?.apply {
            removeProductBtn.setOnClickListener {
                if (highlightedIndex != -1) {
                    val thisProduct = productAdapter.getProductAtPosition(highlightedIndex)
                    itemDeletedAlert(thisProduct)
//                    sharedViewModel.deleteProduct(product = thisProduct)
//                    deletePosition = null
                    findNavController().navigate(R.id.action_productDisplayFragment_self)
                } else {
                    context?.toast(getString(R.string.not_select_any_product_to_delete))
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        prefs.unregisterOnSharedPreferenceChangeListener(this)

    }

    fun itemDeletedAlert(product: Product) {
        val msg = resources.getString(R.string.product_deleted_alert, product.productName)
        val builder = AlertDialog.Builder(context)
        with(builder) {
            setTitle(R.string.alert)
            setMessage(msg)
            setPositiveButton(R.string.yes) { _, _ ->
                sharedViewModel.deleteProduct(product)
                highlightedIndex = -1
                context?.toast(getString(R.string.deleted_colon, product.productName))
//                context?.toast("Deleted: ${product.productName}")
            }
            setNegativeButton(R.string.no) { _, _ ->
                productAdapter.notifyDataSetChanged()
                highlightedIndex = -1
//                context?.toast(getString(R.string.deleted_colon, product.productName))
                context?.toast(getString(R.string.keep_colon, product.productName))
            }
            show()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when (key) {
            EFFECT_SELECTION -> {
                setImage()
            }
            SHOW_NOW_IMAGE -> {
                setBG()
            }

        }
    }
    private fun setBG() {
        val resID = if (prefs.getBoolean(
                SHOW_NOW_IMAGE,
                false
            )
        ) 1 else 0
        productAdapter.updateBG(resID)

    }
    private fun setImage() {
        val effect = prefs.getString(EFFECT_SELECTION, "0")?.toInt()
        if(effect!=null){
            productAdapter.updateProductImageView(effect)
        }
    }

}

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}
