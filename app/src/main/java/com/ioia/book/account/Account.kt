package com.ioia.book.account

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_account")
class Account (

    @NonNull
    @PrimaryKey
    var phone: String = "",
    var activeBusinessId: String? = ""
){
    constructor(): this( "", "")
}