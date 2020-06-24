package de.zw.locity.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.parse.FindCallback
import com.parse.ParseObject
import com.parse.ParseQuery
import de.zw.locity.MainViewModel
import de.zw.locity.R
import de.zw.locity.databinding.MainFragmentBinding
import de.zw.locity.datatypes.Article
import de.zw.locity.datatypes.ArticleAdapter
import de.zw.locity.datatypes.ItemClickListener
import timber.log.Timber


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
       // articleAdapter.submitList(mainViewModel.fetchData())
        fetchData()
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
    
    fun loadData(){
        TODO("move data handling from fragment to repository and add main view Holder")
    }

    fun fetchData() {

        val articleList = arrayListOf<Article>()
        val parseQuery = ParseQuery<ParseObject>("Post")

        parseQuery.findInBackground(FindCallback<ParseObject> { scoreList, e ->
            if (e == null) {
                Timber.d("score Retrieved %s scores", scoreList.size)
                for (parsearticle in scoreList){
                    var headline = parsearticle.getString("headline")
                    if (headline == null){
                        headline = ""
                    }

                    var articletext = parsearticle.getString("content")
                    if (articletext == null){
                        articletext = ""
                    }

                    var bmp = BitmapFactory.decodeResource(activity?.getResources(),
                    R.drawable.book);

                    if (parsearticle.get("picture") != null){
                        val parseFile = parsearticle.getParseFile("picture")

                        if (parseFile!=null){
                            bmp = BitmapFactory.decodeByteArray(parseFile.data, 0, parseFile.data.size)
                        }
                    }

                    val article = Article(
                        headline,
                        articletext,
                        parsearticle.objectId,
                        bmp
                    )
                    articleList += article
                }
                articleAdapter.submitList(articleList)
                articleAdapter.notifyDataSetChanged()


            } else {
                Timber.d("score Error: %s", e.message)
            }
        })
    }

    // Method to convert a bitmap to bitmap drawable
    private fun bitmapToDrawable(bitmap: Bitmap):BitmapDrawable{
        return BitmapDrawable(resources,bitmap)
    }
}