package ar.scacchipa.jsonplaceholderviewer

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ar.scacchipa.jsonplaceholderviewer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var userCase = MiddleCommentsUserCase()
    val commentVM: MiddleCommentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        commentVM.userCase = userCase

        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.commentRecyclerView.layoutManager = LinearLayoutManager(this)

        setContentView(binding.root)

        binding.middleCommentButton.setOnClickListener {
            commentVM.update()
        }
        commentVM.middleCommentList.observe(this, { comments ->
            comments?.let {
                binding.commentRecyclerView.adapter = CommentAdapter(it)
            }
        })

    }
}