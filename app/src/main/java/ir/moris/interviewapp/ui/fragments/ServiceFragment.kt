package ir.moris.interviewapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ir.moris.interviewapp.R
import ir.moris.interviewapp.databinding.FragmentDatabaseBinding
import ir.moris.interviewapp.databinding.FragmentServiceBinding
import ir.moris.interviewapp.services.ActiveAppService


class ServiceFragment : Fragment(R.layout.fragment_service) {

    private lateinit var binding: FragmentServiceBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentServiceBinding.bind(view)
        binding.btnStartService.setOnClickListener {
            val intent = Intent(context , ActiveAppService::class.java)
            activity?.startService(intent)
        }

        binding.btnStopService.setOnClickListener {
            val intent = Intent(context , ActiveAppService::class.java)
            activity?.stopService(intent)
        }

    }
}