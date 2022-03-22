package ar.scacchipa.jsonplaceholderviewer

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JPHService(
     var apiRetrofit:IJPHRetrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()
        .create(IJPHRetrofit::class.java)
): IJPHService {
    override suspend fun getPosts(): List<Post> {
        apiRetrofit.getPosts().let { response ->
            return if (response.isSuccessful) {
                response.body() ?: listOf()
            } else {
                listOf()
            }
        }
    }
    override suspend fun getComment(id: Int): List<Comment> {
        apiRetrofit.getComment(id.toString()).let { response ->
            return if (response.isSuccessful) {
                response.body() ?: listOf()
            } else {
                response.body() ?: listOf()
            }
        }
    }
}