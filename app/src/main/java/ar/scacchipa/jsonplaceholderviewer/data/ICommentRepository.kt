package ar.scacchipa.jsonplaceholderviewer.data

interface ICommentRepository {
    suspend fun getComment(id:Int): List<Comment>
}