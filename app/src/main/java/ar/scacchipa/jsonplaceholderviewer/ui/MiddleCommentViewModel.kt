package ar.scacchipa.jsonplaceholderviewer.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.scacchipa.jsonplaceholderviewer.data.Comment
import ar.scacchipa.jsonplaceholderviewer.domain.MiddleCommentsUserCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MiddleCommentViewModel(
    private val userCase: MiddleCommentsUserCase // = MiddleCommentsUserCase()
): ViewModel() {

    var middleCommentList = MutableLiveData<List<Comment>?>()

    fun updateMiddleComments() {
        viewModelScope.launch(Dispatchers.Main) {
            middleCommentList.value = userCase.getMiddleCommentList()
        }
    }
}