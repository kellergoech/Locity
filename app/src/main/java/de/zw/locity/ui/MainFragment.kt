package de.zw.locity.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import de.zw.locity.MainViewModel
import de.zw.locity.actions.ParseDataRepository
import de.zw.locity.databinding.MainFragmentBinding
import de.zw.locity.datatypes.Article
import de.zw.locity.datatypes.ArticleAdapter
import de.zw.locity.datatypes.ItemClickListener


class MainFragment : Fragment() {

    companion object {
        private val TAG: String? = MainFragment::class.simpleName
    }
    private val mainViewModel: MainViewModel by activityViewModels()
    private var _binding: MainFragmentBinding? = null
    private val binding: MainFragmentBinding get() = _binding!!

    val articleAdapter = ArticleAdapter(object :
        ItemClickListener<Article> {
        override fun onClickListener(item: Article) {
            Toast.makeText(activity, "You click ${item.headline}", Toast.LENGTH_LONG).show()
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainFragmentBinding.inflate(inflater)
        ParseDataRepository.fetchData(articleAdapter)
        binding.adapter = articleAdapter
        binding.mainViewModel = mainViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (false) {
            TODO(
                "add viemodelfactory and inject to be able to retrigger view from other activities " +
                        "and save data in viewmodel instead of mainfragment/repository" +
                        "see kotlin data binding project and Dagger 2 descirption"
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButtonOnClickListener()
    }

    private fun setButtonOnClickListener() {
        binding.testButton.setOnClickListener{
            mainViewModel.addNewClasstoDatabse()}
    }


}