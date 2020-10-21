package com.ioia.book.customer

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_customer")
class Customer(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = 0,
    var name: String? = "",
    var phone: String? = ""
){
    constructor(): this(0, "", "")
}