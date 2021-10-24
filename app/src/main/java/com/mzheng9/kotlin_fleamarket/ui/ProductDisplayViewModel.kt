package com.mzheng9.kotlin_fleamarket.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.mzheng9.kotlin_fleamarket.database.Product
import com.mzheng9.kotlin_fleamarket.database.ProductRepository

class ProductDisplayViewModel(application: Application) : AndroidViewModel(application) {

    init {
        ProductRepository.initialize(application)
    }
    private val productRepository = ProductRepository.get()
    val products = productRepository.getAllProducts()

    fun addProduct(newProduct: Product) {
        productRepository.insert(newProduct)
    }

    fun deleteProduct(product: Product) {
        productRepository.deleteProduct(product)
    }
}
