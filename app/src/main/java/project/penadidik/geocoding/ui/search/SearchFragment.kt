package project.penadidik.geocoding.ui.search

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.Navigator
import androidx.navigation.NavigatorProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import project.penadidik.geocoding.BR
import project.penadidik.geocoding.R
import project.penadidik.geocoding.base.BaseFragment
import project.penadidik.geocoding.binding.FragmentDataBindingComponent
import project.penadidik.geocoding.databinding.FragmentSearchBinding
import project.penadidik.geocoding.util.Helper
import project.penadidik.geocoding.util.autoCleared

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>(), SearchListener {
    override val bindingVariable: Int
        get() = BR.viewModel
    override val viewModel: SearchViewModel by viewModels()
    override val layoutId: Int
        get() = R.layout.fragment_search

    private var searchAdapter by autoCleared<SearchAdapter>()
    private var bindingComponent = FragmentDataBindingComponent(this)
    private var mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribeUI()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = SearchAdapter(bindingComponent, viewModel, this)
        this.searchAdapter = adapter

        with(viewDataBinding) {
            listSearch.adapter = searchAdapter
            listSearch.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        initListener()
    }

    private fun initListener() {

        // swipe refresh
        viewDataBinding.swipe.setOnRefreshListener {
            viewModel.searching(false)
        }

        // pagination, but limited when more than 3 page
        viewDataBinding.listSearch.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = mLayoutManager.itemCount
                val firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition()

                if (firstVisibleItemPosition >= (5*viewModel.page.value!!)
                    && totalItemCount >= 30
                    && viewModel.isRequesting.value == false
                    && viewModel.isPaginating.value == false
                    && viewModel.isLastPage.value == false) {
                    viewModel.isPaginating.value = true
                    Handler().postDelayed({
                        viewModel.searching(viewModel.isPaginating.value!!)
                    }, 300)

                }
            }
        })

        viewDataBinding.favorite.setOnClickListener {
//            findNavController().navigate(UserFragmentDirections.actionUserFragmentToFavoriteFragment())
        }

    }

    private fun subscribeUI() = with(viewModel) {
        data.observe(viewLifecycleOwner) {
            searchAdapter.submitList(it)
        }

        loading.observe(viewLifecycleOwner) { loading ->
            viewDataBinding.loading.visibility = if (loading) View.VISIBLE else View.GONE
        }

        mUpdateRecycler.observe(viewLifecycleOwner) {
            if(it == null){
                viewDataBinding.listSearch.adapter?.notifyDataSetChanged()
            }else{
                viewDataBinding.listSearch.adapter?.notifyItemChanged(it)
            }
        }

        isRequesting.observe(viewLifecycleOwner) {
            viewDataBinding.swipe.isRefreshing = it
        }

        openDetail.observe(viewLifecycleOwner) {

        }

    }

    override fun onClickDetail(model: SearchModel) {
        val bundle = Bundle()
        bundle.putDouble(Helper.KEY_LAT, model.lat)
        bundle.putDouble(Helper.KEY_LON, model.lon)
        bundle.putString(Helper.KEY_STATE, model.state)
        bundle.putString(Helper.KEY_COUNTRY, model.country)

        findNavController().navigate(R.id.detailFragment, bundle)
    }
}