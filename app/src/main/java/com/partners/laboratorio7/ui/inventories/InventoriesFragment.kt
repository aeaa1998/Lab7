package com.partners.laboratorio7.ui.inventories

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.partners.laboratorio7.Adapters.ViewHolder.CustomViewHolder
import com.partners.laboratorio7.Models.Inventary
import com.partners.laboratorio7.Models.Row
import com.partners.laboratorio7.R
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.multiple_inventaries_row.view.*

class InventoriesFragment : Fragment() {
    private lateinit var recycler: RecyclerView
    companion object {
        fun newInstance() = InventoriesFragment()
    }
    var inventories: ArrayList<Inventary> = arrayListOf()

    private lateinit var viewModel: InventoriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val v = inflater.inflate(R.layout.inventories_fragment, container, false)
        recycler = v.findViewById(R.id.recycler_container_inventories)as RecyclerView
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = InventoriesAdapter()
        recycler.adapter?.notifyDataSetChanged()
        val btn = container?.rootView?.findViewById<View>(R.id.fab)
        btn?.visibility = View.VISIBLE


        btn?.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_inventories_fragment_to_create_inventary)

        }


        return v
    }

    override fun onResume() {
        super.onResume()
        recycler.adapter?.notifyDataSetChanged()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = activity!!.run {
            ViewModelProviders.of(this).get(InventoriesViewModel::class.java)
        }

        viewModel.inventories.observe(this, Observer {
            inventories = it
            recycler.adapter?.notifyDataSetChanged()
        })
    }
    inner class InventoriesAdapter: RecyclerView.Adapter<CustomViewHolder>() {
        override fun getItemCount() = inventories.size

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
            holder.view.inventary_info.text = viewModel.inventories.value?.get(position)?.name ?: ""
            holder.view.inventary_button.setOnClickListener {
                Toast.makeText(context,"YEAHHHH", Toast.LENGTH_LONG).show()
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CustomViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val d = viewModel.inventories.value
            val row = inventories[p1]
//            val binding = DataBindingUtil.inflate<RecyclerRowBinding>(layoutInflater, R.layout.recycler_row, p0, false)
//            binding.product = ProductBinding(row.getProduct().getName(), row.getQuantity())
            val cellForRow = layoutInflater.inflate(R.layout.multiple_inventaries_row, parent, false)

            return CustomViewHolder(cellForRow)

        }

    }

}


