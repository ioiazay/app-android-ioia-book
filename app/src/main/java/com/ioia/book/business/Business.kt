package com.ioia.book.business

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_business")
class Business (
    @PrimaryKey(autoGenerate = true)
    var id: Long? = 0,
    var name: String? = "",
    var type: String? = ""
){
    constructor(): this(0, "", "")
}