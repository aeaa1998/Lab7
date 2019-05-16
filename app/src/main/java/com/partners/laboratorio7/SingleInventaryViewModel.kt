package com.partners.laboratorio7

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.partners.laboratorio7.Models.Inventary
import com.partners.laboratorio7.Models.Product
import com.partners.laboratorio7.Models.Row

class SingleInventaryViewModel : ViewModel() {
    private var _inventory = MutableLiveData<Inventary>()
    val inventory: LiveData<Inventary>
        get() = _inventory


//    init {
//        _inventory.value = Inventary(arrayListOf(), "Set Name")
//    }

    fun onAddRow(r: Row) {
        _inventory.value?.rows?.add(r)
    }

    fun checkProductExists(p: Product): Boolean{
        val checkRows = _inventory.value?.rows?.filter { row ->  row.product.codigo === p.codigo}
        return !checkRows.isNullOrEmpty()
    }

}

