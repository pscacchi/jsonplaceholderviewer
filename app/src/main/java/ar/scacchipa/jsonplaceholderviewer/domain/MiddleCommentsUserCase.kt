package ar.scacchipa.jsonplaceholderviewer.domain

import ar.scacchipa.jsonplaceholderviewer.data.Comment
import ar.scacchipa.jsonplaceholderviewer.data.ICommentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MiddleCommentsUserCase(
    private val commentRepository: ICommentRepository// = CommentRepository()
) {
    suspend fun getMiddleCommentList(): List<Comment> =
        withContext(Dispatchers.IO) {
            val postCount = commentRepository.getPosts().size
            val comments = commentRepository.getComment(postCount / 2)
            return@withContext comments
        }
    fun nullFunction() = null

}