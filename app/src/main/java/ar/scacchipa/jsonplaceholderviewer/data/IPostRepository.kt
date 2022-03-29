package ar.scacchipa.jsonplaceholderviewer.data

interface IPostRepository {
    suspend fun getPosts(): List<Post>
}