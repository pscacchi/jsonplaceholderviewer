package ar.scacchipa.jsonplaceholderviewer

import ar.scacchipa.jsonplaceholderviewer.data.*
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import retrofit2.Response
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MockPostSourceData: IRemotePostSourceData {
    override suspend fun getPosts(): Response<List<Post>> {
        return Response.success(getMockPosts())
    }
}
class MockCommentSourceData: IRemoteCommentSourceData {
    override suspend fun getComment(id: String): Response<List<Comment>> {
        return if (id.toInt() == 5) {
            Response.success( getMockComments() )
        } else {
            Response.success( listOf() )
        }
    }
}

class PostRespositoryUnitTest: KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(
            module {
                single { MockPostSourceData() as IRemotePostSourceData }
                single { PostRepository(get()) as IPostRepository }
            })
    }

    private val postRepository: IPostRepository by inject()

    @Test
    fun shouldGetNinePost() = runBlocking {
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

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(
            module {
                factory { MockCommentSourceData() as IRemoteCommentSourceData }
                factory { CommentRepository( get() ) as ICommentRepository }
            }
        )
    }

    val commentRepository: ICommentRepository by inject()

    @Test
    fun shoulGetFiveCommentList() = runBlocking {
        val comments = commentRepository.getComment(5)

        assertEquals(comments.size, 5)
        assertTrue { comments.all { post -> post.postId == 5 } }
    }

    @After
    fun after() {
        stopKoin()
    }
}