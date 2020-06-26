package de.zw.locity.actions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.parse.Parse
import com.parse.ParseObject
import com.parse.ParseQuery
import de.zw.locity.datatypes.Article
import de.zw.locity.datatypes.ArticleAdapter
import timber.log.Timber

object ParseDataRepository {

    fun fetchData(adapter : ArticleAdapter) {
        val articleList = arrayListOf<Article>()
        val parseQuery = ParseQuery<ParseObject>("Post")

        parseQuery.findInBackground { scoreList, e ->
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

                    adapter.submitList(articleList)
                    adapter.notifyDataSetChanged()
                }
            } else {
                Timber.d("score Error: %s", e.message)
            }
        }
    }

    fun writeArticle(){
        val myFirstClass = ParseObject("MyFirstClass")
        myFirstClass.put("name", "First Button generated entry")
        myFirstClass.saveInBackground()
    }

    fun initParse(context: Context, appID : String, clientID : String, serverName : String) {
        //initialize parse
        Parse.initialize(
            Parse.Configuration.Builder(context)
                .applicationId(appID) // if defined
                .clientKey(clientID)
                .server(serverName)
                .build()
        )
    }
}