package ar.scacchipa.jsonplaceholderviewer.data

import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ICommentSourceData {
    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>

    @GET("posts/{id}/comments")
    suspend fun getComment(@Path("id") id: String): Response<List<Comment>>
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()
}

fun provideNetworkApi(retrofit: Retrofit): ICommentSourceData =
    retrofit.create(ICommentSourceData::class.java)

