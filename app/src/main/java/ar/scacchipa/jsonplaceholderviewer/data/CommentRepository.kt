package ar.scacchipa.jsonplaceholderviewer.data

class CommentRepository(
    var apiSourceData: ICommentSourceData// = provideNetworkApi(provideRetrofit())
): ICommentRepository  {
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