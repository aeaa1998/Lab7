package com.partners.laboratorio7

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.partners.laboratorio7.Models.Inventary
import com.partners.laboratorio7.Models.Product
import com.partners.laboratorio7.ui.inventories.InventoriesViewModel
import com.partners.laboratorio7.ui.products.ProductsViewModel
import kotlinx.android.synthetic.main.fragment_create_inventary.*
import kotlinx.android.synthetic.main.fragment_create_product.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [createProduct.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [createProduct.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class createProduct : Fragment() {
lateinit var viewModel:ProductsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val btnFab = container?.rootView?.findViewById<View>(R.id.fab)
        btnFab?.visibility = View.GONE
        btnFab?.isClickable = false
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_create_product, container, false)
        val btn = v.findViewById<View>(R.id.send_button)
        viewModel = activity!!.run {
            ViewModelProviders.of(this).get(ProductsViewModel::class.java)
        }

        btn.setOnClickListener {
            val p = Product(product_name.text.toString(),product_code.text.toString())
            if (product_name.text.isEmpty() || product_code.text.isEmpty()){
                Toast.makeText(context, "Llene los campo", Toast.LENGTH_LONG).show()
            }else{
                if (viewModel.hasCode(p)){
                    Toast.makeText(context, "Ese codigo ya existe", Toast.LENGTH_LONG).show()
                }
                else{
                    viewModel.onAddRow(p)
                    view?.clearFocus()
                    Toast.makeText(context, "Se ah creado el producto", Toast.LENGTH_LONG).show()
                    NavHostFragment.findNavController(this).navigateUp()
                }
            }
        }
        return v
    }



}
