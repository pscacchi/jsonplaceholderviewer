package ar.scacchipa.jsonplaceholderviewer

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JPHRepository(
     var apiSourceData:IJPHSourceData = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()
        .create(IJPHSourceData::class.java)
): IJPHRepository {
    override suspend fun getPosts(): List<Post> {
        val response = apiSourceData.getPosts()
        return if (response.isSuccessful) {
            response.body() ?: listOf()
        } else {
            listOf()
        }
    }
    override suspend fun getComment(id: Int): List<Comment> {
        apiSourceData.getComment(id.toString()).let { response ->
            return if (response.isSuccessful) {
                response.body() ?: listOf()
            } else {
                response.body() ?: listOf()
            }
        }
    }
}