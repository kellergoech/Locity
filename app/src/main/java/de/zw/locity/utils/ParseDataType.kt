package de.zw.locity.utils

import com.parse.ParseObject

object ParseDataType {
    data class Article(var headline : String,var articletext : String, var Id : String){
    }

    fun addNewClasstoDatabse(){
        val myFirstClass = ParseObject("MyFirstClass")
        myFirstClass.put("name", "First Button generated entry")
        myFirstClass.saveInBackground()
    }

    fun getArticles(){

    }

}