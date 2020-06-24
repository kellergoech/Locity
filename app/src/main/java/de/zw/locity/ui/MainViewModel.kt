package de.zw.locity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.parse.FindCallback
import com.parse.ParseObject
import com.parse.ParseQuery
import timber.log.Timber
import de.zw.locity.utils.Article


class MainViewModel() : ViewModel(){
    //data class
    private val TAG: String? = MainViewModel::class.simpleName

    fun addNewClasstoDatabse(){
        val myFirstClass = ParseObject("MyFirstClass")
        myFirstClass.put("name", "First Button generated entry")
        myFirstClass.saveInBackground()
    }

    fun <T> querryArticles(vararg channels: T): List<Article> {
        var result = ArrayList<Article>()
        val parseQuery = ParseQuery<ParseObject>("Post")

        parseQuery.findInBackground(FindCallback<ParseObject>{ scoreList, e ->
            if (e == null) {
                Timber.d("score Retrieved %s scores", scoreList.size)
                Log.d("Daten", scoreList.toString())
            } else {

                Timber.d("score Error: %s", e.message)
            }
        })
        return result
    }

}