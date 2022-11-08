package project.penadidik.geocoding.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import project.penadidik.geocoding.R
import project.penadidik.geocoding.base.BaseRecyclerAdapter
import project.penadidik.geocoding.databinding.ItemDetailDailyBinding

class DetailAdapter(
    private val dataBindingComponent: DataBindingComponent
) : BaseRecyclerAdapter<DetailModel>(
    callBack = object : DiffUtil.ItemCallback<DetailModel>() {
        override fun areItemsTheSame(oldItem: DetailModel, newItem: DetailModel): Boolean {
            return oldItem.dt_txt == newItem.dt_txt && oldItem.temp_in_main == newItem.temp_in_main
        }

        override fun areContentsTheSame(oldItem: DetailModel, newItem: DetailModel): Boolean {
            return oldItem.dt_txt == newItem.dt_txt && oldItem.temp_in_main == newItem.temp_in_main
        }

    }
) {
    override fun createBinding(parent: ViewGroup, viewType: Int?): ViewDataBinding = DataBindingUtil.inflate<ItemDetailDailyBinding>(
        LayoutInflater.from(parent.context), R.layout.item_detail_daily,
        parent, false, dataBindingComponent
        ).apply {  }

    override fun bind(binding: ViewDataBinding, item: DetailModel) {
        if (binding is ItemDetailDailyBinding) {
            binding.model = item
        }
    }

}