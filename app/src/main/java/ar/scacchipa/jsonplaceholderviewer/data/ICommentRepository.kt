package ar.scacchipa.jsonplaceholderviewer.data

interface ICommentRepository {
    suspend fun getPosts(): List<Post>
    suspend fun getComment(id:Int): List<Comment>
}