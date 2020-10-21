package com.ioia.book.utang

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.ioia.book.R
import com.ioia.book.databinding.HutangFragBinding
import kotlinx.android.synthetic.main.hutang_frag.*
import kotlinx.android.synthetic.main.hutang_frag.view.*
import kotlinx.android.synthetic.main.hutang_frag.view.et_search_hutang
import kotlinx.android.synthetic.main.hutang_frag.view.rv_hutang

class HutangFragment(
    private val businessId: Long
) : Fragment() {

    private val viewmodel by viewModels<HutangViewModel> {
        Injector.provideHutangViewModelFactory(requireContext(), businessId)
    }

    private lateinit var binding: HutangFragBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.hutang_frag, container, false)
        initDataBinding()
        initAnimation(binding.root)
        initListener(binding.root)
        initViewModel()
        initRecyclerView(binding.root)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewmodel.getHutangListByBusiness()
    }

    private fun initDataBinding(){
        binding.lifecycleOwner = this
        binding.viewmodel = viewmodel
    }

    private fun initViewModel(){
        viewmodel.hutangListByBusiness.observe(binding.lifecycleOwner!!, Observer {
            rv_hutang.adapter?.notifyDataSetChanged()
            initHeader(it)
        })
    }

    private fun initHeader(hutang: List<Hutang>){
        var totalHutangPelanggan: Long = 0
        var totalHutangSaya: Long = 0

        for(h in hutang){
            when(h.status){
                0 -> {
                    totalHutangPelanggan += h.hutang!!
                }

                1 -> {
                    totalHutangSaya += h.hutang!!
                }
            }
        }

        tv_hutang_pel_val.text = "Rp. $totalHutangPelanggan"
        tv_hutang_saya_val.text = "Rp. $totalHutangSaya"
    }

    private fun initRecyclerView(v: View){
        v.rv_hutang.apply {
            adapter = HutangRVAdapter(viewmodel, viewmodel.hutangListByBusiness)
            itemAnimator = DefaultItemAnimator()
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
        }
    }

    private fun initAnimation(v: View){
        ObjectAnimator.ofFloat(v.iv_arrow, View.TRANSLATION_X, 50F).apply {
            duration = 300
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            start()
        }
    }

    private fun initListener(v: View){
        v.et_search_hutang.doOnTextChanged { text, _, _, _ ->
            if(text?.length!! >= 3){
                viewmodel.resetDataHutang()
                viewmodel.getHutangListByBusinessSearch(text.toString())
            }
        }
    }


}
