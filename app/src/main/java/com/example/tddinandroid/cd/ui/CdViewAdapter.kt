package com.example.tddinandroid.cd.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.tddinandroid.cd.domain.Cd
import com.example.tddinandroid.databinding.CdAdapterItemBinding

class CdViewAdapter(private val data: List<Cd>, private val navController: NavController) :
    RecyclerView.Adapter<CdViewAdapter.ViewHolder>() {

    override fun getItemCount(): Int = 3

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CdAdapterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, navController)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(data[position])
    }

    class ViewHolder(
        private val binding: CdAdapterItemBinding,
        private val navController: NavController
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setData(data: Cd) {
            binding.albumName.text = data.albumName
            binding.artistName.text = data.artistName

            binding.root.setOnClickListener {
                navController
                    .navigate(CdListFragmentDirections.actionNavCdListToNavCdDetails(data))
            }
        }
    }
}