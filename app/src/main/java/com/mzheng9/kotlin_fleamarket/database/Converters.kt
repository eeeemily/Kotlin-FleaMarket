package com.mzheng9.kotlin_fleamarket.database

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun toUUID(uuid: String?): UUID? {
        return UUID.fromString(uuid)
    }

    @TypeConverter
    fun fromUUID(uuid: UUID?): String? {
        return uuid?.toString()
    }
}