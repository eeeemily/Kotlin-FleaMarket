package com.mzheng9.kotlin_fleamarket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mzheng9.kotlin_fleamarket.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true) // what is this
        val fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        binding = fragmentHomeBinding
        binding?.apply {

            loginBtn.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
            }

        }
        return fragmentHomeBinding.root
    }


}