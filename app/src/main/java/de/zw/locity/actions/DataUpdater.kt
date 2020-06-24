package de.zw.locity.actions

import android.graphics.BitmapFactory
import com.parse.FindCallback
import com.parse.ParseObject
import com.parse.ParseQuery
import de.zw.locity.R
import de.zw.locity.datatypes.Article
import timber.log.Timber

class DataUpdater(val listener: onCompleteListener<Boolean>) {

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

                    TODO("implement data handling in seperate class")
/**
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
                    articleList += article **/
                }
            } else {
                Timber.d("score Error: %s", e.message)
            }
        })
    }
}