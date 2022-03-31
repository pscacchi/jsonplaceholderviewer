package ar.scacchipa.jsonplaceholderviewer.data

import retrofit2.Response
import retrofit2.http.GET

interface IRemotePostSourceData {
    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>
}



