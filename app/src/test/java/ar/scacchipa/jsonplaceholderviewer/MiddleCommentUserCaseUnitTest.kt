package ar.scacchipa.jsonplaceholderviewer

import ar.scacchipa.jsonplaceholderviewer.data.Comment
import ar.scacchipa.jsonplaceholderviewer.data.ICommentRepository
import ar.scacchipa.jsonplaceholderviewer.data.IPostRepository
import ar.scacchipa.jsonplaceholderviewer.data.Post
import ar.scacchipa.jsonplaceholderviewer.domain.MiddleCommentsUserCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MockPostRepository: IPostRepository {
    override suspend fun getPosts(): List<Post> {
        return getMockPosts()
    }
}

class MockCommentRepository: ICommentRepository {
    override suspend fun getComment(id: Int): List<Comment> {
        return getMockComments()
    }
}

class MiddleCommentUserCaseUnitTest: KoinTest {

    private val middleCommentsUserCase: MiddleCommentsUserCase by inject()

    @Test
    fun shouldGetMiddleComment() = runBlocking {
        startKoin {
            modules(
                module {
                    factory { MockPostRepository() as IPostRepository }
                    factory { MockCommentRepository() as ICommentRepository}
                    factory { MiddleCommentsUserCase( get(), get() ) }
                })
        }

        val comments = middleCommentsUserCase.getMiddleCommentList()

        assertEquals( comments.size, 5 )
        assertTrue { comments.all { comment -> comment.postId == 5 } }
    }

    @After
    fun after() {
        stopKoin()
    }
}