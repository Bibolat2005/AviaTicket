package com.example.aviatickets.fragment

import OfferListAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aviatickets.R
import com.example.aviatickets.databinding.FragmentOfferListBinding
import com.example.aviatickets.model.network.ApiClient
import com.example.aviatickets.model.service.FakeService


class OfferListFragment : Fragment() {

    companion object {
        fun newInstance() = OfferListFragment()
    }

    private var _binding: FragmentOfferListBinding? = null
    private val binding
        get() = _binding!!

    private val adapter: OfferListAdapter by lazy {
        OfferListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOfferListBinding.inflate(layoutInflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        val client = ApiClient.instance
        val response = client.fetchPersonList()
        //adapter.setItems(response)
    }

    private fun setupUI() {
        with(binding) {
            offerList.adapter = adapter

            sortRadioGroup.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.sort_by_price -> {
                        sortOfferListByPrice()
                    }

                    R.id.sort_by_duration -> {
                        sortOfferListByDuration()
                    }
                }
            }
        }
    }
    private fun sortOfferListByPrice() {
        val sortedList = FakeService.offerList.sortedBy { it.price }
        adapter.setItems(sortedList)
    }

    private fun sortOfferListByDuration() {
        val sortedList = FakeService.offerList.sortedBy { it.flight.duration }
        adapter.setItems(sortedList)
    }

}