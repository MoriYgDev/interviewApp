package ir.moris.interviewapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ir.moris.interviewapp.R
import ir.moris.interviewapp.databinding.FragmentApiBinding
import ir.moris.interviewapp.databinding.FragmentDatabaseBinding


class ApiFragment : Fragment(R.layout.fragment_api) {

    private lateinit var binding: FragmentApiBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentApiBinding.bind(view)
    }
}