package ar.scacchipa.jsonplaceholderviewer

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()
}

fun provideNetworkApi(retrofit: Retrofit): IJPHSourceData =
    retrofit.create(IJPHSourceData::class.java)

class JPHRepository(
     var apiSourceData:IJPHSourceData// = provideNetworkApi(provideRetrofit())
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