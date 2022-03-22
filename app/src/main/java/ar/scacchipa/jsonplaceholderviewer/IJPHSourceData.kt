package ar.scacchipa.jsonplaceholderviewer

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IJPHSourceData {
    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>

    @GET("posts/{id}/comments")
    suspend fun getComment(@Path("id") id: String): Response<List<Comment>>
}

interface IJPHRepository {
    suspend fun getPosts(): List<Post>
    suspend fun getComment(id:Int): List<Comment>
}