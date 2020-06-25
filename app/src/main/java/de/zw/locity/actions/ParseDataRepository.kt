package de.zw.locity.actions

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.parse.FindCallback
import com.parse.ParseObject
import com.parse.ParseQuery
import de.zw.locity.datatypes.Article
import timber.log.Timber

object ParseDataRepository {

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

                    val conf = Bitmap.Config.ARGB_8888 // see other conf types
                    var bmp = Bitmap.createBitmap(100, 100, conf)

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

                    TODO("IMplement callback from funtion to udpdate Mutable list in listview")
                }
            } else {
                Timber.d("score Error: %s", e.message)
            }
        })
    }

    fun writeArticle(){
        val myFirstClass = ParseObject("MyFirstClass")
        myFirstClass.put("name", "First Button generated entry")
        myFirstClass.saveInBackground()
    }
}