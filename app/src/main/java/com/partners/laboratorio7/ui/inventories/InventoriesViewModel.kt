package com.partners.laboratorio7.ui.inventories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.partners.laboratorio7.Models.Inventary
import com.partners.laboratorio7.Models.Row

class InventoriesViewModel : ViewModel() {
    private var _inventories = MutableLiveData<ArrayList<Inventary>>()
    val inventories: LiveData<ArrayList<Inventary>>
        get() = _inventories

    init {
        _inventories.value = arrayListOf()
    }

    fun onAddRow(row: Inventary) {
        _inventories.value?.add(row)
    }
}
