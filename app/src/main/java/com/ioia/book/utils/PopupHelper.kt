package com.ioia.book.utils

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.widget.DatePicker
import android.widget.PopupMenu
import android.widget.PopupWindow
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import java.util.*

object PopupHelper {
    fun showToast(context: Context, msg: String){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun showDatePicker(context: Context, callback: (datePicker: DatePicker, year: Int, month: Int, day: Int)->Unit){
        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog(context,
            DatePickerDialog.OnDateSetListener { dp, year, month, day ->
                callback(dp!!, year, month, day) },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }

    fun showPopupMenu(context: Context, anchorView: View, menuId:Int, onItemClick: (itemId: Int)-> Unit){
        val popupMenu = PopupMenu(context, anchorView)
        popupMenu.menuInflater.inflate(menuId, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { menuItem ->
            onItemClick(menuItem.itemId)
            true
        }
        popupMenu.show()
    }

    fun showPopupDialogBuilder(context: Context, title: String, msg: String): AlertDialog.Builder{
        val dialog = AlertDialog.Builder(context)
        dialog.setTitle(title)
        dialog.setMessage(msg)

        return dialog
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("RtlHardcoded")
    fun showPopupWindow(
        context: Context,
        layoutId: Int,
        onPopupInstantiate: (popup: PopupWindow)->Unit,
        onViewInstantiate: (popup: PopupWindow, view: View)->Unit){

        val popupWindow = PopupWindow(context)
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(layoutId, null, false)

        popupWindow.contentView = view
        popupWindow.isOutsideTouchable = true

        onViewInstantiate(popupWindow, view)
        onPopupInstantiate(popupWindow)
    }
}