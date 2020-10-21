package com.ioia.book.utang.laporan

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ioia.book.utang.HutangRepository

@Suppress("UNCHECKED_CAST")
class LaporanViewModelFactory(
    private val context: Context,
    private val hutangRepository: HutangRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LaporanViewModel(context, hutangRepository) as T
    }
}