package com.mzheng9.kotlin_fleamarket.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(product: Product)

    @Query("DELETE FROM product_table")
    fun deleteAll()

    @Delete
    fun deleteProduct(product: Product)

//    @Query("SELECT * FROM product_table WHERE product_order=(:id)")
//    fun getItem(id: Long): LiveData<Product?>

    @Query("SELECT * FROM product_table LIMIT 1")
    fun getAnyProduct(): Array<Product>

    @Query("SELECT * FROM product_table ORDER BY product_ID DESC")
    fun getAllProducts(): LiveData<List<Product>>
}