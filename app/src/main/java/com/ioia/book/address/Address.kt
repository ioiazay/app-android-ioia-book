package com.ioia.book.address

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_address")
class Address(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = 0,
    var name: String? = "",
    var street: String? = "",
    var city: String? = "",
    var country: String? = "",
    var zipcode: String? = "",
    var customerId: Long? = 0
)