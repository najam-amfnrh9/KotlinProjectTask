package theme

import android.util.Log

actual fun log(message: String) {
    Log.d("PROJECT_LOG", message)
}

actual fun log(tag: String, message: String) {
}