package ar.scacchipa.jsonplaceholderviewer

import ar.scacchipa.jsonplaceholderviewer.data.*
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import retrofit2.Response
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class MockSourceData: ITypicodeSourceData {
    override suspend fun getPosts(): Response<List<Post>> {
        return Response.success( getMockPosts() )
    }

    override suspend fun getComment(id: String): Response<List<Comment>> {
        return if (id.toInt() == 5) {
            Response.success( getMockComments() )
        } else {
            Response.success( listOf() )
        }
    }
}

class PostRespositoryUnitTest: KoinTest {

    private val postRepository: IPostRepository by inject()

    @Test
    fun shouldGetNinePost() = runBlocking {
        startKoin {
            modules(
                module {
                    single { MockSourceData() as ITypicodeSourceData }
                    single { PostRepository(get()) as IPostRepository }
                })
        }

        val posts = postRepository.getPosts()

        assertEquals(posts.size, 9)
        assertTrue {
            (1..9).all { num -> posts.any { post -> post.id == num } }
        }
    }

    @After
    fun after() {
        stopKoin()
    }
}

class CommentRepositoryUnit: KoinTest {

    val commentRepository: ICommentRepository by inject()

    @Test
    fun shoulGetFiveCommentList() = runBlocking {
        startKoin {
            modules(
                module {
                    factory { MockSourceData() as ITypicodeSourceData }
                    factory { CommentRepository( get() ) as ICommentRepository }
                }
            )
        }

        val comments = commentRepository.getComment(5)

        assertEquals(comments.size, 5)
        assertTrue { comments.all { post -> post.postId == 5 } }
    }

    @After
    fun after() {
        stopKoin()
    }
}