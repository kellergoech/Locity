package de.zw.locity

import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.parse.FindCallback
import com.parse.ParseObject
import com.parse.ParseQuery
import de.zw.locity.datatypes.Article
import timber.log.Timber

class MainViewModel() : ViewModel(){

    //data class
    private val TAG: String? = MainViewModel::class.simpleName
    private val articlelist: MutableLiveData<Array<Article>> = MutableLiveData()

    fun getArticles() : LiveData<Array<Article>>{
        return articlelist
    }
    fun fetchData(){

        val articleList = arrayListOf<Article>()
        val parseQuery = ParseQuery<ParseObject>("Post")

        parseQuery.findInBackground(FindCallback<ParseObject> { scoreList, e ->
            if (e == null) {
                Timber.d("score Retrieved %s scores", scoreList.size)
                for (parsearticle in scoreList) {
                    var headline = parsearticle.getString("headline")
                    if (headline == null) {
                        headline = ""
                    }


                    var articletext = parsearticle.getString("content")
                    if (articletext == null) {
                        articletext = ""
                    }
                    TODO("move data handling to extra class and implement data handling in this view model")
                    /**
                    val bmp = BitmapFactory.decodeResource(.getResources(),
                        R.drawable.book);

                    val article = Article(
                        headline,
                        articletext,
                        parsearticle.objectId,
                        R.drawable.book,
                        bmp
                    )
                    articleList += article **/

                }

            } else {
                Timber.d("score Error: %s", e.message)
            }
        })
    }

    fun addNewClasstoDatabse() {
        val myFirstClass = ParseObject("MyFirstClass")
        myFirstClass.put("name", "First Button generated entry")
        myFirstClass.saveInBackground()
    }
}