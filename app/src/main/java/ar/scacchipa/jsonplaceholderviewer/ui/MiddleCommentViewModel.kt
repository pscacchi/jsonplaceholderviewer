package ar.scacchipa.jsonplaceholderviewer.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.scacchipa.jsonplaceholderviewer.data.Comment
import ar.scacchipa.jsonplaceholderviewer.domain.MiddleCommentsUserCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MiddleCommentViewModel(
    private val userCase: MiddleCommentsUserCase, // = MiddleCommentsUserCase()
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
): ViewModel() {

    var middleCommentList = MutableLiveData<List<Comment>?>()

    fun updateMiddleComments() {
        viewModelScope.launch(dispatcher) {
            middleCommentList.value = userCase.getMiddleCommentList()
        }
    }
}