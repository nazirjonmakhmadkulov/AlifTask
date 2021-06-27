package com.nazirjon.aliftask.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nazirjon.aliftask.R
import com.nazirjon.aliftask.adapters.GuidesAdapter
import com.nazirjon.aliftask.data.db.GuidebookDatabase
import com.nazirjon.aliftask.data.models.Data
import com.nazirjon.aliftask.repository.GuidebookRepository
import com.nazirjon.aliftask.ui.viewmodels.GuidebookViewModel
import com.nazirjon.aliftask.ui.viewmodels.GuidebookViewModelProviderFactory
import kotlinx.android.synthetic.main.fragment_guidebook.*

class GuidebookFragment : Fragment(R.layout.fragment_guidebook), GuidesAdapter.GuideActions {

    lateinit var viewModel: GuidebookViewModel
    lateinit var guidesAdapter: GuidesAdapter
    private var startIndex = 0
    private var endIndex = 3
    private var guidesList = mutableListOf<Data>()
    private var isLoaded = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = GuidebookRepository(GuidebookDatabase(requireContext()))
        val viewModelProviderFactory =
            activity?.let { GuidebookViewModelProviderFactory(it.application, repository) }

        viewModel =
            viewModelProviderFactory?.let {
                ViewModelProvider(
                    this,
                    it
                ).get(GuidebookViewModel::class.java)
            }!!

        guidesAdapter = GuidesAdapter(this)
        guides_recycler_view.apply {
            adapter = guidesAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        if (endIndex < guidesList.size) {
                            viewModel.saveGuidesToDb(guidesList.subList(startIndex, endIndex))
                            guidesAdapter.setDataToAdapter(guidesList.subList(startIndex, endIndex))
                            if ((endIndex + 3) <= guidesList.size) {
                                startIndex += 3
                                endIndex += 3
                            } else {
                                endIndex = guidesList.size
                            }
                        }
                    }
                }
            })
        }

        getUpcomingGuides()

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        }

    }

    private fun getUpcomingGuides() {
        viewModel.guidebookResponse.observe(viewLifecycleOwner) { response ->
            response.data.let {
                hideProgressBar()
                guidesList.addAll(it)
                viewModel.saveGuidesToDb(guidesList.subList(startIndex, endIndex))
                guidesAdapter.setDataToAdapter(guidesList.subList(startIndex, endIndex))
                startIndex += 3
                endIndex += 3
            }
        }
    }

    private fun hideProgressBar() {
        guides_progress_bar.isVisible = false
    }

    private fun showProgressBar() {
        guides_progress_bar.isVisible = true
    }

    override fun openGuide(guide: Data) {
        val direction = GuidebookFragmentDirections.actionHomeFragmentToDashboardFragment(guide)
        findNavController().navigate(direction)
    }
}