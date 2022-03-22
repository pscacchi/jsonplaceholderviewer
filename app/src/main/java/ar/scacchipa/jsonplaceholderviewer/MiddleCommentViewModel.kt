package ar.scacchipa.jsonplaceholderviewer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MiddleCommentViewModel: ViewModel() {

    var userCase: MiddleCommentsUserCase? = null
    var middleCommentList = MutableLiveData<List<Comment>?>()


    fun update() {
        userCase?.let {
            viewModelScope.launch(Dispatchers.Main) {
                middleCommentList.value = it.getMiddleCommentList()
            }
        }
    }
}