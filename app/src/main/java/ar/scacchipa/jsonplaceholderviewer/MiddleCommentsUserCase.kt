package ar.scacchipa.jsonplaceholderviewer

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MiddleCommentsUserCase(
    val jphService:IJPHService = JPHService()
) {
    suspend fun getMiddleCommentList(): List<Comment> =
        withContext(Dispatchers.IO) {
            val postCount = jphService.getPosts().size
            return@withContext jphService.getComment(postCount / 2)
        }
}