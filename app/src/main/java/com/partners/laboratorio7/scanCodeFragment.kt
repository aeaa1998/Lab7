package com.partners.laboratorio7

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.google.zxing.Result
import com.partners.laboratorio7.App.Companion.indexInventary
import com.partners.laboratorio7.Models.Product
import com.partners.laboratorio7.Models.Row
import com.partners.laboratorio7.ui.inventories.InventoriesViewModel
import com.partners.laboratorio7.ui.products.ProductsViewModel
import me.dm7.barcodescanner.zxing.ZXingScannerView


class scanCodeFragment : Fragment(), ZXingScannerView.ResultHandler {
    lateinit var scannerView: ZXingScannerView
    lateinit var viewModelSingle: SingleInventaryViewModel
    lateinit var viewModelProducts: ProductsViewModel
    lateinit var viewModelAll: InventoriesViewModel
    override fun handleResult(p0: Result?) {
        if (!viewModelProducts.hasCodeString(p0.toString())){
            val p = viewModelProducts.getProduct(p0.toString()) ?: Product("error", "error")
            if (viewModelSingle.checkProductExists(p)){
                viewModelSingle.onAddRow(Row(p, 1))
                viewModelAll.inventories.value?.get(indexInventary)?.rows = viewModelSingle.inventory.value!!.rows
                NavHostFragment.findNavController(this).navigateUp()
                Toast.makeText(context, "Se ha adjuntado el producto", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(context, "El producto no existe", Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(context, "El codigo qr no existe", Toast.LENGTH_LONG).show()
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val btn = container?.rootView?.findViewById<View>(R.id.fab)
        btn?.visibility = View.GONE
        btn?.isClickable = false

        scannerView = ZXingScannerView(context)
        return scannerView
    }

    override fun onPause() {
        super.onPause()
        scannerView.stopCamera()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModelProducts = activity!!.run {
            ViewModelProviders.of(this).get(ProductsViewModel::class.java)
        }
        viewModelSingle = activity.run {
            ViewModelProviders.of(this!!).get(SingleInventaryViewModel::class.java)
        }
        viewModelAll = activity.run {
            ViewModelProviders.of(this!!).get(InventoriesViewModel::class.java)
        }

    }

    override fun onResume() {
        super.onResume()
        scannerView.setResultHandler(this)
        scannerView.startCamera()
    }


}
