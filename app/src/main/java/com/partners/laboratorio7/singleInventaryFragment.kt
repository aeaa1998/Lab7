package com.partners.laboratorio7

import android.content.Intent
import android.content.pm.PackageManager
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.partners.laboratorio7.Adapters.ViewHolder.CustomViewHolder
import com.partners.laboratorio7.Models.Inventary
import com.partners.laboratorio7.Models.Row
import com.partners.laboratorio7.ui.inventories.InventoriesFragmentArgs
import com.partners.laboratorio7.ui.inventories.InventoriesFragmentDirections
import com.partners.laboratorio7.ui.inventories.InventoriesViewModel
import kotlinx.android.synthetic.main.card_view_row.view.*
import kotlinx.android.synthetic.main.multiple_inventaries_row.view.*


class singleInventaryFragment : Fragment() {
    lateinit var selectedInventory: Inventary
    lateinit var rows: ArrayList<Row>
    lateinit var recycler: RecyclerView
    lateinit var viewModelAll: InventoriesViewModel

    companion object {
        fun newInstance() = singleInventaryFragment()
    }

    private lateinit var viewModel: SingleInventaryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v =inflater.inflate(R.layout.single_inventary_fragment, container, false)
        recycler = v.findViewById(R.id.inventory_rows_recycler)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = SingleInventoryAdapter()
        recycler.adapter?.notifyDataSetChanged()
        val btn = container?.rootView?.findViewById<View>(R.id.fab)
        btn?.visibility = View.VISIBLE
        btn?.isClickable = true


        btn?.setOnClickListener {
            if (ContextCompat.checkSelfPermission(activity!!.applicationContext, android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity!!,
                    arrayOf(android.Manifest.permission.CAMERA),
                    1)
            }else{
                NavHostFragment.findNavController(this).navigate(R.id.action_singleInventaryFragment_to_scanCodeFragment)
            }
        }

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = activity.run {
            ViewModelProviders.of(this!!).get(SingleInventaryViewModel::class.java)
        }
        viewModelAll = activity.run {ViewModelProviders.of(this!!).get(InventoriesViewModel::class.java) }
        viewModel.inventory.value?.rows = selectedInventory.rows
        viewModel.inventory.value?.name = selectedInventory.name
        selectedInventory = singleInventaryFragmentArgs.fromBundle(arguments!!).selectedInventary
        viewModel.inventory.observe(this, Observer {
            rows = it.rows
            recycler.adapter?.notifyDataSetChanged()
        })

    }

    inner class SingleInventoryAdapter: RecyclerView.Adapter<CustomViewHolder>() {
        override fun getItemCount() = viewModel.inventory.value?.rows?.size ?: 0
        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
            holder.view.plus_quantity.setOnClickListener {
                viewModel.inventory.value!!.rows[position].quantity++
                viewModelAll.inventories.value!![App.indexInventary].rows[position].quantity++
                holder.view.quantity_product.text = viewModel.inventory.value!!.rows[position].quantity.toString()
            }
            holder.view.minus_quantity.setOnClickListener {
                val q =viewModel.inventory.value!!.rows[position].quantity--
                if(q >= 0){
                    viewModel.inventory.value!!.rows[position].quantity--
                    viewModelAll.inventories.value!![App.indexInventary].rows[position].quantity--
                    holder.view.quantity_product.text = viewModel.inventory.value!!.rows[position].quantity.toString()
                }else{
                    Toast.makeText(context, "No se puede tener una cantidad negativa de productos", Toast.LENGTH_LONG).show()

                }

            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CustomViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
//            val binding = DataBindingUtil.inflate<RecyclerRowBinding>(layoutInflater, R.layout.recycler_row, p0, false)
//            binding.product = ProductBinding(row.getProduct().getName(), row.getQuantity())
            val cellForRow = layoutInflater.inflate(R.layout.card_view_row, parent, false)

            return CustomViewHolder(cellForRow)

        }

    }

}


