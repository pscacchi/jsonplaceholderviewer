package ar.scacchipa.jsonplaceholderviewer

import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ar.scacchipa.jsonplaceholderviewer.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

val appModule = module {

    factory { provideRetrofit() }
    single <IJPHSourceData> { provideNetworkApi(get()) }
    single <IJPHRepository> { JPHRepository( get() ) }
    single <MiddleCommentsUserCase> { MiddleCommentsUserCase( get() ) }
    viewModel { MiddleCommentViewModel( get() ) }
}

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            //androidContext(this@MyApplication)
            modules(appModule)
        }
    }
}
class MainActivity : AppCompatActivity() {

    private val commentVM: MiddleCommentViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.commentRecyclerView.layoutManager = LinearLayoutManager(this)

        setContentView(binding.root)

        binding.middleCommentButton.setOnClickListener {
            commentVM.update()
        }
        commentVM.middleCommentList.observe(this) { comments ->
            comments?.let {
                binding.commentRecyclerView.adapter = CommentAdapter(it)
            }
        }

    }
}