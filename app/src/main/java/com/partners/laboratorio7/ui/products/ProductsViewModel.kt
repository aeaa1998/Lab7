package com.partners.laboratorio7.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.partners.laboratorio7.Models.Inventary
import com.partners.laboratorio7.Models.Product

class ProductsViewModel : ViewModel() {
    private var _products = MutableLiveData<ArrayList<Product>>()
    val products: LiveData<ArrayList<Product>>
        get() = _products

    init {
        _products.value = arrayListOf()
    }

    fun onAddRow(row: Product) {
        _products.value?.add(row)
    }
}
