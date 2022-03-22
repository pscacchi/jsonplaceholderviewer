package ar.scacchipa.jsonplaceholderviewer

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MiddleCommentsUserCase(
    val jphRepository:IJPHRepository = JPHRepository()
) {
    suspend fun getMiddleCommentList(): List<Comment> =
        withContext(Dispatchers.IO) {
            val postCount = jphRepository.getPosts().size
            val comments = jphRepository.getComment(postCount / 2)
            return@withContext comments
        }
}