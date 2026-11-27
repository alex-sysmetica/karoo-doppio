package com.alex.karoo.doppio.app

import android.app.Application
import timber.log.Timber

/** The process: it owns the one [AppGraph] that the extension service and the app share. */
class DoppioApp : Application() {
    /** Built on first use. */
    val graph: AppGraph by lazy { graphFactory(this) }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    companion object {
        /** The only way to replace the graph. Only the debug tools set it. */
        var graphFactory: (Application) -> AppGraph = ::RealAppGraph
    }
}
