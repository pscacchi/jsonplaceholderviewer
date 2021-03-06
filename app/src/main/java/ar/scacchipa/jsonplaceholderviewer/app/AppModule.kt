package ar.scacchipa.jsonplaceholderviewer.app

import ar.scacchipa.jsonplaceholderviewer.data.*
import ar.scacchipa.jsonplaceholderviewer.domain.MiddleCommentsUserCase
import ar.scacchipa.jsonplaceholderviewer.ui.MiddleCommentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { provideRetrofit() }
    single <IRemotePostSourceData> { providePostNetworkApi( get() ) }
    single <IRemoteCommentSourceData> { provideCommentNetworkApi( get() )}
    single <IPostRepository> { PostRepository( get() ) }
    single <ICommentRepository> { CommentRepository( get() ) }
    factory <MiddleCommentsUserCase> { MiddleCommentsUserCase( get(), get() ) }
    viewModel { MiddleCommentViewModel( get() ) }
}