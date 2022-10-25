package project.penadidik.cleanarchitecture.ui.user

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import project.penadidik.cleanarchitecture.R
import project.penadidik.cleanarchitecture.base.BaseFragment
import project.penadidik.cleanarchitecture.binding.FragmentDataBindingComponent
import project.penadidik.cleanarchitecture.util.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import project.penadidik.cleanarchitecture.databinding.FragmentUserBinding

@AndroidEntryPoint
class UserFragment : BaseFragment<FragmentUserBinding, UserViewModel>() {

    override val bindingVariable: Int
        get() = project.penadidik.cleanarchitecture.BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_user

    override val viewModel: UserViewModel by viewModels()
    private var userAdapter by autoCleared<UserAdapter>()
    private var bindingComponent = FragmentDataBindingComponent(this)
    private var mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        subscribeUI()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = UserAdapter(bindingComponent, viewModel)
        this.userAdapter = adapter

        with(viewDataBinding) {
            listUser.adapter = userAdapter
            listUser.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        initListener()
    }

    private fun initListener() {

        // swipe refresh
        viewDataBinding.swipe.setOnRefreshListener {
            viewModel.searchUser(false)
        }

        // pagination, but limited when more than 3 page
        viewDataBinding.listUser.addOnScrollListener(object : RecyclerView.OnScrollListener() {

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
                        viewModel.searchUser(viewModel.isPaginating.value!!)
                    }, 300)

                }
            }
        })

        viewDataBinding.favorite.setOnClickListener {
            findNavController().navigate(UserFragmentDirections.actionUserFragmentToFavoriteFragment())
        }

        viewDataBinding.sort.setOnCheckedChangeListener { _, id ->
            viewModel.sort(id == R.id.ascending)
        }

    }

    private fun subscribeUI() = with(viewModel) {
        data.observe(viewLifecycleOwner) {
            userAdapter.submitList(it)
        }

        loading.observe(viewLifecycleOwner) { loading ->
            viewDataBinding.loading.visibility = if (loading) View.VISIBLE else View.GONE
        }

        mUpdateRecycler.observe(viewLifecycleOwner) {
            if(it == null){
                viewDataBinding.listUser.adapter?.notifyDataSetChanged()
            }else{
                viewDataBinding.listUser.adapter?.notifyItemChanged(it)
            }
        }

        isRequesting.observe(viewLifecycleOwner) {
            viewDataBinding.swipe.isRefreshing = it
        }

    }
}
