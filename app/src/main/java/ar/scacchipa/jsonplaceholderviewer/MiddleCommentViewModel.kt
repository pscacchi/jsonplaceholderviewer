package ar.scacchipa.jsonplaceholderviewer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MiddleCommentViewModel(
    private val userCase: MiddleCommentsUserCase // = MiddleCommentsUserCase()
): ViewModel() {

    var middleCommentList = MutableLiveData<List<Comment>?>()

    fun update() {
        viewModelScope.launch(Dispatchers.Main) {
            middleCommentList.value = userCase.getMiddleCommentList()
        }
    }
}