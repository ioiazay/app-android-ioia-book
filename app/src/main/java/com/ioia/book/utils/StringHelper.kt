package com.ioia.book.utils

import java.text.NumberFormat
import java.util.*

object StringHelper {

    fun setInIDRFormat(value: Long, label: CurrencyLabel): String{
        val localeID = Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)

        val labelTemp = when(label){
            CurrencyLabel.RP -> {
                "Rp "
            }

            CurrencyLabel.IDR -> {
                "IDR "
            }
        }

        val valueStringDefault = numberFormat.format(value)
        val valueString = valueStringDefault.removeRange(0,2)

        return labelTemp + valueString
    }



    enum class CurrencyLabel{
        RP,
        IDR
    }

}