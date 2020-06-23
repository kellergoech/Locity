package de.zw.locity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.parse.ParseObject
import java.util.*

class MainViewModel : ViewModel() {
    private val _name = MutableLiveData<String>().apply { value = "Test" }

    val name: LiveData<String> = _name // Expose the immutable version of the LiveData

    fun addanewclasstoDatabase(){
        val myFirstClass = ParseObject("MyFirstClass")
        myFirstClass.put("name", "First Button generated entry")
        myFirstClass.saveInBackground()
    }


}