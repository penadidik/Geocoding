package project.penadidik.geocoding.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import project.penadidik.geocoding.BR
import project.penadidik.geocoding.R
import project.penadidik.geocoding.base.BaseFragment
import project.penadidik.geocoding.binding.FragmentDataBindingComponent
import project.penadidik.geocoding.databinding.FragmentDetailBinding
import project.penadidik.geocoding.util.Helper
import project.penadidik.geocoding.util.autoCleared

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {
    override val bindingVariable: Int
        get() = BR.viewModel
    override val viewModel: DetailViewModel by viewModels()
    override val layoutId: Int
        get() = R.layout.fragment_detail

    private var detailAdapter by autoCleared<DetailAdapter>()
    private var bindingComponent = FragmentDataBindingComponent(this)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribeUI()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = DetailAdapter(bindingComponent)
        this.detailAdapter = adapter

        with(viewDataBinding) {
            listDaily.adapter = detailAdapter
            listDaily.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        if (arguments != null) {
            viewModel.state.set(requireArguments().getString(Helper.KEY_STATE))
            viewModel.country.set(requireArguments().getString(Helper.KEY_COUNTRY))
            viewModel.lat.set(requireArguments().getDouble(Helper.KEY_LAT).toString())
            viewModel.lon.set(requireArguments().getDouble(Helper.KEY_LON).toString())

            viewModel.getDetail(requireArguments().getDouble(Helper.KEY_LAT), requireArguments().getDouble(Helper.KEY_LON))
        }

        initListener()
    }


    private fun initListener() {

        // swipe refresh
        viewDataBinding.swipe.setOnRefreshListener {
            viewModel.getDetail(viewModel.lat.get()!!.toDouble(), viewModel.lon.get()!!.toDouble())
        }

//        viewDataBinding.favorite.setOnClickListener {
//            findNavController().navigate(UserFragmentDirections.actionUserFragmentToFavoriteFragment())
//        }

    }

    private fun subscribeUI() = with(viewModel) {
        data.observe(viewLifecycleOwner) {
            detailAdapter.submitList(it)
        }

        loading.observe(viewLifecycleOwner) { loading ->
            viewDataBinding.loading.visibility = if (loading) View.VISIBLE else View.GONE
        }

        mUpdateRecycler.observe(viewLifecycleOwner) {
            if(it == null){
                viewDataBinding.listDaily.adapter?.notifyDataSetChanged()
            }else{
                viewDataBinding.listDaily.adapter?.notifyItemChanged(it)
            }
        }

        isRequesting.observe(viewLifecycleOwner) {
            viewDataBinding.swipe.isRefreshing = it
        }

    }

}