package viewmodels.ui

import androidx.compose.runtime.mutableStateOf

class UiStateViewModel {

 /* moveFromSplash
 *  It will allow to update screen
 * */
    var moveFromSplash = mutableStateOf(false)

 /* openErrorDialog
 *  It will display error Box when no data fetched
 * */
    var openErrorDialog = mutableStateOf(false)


}