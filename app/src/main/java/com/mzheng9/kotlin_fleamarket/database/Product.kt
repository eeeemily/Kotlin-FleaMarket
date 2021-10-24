package com.mzheng9.kotlin_fleamarket.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "product_table")
data class Product(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "product_ID")
    var productID: Long = 0L,

    @ColumnInfo(name = "product_name")
    var productName: String = "",

    @ColumnInfo(name = "product_price")
    var productPrice: String = "",

    @ColumnInfo(name = "product_date")
    var productDate: String = "",

    @ColumnInfo(name = "product_img_link")
    var productImgLink: String = "",

    @ColumnInfo(name = "product_description")
    var productDescription: String = ""
)