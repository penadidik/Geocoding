package project.penadidik.geocoding.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import project.penadidik.geocoding.R
import project.penadidik.geocoding.base.BaseRecyclerAdapter
import project.penadidik.geocoding.databinding.ItemSearchBinding

class SearchAdapter(
    private val dataBindingComponent: DataBindingComponent,
    private val viewModel: SearchViewModel
) : BaseRecyclerAdapter<SearchModel>(
    callBack = object : DiffUtil.ItemCallback<SearchModel>() {
        override fun areItemsTheSame(oldItem: SearchModel, newItem: SearchModel): Boolean {
            return oldItem.lat == oldItem.lat && oldItem.state == newItem.state
        }

        override fun areContentsTheSame(oldItem: SearchModel, newItem: SearchModel): Boolean {
            return oldItem.lat == oldItem.lat && oldItem.state == newItem.state
        }

    }
) {
    override fun createBinding(parent: ViewGroup, viewType: Int?): ViewDataBinding =
        DataBindingUtil.inflate<ItemSearchBinding>(
            LayoutInflater.from(parent.context), R.layout.item_search,
            parent, false, dataBindingComponent
        ).apply {  }

    override fun bind(binding: ViewDataBinding, item: SearchModel) {
        if (binding is ItemSearchBinding) {
            binding.model = item

            binding.favorite.setOnClickListener {
                viewModel.setFavorite(item)
            }
        }
    }
}