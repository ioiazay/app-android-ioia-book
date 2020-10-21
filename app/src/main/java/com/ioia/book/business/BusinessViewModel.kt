package com.ioia.book.business

import android.content.Context
import androidx.lifecycle.ViewModel

class BusinessViewModel(
    private val context: Context,
    private val repository: BusinessRepository
): ViewModel() {

}