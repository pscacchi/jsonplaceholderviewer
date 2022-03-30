package ar.scacchipa.jsonplaceholderviewer.data

class PostRepository(
    var apiSourceData: ITypicodeSourceData// = provideNetworkApi(provideRetrofit())
): IPostRepository {
    override suspend fun getPosts(): List<Post> {
        val response = apiSourceData.getPosts()
        return if (response.isSuccessful) {
            response.body() ?: listOf()
        } else {
            listOf()
        }
    }
}