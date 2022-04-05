package ar.scacchipa.jsonplaceholderviewer.ui

import androidx.lifecycle.*
import ar.scacchipa.jsonplaceholderviewer.data.Comment
import ar.scacchipa.jsonplaceholderviewer.domain.MiddleCommentsUserCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MiddleCommentViewModel(
    private val userCase: MiddleCommentsUserCase, // = MiddleCommentsUserCase()
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
): ViewModel() {

    private var middleCommentList = MutableLiveData<List<Comment>?>()
    fun setMiddleCommentList(comments: List<Comment>) {
        this.middleCommentList.value = comments
    }

    fun setCommentObserve(owner: LifecycleOwner, observer: Observer<in List<Comment>?>) {
        middleCommentList.observe(owner, observer)
    }
    fun updateMiddleComments() {
        viewModelScope.launch(dispatcher) {
            middleCommentList.value = userCase.getMiddleCommentList()
        }
    }
}

