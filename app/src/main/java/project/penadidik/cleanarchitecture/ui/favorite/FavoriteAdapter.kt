package project.penadidik.cleanarchitecture.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import project.penadidik.cleanarchitecture.R
import project.penadidik.cleanarchitecture.base.BaseRecyclerAdapter
import project.penadidik.cleanarchitecture.databinding.ItemFavoriteBinding
import project.penadidik.cleanarchitecture.model.FavoriteModel
import com.bumptech.glide.Glide

class FavoriteAdapter(
    private val dataBindingComponent: DataBindingComponent,
    private val viewModel: FavoriteViewModel
) : BaseRecyclerAdapter<FavoriteModel>(
    callBack = object : DiffUtil.ItemCallback<FavoriteModel>() {
        override fun areItemsTheSame(oldItem: FavoriteModel, newItem: FavoriteModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FavoriteModel, newItem: FavoriteModel): Boolean {
            return oldItem.login == newItem.login && oldItem.htmlUrl == newItem.htmlUrl && oldItem.avatar == newItem.avatar
        }
    }) {

    override fun createBinding(parent: ViewGroup, viewType: Int?): ViewDataBinding {
        return DataBindingUtil.inflate<ItemFavoriteBinding>(
            LayoutInflater.from(parent.context), R.layout.item_favorite,
            parent, false, dataBindingComponent
        )
    }

    override fun bind(binding: ViewDataBinding, item: FavoriteModel) {
        if (binding is ItemFavoriteBinding) {
            binding.user = item

            Glide.with(binding.root)
                .load(item.avatar)
                .placeholder(R.drawable.ic_account_circle)
                .into(binding.userImage)

            binding.delete.setOnClickListener {
                viewModel.deleteFavorite(item.id)
            }
        }
    }
}