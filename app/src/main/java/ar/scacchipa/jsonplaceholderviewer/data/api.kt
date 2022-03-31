package ar.scacchipa.jsonplaceholderviewer.data

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()
}

fun providePostNetworkApi(retrofit: Retrofit): IRemotePostSourceData =
    retrofit.create(IRemotePostSourceData::class.java)

fun provideCommentNetworkApi(retrofit: Retrofit): IRemoteCommentSourceData =
    retrofit.create(IRemoteCommentSourceData::class.java)
