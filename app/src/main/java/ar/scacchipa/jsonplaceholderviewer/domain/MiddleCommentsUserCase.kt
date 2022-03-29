package ar.scacchipa.jsonplaceholderviewer.domain

import ar.scacchipa.jsonplaceholderviewer.data.Comment
import ar.scacchipa.jsonplaceholderviewer.data.ICommentRepository
import ar.scacchipa.jsonplaceholderviewer.data.IPostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MiddleCommentsUserCase(
    private val postRepository: IPostRepository,
    private val commentRepository: ICommentRepository, // = CommentRepository()
) {
    suspend fun getMiddleCommentList(): List<Comment> =
        withContext(Dispatchers.IO) {
            val postCount = postRepository.getPosts().size
            val postId: Int = if (postCount % 2 == 0) {
                postCount / 2
            } else {
                postCount / 2 + 1
            }
            val comments = commentRepository.getComment(postId)
            return@withContext comments
        }
}