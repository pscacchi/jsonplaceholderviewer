package ar.scacchipa.jsonplaceholderviewer.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IRemoteCommentSourceData {
    @GET("posts/{id}/comments")
    suspend fun getComment(@Path("id") id: String): Response<List<Comment>>
}
