package de.zw.locity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.parse.Parse
import com.parse.ParseObject

import de.zw.locity.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Parse.initialize(Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id)) // if defined
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        )



        val myFirstClass = ParseObject("MyFirstClass")
        myFirstClass.put("name", "I'm able to save objects!")
        myFirstClass.saveInBackground()
    }
}