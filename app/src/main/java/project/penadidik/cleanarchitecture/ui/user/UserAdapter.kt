package project.penadidik.cleanarchitecture.ui.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import project.penadidik.cleanarchitecture.R
import project.penadidik.cleanarchitecture.base.BaseRecyclerAdapter
import project.penadidik.cleanarchitecture.databinding.ItemUserBinding
import project.penadidik.cleanarchitecture.model.UserModel
import com.bumptech.glide.Glide

class UserAdapter(
    private val dataBindingComponent: DataBindingComponent,
    private val viewModel: UserViewModel
) : BaseRecyclerAdapter<UserModel>(
    callBack = object : DiffUtil.ItemCallback<UserModel>() {
        override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem.login == newItem.login && oldItem.htmlUrl == newItem.htmlUrl && oldItem.avatar == newItem.avatar
        }
    }) {

    override fun createBinding(parent: ViewGroup, viewType: Int?): ViewDataBinding =
        DataBindingUtil.inflate<ItemUserBinding>(
            LayoutInflater.from(parent.context), R.layout.item_user,
            parent, false, dataBindingComponent
        ).apply {

        }

    override fun bind(binding: ViewDataBinding, item: UserModel) {
        if (binding is ItemUserBinding) {
            binding.user = item

            Glide.with(binding.root)
                .load(item.avatar)
                .placeholder(R.drawable.ic_account_circle)
                .into(binding.userImage)

            binding.favorite.setOnClickListener {
                viewModel.setFavorite(item.id, item.isFavorite != true)
            }
        }
    }
}
