package de.zw.locity

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.WindowManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import timber.log.Timber
import com.parse.Parse
import de.zw.locity.R

class ZwoenitzApplication : Application(), LifecycleObserver,
    Application.ActivityLifecycleCallbacks {

    companion object {
        val TAG: String? = ZwoenitzApplication::class.simpleName
        private lateinit var instance: ZwoenitzApplication

        /* describes if the app is in foreground
         * Initialized to false, because app could also be started by a background job.
         * For the cases where the app is started via the launcher icon, the onAppForegrounded
         * event will be called, setting it to true
         */
        var isAppInForeground = false

        fun getAppContext(): Context =
            instance.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        registerActivityLifecycleCallbacks(this)

        //initialize parse -> maybe external class later on?
        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id)) // if defined
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        )

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    /**
     * Callback when the app is open but backgrounded
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onAppBackgrounded() {
        isAppInForeground = false
        Timber.v("App backgrounded")
    }

    /**
     * Callback when the app is foregrounded
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onAppForegrounded() {
        isAppInForeground = true
        Timber.v("App foregrounded")
    }

    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityStarted(activity: Activity) {
        // does not override function. Empty on intention
    }

    override fun onActivityDestroyed(activity: Activity) {
        // does not override function. Empty on intention
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        // does not override function. Empty on intention
    }

    override fun onActivityStopped(activity: Activity) {
        // does not override function. Empty on intention
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        // prevents screenshot of the app for all activities,
        // except for deviceForTesters build flavor, which is used for testing
        if (BuildConfig.FLAVOR != "deviceForTesters") {
            activity.window.setFlags(
                WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE
            )
        }

        // set screen orientation to portrait
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT
    }

    override fun onActivityResumed(activity: Activity) {

    }
}
