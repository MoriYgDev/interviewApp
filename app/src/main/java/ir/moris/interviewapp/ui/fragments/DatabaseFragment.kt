package ir.moris.interviewapp.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import ir.moris.interviewapp.MainActivity
import ir.moris.interviewapp.R
import ir.moris.interviewapp.adapters.DbRVAdapter
import ir.moris.interviewapp.databinding.FragmentDatabaseBinding
import ir.moris.interviewapp.db.Person
import ir.moris.interviewapp.ui.DbViewModel
import java.util.ArrayList


class DatabaseFragment : Fragment(R.layout.fragment_database) {

    private lateinit var binding: FragmentDatabaseBinding
    lateinit var viewModel: DbViewModel
    lateinit var recyclerViewAdapter : DbRVAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDatabaseBinding.bind(view)

        viewModel = (activity as MainActivity).viewModel
        recyclerViewAdapter = DbRVAdapter()
        binding.rvPersonDatabase.adapter = recyclerViewAdapter
        binding.rvPersonDatabase.layoutManager = LinearLayoutManager(activity)
        viewModel.getAll().observe(viewLifecycleOwner) {
            recyclerViewAdapter.differ.submitList(it)
        }

    }

}