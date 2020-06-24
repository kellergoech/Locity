package de.zw.locity.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import de.zw.locity.MainViewModel
import de.zw.locity.databinding.MainFragmentBinding

import de.zw.locity.R
import de.zw.locity.utils.Article
import de.zw.locity.utils.ArticleAdapter
import de.zw.locity.utils.ItemClickListener
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment : Fragment() {

    companion object {
        private val TAG: String? = MainFragment::class.simpleName
    }
    private val mainViewModel: MainViewModel by activityViewModels()
    private var _binding: MainFragmentBinding? = null
    private val binding: MainFragmentBinding get() = _binding!!

    val articleAdapter = ArticleAdapter(object : ItemClickListener<Article> {
        override fun onClickListener(item: Article) {
            Toast.makeText(activity, "You click ${item.headline}", Toast.LENGTH_LONG).show()
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainFragmentBinding.inflate(inflater)
        articleAdapter.submitList(fetchData())
        binding.adapter = articleAdapter
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

    }

    private fun setButtonOnClickListener() {
        binding.testButton.setOnClickListener{
            mainViewModel.addNewClasstoDatabse()}
    }

    fun fetchData(): ArrayList<Article> {
        val articleList = arrayListOf<Article>()
        //here implement parse datasearching
        for (a in 1..5) {
            val article = Article("testing","here is written something","XXdfd",R.drawable.book)
            articleList += article
        }

        return articleList
    }
}