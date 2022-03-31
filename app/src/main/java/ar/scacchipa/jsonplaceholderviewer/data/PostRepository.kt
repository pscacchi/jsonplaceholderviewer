package ar.scacchipa.jsonplaceholderviewer.data

class PostRepository(
    var apiPostSourceData: IRemotePostSourceData// = provideNetworkApi(provideRetrofit())
): IPostRepository {
    override suspend fun getPosts(): List<Post> {
        val response = apiPostSourceData.getPosts()
        return if (response.isSuccessful) {
            response.body() ?: listOf()
        } else {
            listOf()
        }
    }
}