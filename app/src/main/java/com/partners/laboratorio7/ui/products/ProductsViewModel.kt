package com.partners.laboratorio7.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.partners.laboratorio7.Models.Inventary
import com.partners.laboratorio7.Models.Product
import com.partners.laboratorio7.Models.Row

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

    fun hasCode(r: Product): Boolean{
        val filteredP = _products.value?.filter { product -> product.codigo === r.codigo }
        return !filteredP.isNullOrEmpty()
    }
    fun hasCodeString(code: String): Boolean{
        val filteredP = _products.value?.filter { product -> product.codigo === code }
        return !filteredP.isNullOrEmpty()
    }

    fun getProduct(code: String): Product?{
        val filteredP = _products.value?.filter { product -> product.codigo === code }
        if (filteredP?.isNotEmpty() == true)
            return filteredP.get(0)
        else
            return Product("a", "abdljksandljkasnd;asnd")
    }

}
