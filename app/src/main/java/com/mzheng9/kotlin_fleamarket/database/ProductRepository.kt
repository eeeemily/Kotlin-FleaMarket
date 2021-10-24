package com.mzheng9.kotlin_fleamarket.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import java.util.concurrent.Executors


class ProductRepository private constructor(context: Context) {

    private val database: ProductDatabase = Room.databaseBuilder(
        context.applicationContext,
        ProductDatabase::class.java,
        "product_database"
    )
        .build()

    private val productDao = database.productDao()
    private val executor = Executors.newSingleThreadExecutor()

    fun getAllProducts(): LiveData<List<Product>> = productDao.getAllProducts()
//    fun getOneProducts(id: Long): LiveData<List<Product>> = productDao.getAnyProduct(id)

    fun insert(product: Product) {
        executor.execute {
            productDao.insert(product)
        }
    }

    fun deleteProduct(product: Product) {
        executor.execute {
            productDao.deleteProduct(product)
        }
    }

    companion object {
        private var INSTANCE: ProductRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = ProductRepository(context)
            }
        }

        fun get(): ProductRepository {
            return INSTANCE
                ?: throw java.lang.IllegalStateException("ProductRepository must be initialized.")
        }
    }
}
