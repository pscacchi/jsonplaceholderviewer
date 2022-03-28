package ar.scacchipa.jsonplaceholderviewer.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ar.scacchipa.jsonplaceholderviewer.databinding.ActivityMainBinding
import ar.scacchipa.jsonplaceholderviewer.ui.CommentAdapter
import ar.scacchipa.jsonplaceholderviewer.ui.MiddleCommentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val commentVM: MiddleCommentViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.commentRecyclerView.layoutManager = LinearLayoutManager(this)

        setContentView(binding.root)

        binding.middleCommentButton.setOnClickListener {
            commentVM.updateMiddleComments()
        }
        commentVM.middleCommentList.observe(this) { comments ->
            comments?.let {
                binding.commentRecyclerView.adapter = CommentAdapter(it)
            }
        }
    }
}