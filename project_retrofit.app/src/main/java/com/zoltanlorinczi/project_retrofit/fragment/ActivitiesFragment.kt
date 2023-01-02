package com.zoltanlorinczi.project_retrofit.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zoltanlorinczi.project_retorfit.R
import com.zoltanlorinczi.project_retrofit.adapter.ActivitiesAdapter
import com.zoltanlorinczi.project_retrofit.api.ThreeTrackerRepository
import com.zoltanlorinczi.project_retrofit.api.model.ActivityResponse
import com.zoltanlorinczi.project_retrofit.viewmodel.ActivitiesViewModel
import com.zoltanlorinczi.project_retrofit.viewmodel.ActivitiesViewModelFactory
import com.zoltanlorinczi.project_retrofit.viewmodel.UsersViewModel
import com.zoltanlorinczi.project_retrofit.viewmodel.UsersViewModelFactory

class ActivitiesFragment : Fragment(R.layout.fragment_tasks_list), ActivitiesAdapter.OnItemClickListener,
    ActivitiesAdapter.OnItemLongClickListener {

    companion object {
        private val TAG: String = javaClass.simpleName
    }

    private lateinit var activitiesViewModel: ActivitiesViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ActivitiesAdapter
    private lateinit var usersViewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ActivitiesViewModelFactory(ThreeTrackerRepository())
        activitiesViewModel = ViewModelProvider(this, factory)[ActivitiesViewModel::class.java]
        usersViewModel =ViewModelProvider(requireActivity(), UsersViewModelFactory(ThreeTrackerRepository()))[UsersViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_activities, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        setupRecyclerView()
        activitiesViewModel.products.observe(viewLifecycleOwner) {
            Log.d(TAG, "Activities list = $it")
            adapter.setData(activitiesViewModel.products.value as ArrayList<ActivityResponse>)
            adapter.notifyDataSetChanged()

        }


        return view
    }

    private fun setupRecyclerView() {
        adapter = ActivitiesAdapter(ArrayList(), this.requireContext(), this, this, usersViewModel)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerView.setHasFixedSize(true)
    }

    override fun onItemClick(position: Int) {
//        TODO("Not yet implemented")
    }

    override fun onItemLongClick(position: Int) {
//        TODO("Not yet implemented")
    }
}