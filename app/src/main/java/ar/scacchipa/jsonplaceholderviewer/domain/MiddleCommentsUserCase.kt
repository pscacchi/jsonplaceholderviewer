package ar.scacchipa.jsonplaceholderviewer.domain

import ar.scacchipa.jsonplaceholderviewer.data.Comment
import ar.scacchipa.jsonplaceholderviewer.data.ICommentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MiddleCommentsUserCase(
    private val jphRepository: ICommentRepository// = JPHRepository()
) {
    suspend fun getMiddleCommentList(): List<Comment> =
        withContext(Dispatchers.IO) {
            val postCount = jphRepository.getPosts().size
            val comments = jphRepository.getComment(postCount / 2)
            return@withContext comments
        }
}