package project.penadidik.cleanarchitecture.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import project.penadidik.cleanarchitecture.R
import project.penadidik.cleanarchitecture.base.BaseFragment
import project.penadidik.cleanarchitecture.binding.FragmentDataBindingComponent
import project.penadidik.cleanarchitecture.databinding.FragmentFavoriteBinding
import project.penadidik.cleanarchitecture.util.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>() {

    override val bindingVariable: Int
        get() = project.penadidik.cleanarchitecture.BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_favorite

    override val viewModel: FavoriteViewModel by viewModels()
    private var favoriteAdapter by autoCleared<FavoriteAdapter>()
    private var bindingComponent = FragmentDataBindingComponent(this)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        subscribeUI()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = FavoriteAdapter(bindingComponent, viewModel)
        this.favoriteAdapter = adapter

        with(viewDataBinding) {
            listFavorite.adapter = favoriteAdapter
            listFavorite.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        initListener()
    }

    private fun initListener() {
        viewDataBinding.swipe.setOnRefreshListener {
            viewModel.searchFavorite()
        }
    }

    private fun subscribeUI() = with(viewModel) {
        data.observe(viewLifecycleOwner) {
            favoriteAdapter.submitList(it)
        }

        loading.observe(viewLifecycleOwner) { loading ->
            viewDataBinding.swipe.isRefreshing = loading
            viewDataBinding.loading.visibility = if (loading) View.VISIBLE else View.GONE
        }

        mUpdateRecycler.observe(viewLifecycleOwner) {
            if(it == null){
                viewDataBinding.listFavorite.adapter?.notifyDataSetChanged()
            }else{
                viewDataBinding.listFavorite.adapter?.notifyItemChanged(it)
            }
        }

    }
}