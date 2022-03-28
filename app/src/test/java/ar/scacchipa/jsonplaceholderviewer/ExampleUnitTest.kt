package ar.scacchipa.jsonplaceholderviewer

import ar.scacchipa.jsonplaceholderviewer.data.Comment
import ar.scacchipa.jsonplaceholderviewer.data.ICommentRepository
import ar.scacchipa.jsonplaceholderviewer.data.Post
import ar.scacchipa.jsonplaceholderviewer.domain.MiddleCommentsUserCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.assertTrue

class MockRepository: ICommentRepository {
    override suspend fun getPosts(): List<Post> {
        return listOf(
            Post(
                1, 1,
                "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
            ),
            Post(
                1, 2, "qui est esse",
                "est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\nqui aperiam non debitis possimus qui neque nisi nulla"
            ),
            Post(
                2, 3, "ea molestias quasi exercitationem repellat qui ipsa sit aut",
                "et iusto sed quo iure\nvoluptatem occaecati omnis eligendi aut ad\nvoluptatem doloribus vel accusantium quis pariatur\nmolestiae porro eius odio et labore et velit aut"
            ),
            Post(
                2, 4, "eum et est occaecati",
                "ullam et saepe reiciendis voluptatem adipisci\nsit amet autem assumenda provident rerum culpa\nquis hic commodi nesciunt rem tenetur doloremque ipsam iure\nquis sunt voluptatem rerum illo velit"
            ),
            Post(
                2, 5, "nesciunt quas odio",
                "repudiandae veniam quaerat sunt sed\nalias aut fugiat sit autem sed est\nvoluptatem omnis possimus esse voluptatibus quis\nest aut tenetur dolor neque"
            ),
            Post(
                3, 6, "dolorem eum magni eos aperiam quia",
                "ut aspernatur corporis harum nihil quis provident sequi\nmollitia nobis aliquid molestiae\nperspiciatis et ea nemo ab reprehenderit accusantium quas\nvoluptate dolores velit et doloremque molestiae"
            ),
            Post(
                4, 7, "magnam facilis autem",
                "dolore placeat quibusdam ea quo vitae\nmagni quis enim qui quis quo nemo aut saepe\nquidem repellat excepturi ut quia\nsunt ut sequi eos ea sed quas"
            ),
            Post(
                4, 8, "dolorem dolore est ipsam",
                "dignissimos aperiam dolorem qui eum\nfacilis quibusdam animi sint suscipit qui sint possimus cum\nquaerat magni maiores excepturi\nipsam ut commodi dolor voluptatum modi aut vitae"
            ),
            Post(
                5, 9, "nesciunt iure omnis dolorem tempora et accusantium",
                "consectetur animi nesciunt iure dolore\nenim quia ad\nveniam autem ut quam aut nobis\net est aut quod aut provident voluptas autem voluptas"
            )
        )
    }

    override suspend fun getComment(id: Int): List<Comment> {
        return if (id == 4) listOf(
            Comment(
                5, 206,
                "et fugit eligendi deleniti quidem qui sint nihil autem",
                "Presley.Mueller@myrl.com",
                "doloribus at sed quis culpa deserunt consectetur qui praesentium\naccusamus fugiat dicta\nvoluptatem rerum ut voluptate autem\nvoluptatem repellendus aspernatur dolorem in"
            ),
            Comment(
                5, 207,
                "repellat consequatur praesentium vel minus molestias voluptatum",
                "Dallas@ole.me",
                "maiores sed dolores similique labore et inventore et\nquasi temporibus esse sunt id et\neos voluptatem aliquam\naliquid ratione corporis molestiae mollitia quia et magnam dolor"
            ),
            Comment(
                5, 208,
                "et omnis dolorem",
                "Mallory_Kunze@marie.org",
                "ut voluptatem corrupti velit\nad voluptatem maiores\net nisi velit vero accusamus maiores\nvoluptates quia aliquid ullam eaque"
            ),
            Comment(
                5, 209,
                "provident id voluptas",
                "Meghan_Littel@rene.us",
                "sapiente assumenda molestiae atque\nadipisci laborum distinctio aperiam et ab ut omnis\net occaecati aspernatur odit sit rem expedita\nquas enim ipsam minus"
            ),
            Comment(
                5, 210,
                "eaque et deleniti atque tenetur ut quo ut",
                "Carmen_Keeling@caroline.name",
                "voluptate iusto quis nobis reprehenderit ipsum amet nulla\nquia quas dolores velit et non\naut quia necessitatibus\nnostrum quaerat nulla et accusamus nisi facilis"
            )
        ) else {
            return listOf()
        }
    }
}

class ExampleUnitTest: KoinTest {

    private val middleCommentsUserCase: MiddleCommentsUserCase by inject()

    @Test
    fun middleComment() = runBlocking {

        startKoin {
            modules(
                module {
                    single { MockRepository() as ICommentRepository }
                    single { MiddleCommentsUserCase( get() ) }
                })
        }

        //val middleCommentsUserCase = get<MiddleCommentsUserCase>()
        val comments = middleCommentsUserCase.getMiddleCommentList()

        assertTrue (  comments != null )
    }
}