package ar.scacchipa.jsonplaceholderviewer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MiddleCommentViewModel: ViewModel() {
    var middleCommentList = MutableLiveData<List<Comment>?>()
}