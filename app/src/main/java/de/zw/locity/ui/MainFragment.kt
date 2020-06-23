package de.zw.locity.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import de.zw.locity.MainViewModel
import de.zw.locity.databinding.MainFragmentBinding


import de.zw.locity.R


class MainFragment : Fragment() {


    companion object {
        private val TAG: String? = MainFragment::class.simpleName
    }
    private val mainViewModel: MainViewModel by activityViewModels()
    private var _binding: MainFragmentBinding? = null
    private val binding: MainFragmentBinding get() = _binding!!

    private lateinit var viewModel: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainFragmentBinding.inflate(inflater)
        binding.mainViewModel = mainViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButtonOnClickListener()
        setRecycleAdapter()
    }

    private fun setRecycleAdapter(){

    }

    private fun setButtonOnClickListener() {
        binding.testButton.setOnClickListener{
            mainViewModel.addNewClasstoDatabse()}

        binding.mainNews.setOnClickListener{
            mainViewModel.searchDatabase()}
    }
}