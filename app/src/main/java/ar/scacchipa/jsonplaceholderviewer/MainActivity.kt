package ar.scacchipa.jsonplaceholderviewer

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ar.scacchipa.jsonplaceholderviewer.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    var userCase = MiddleCommentsUserCase()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val commentVM: MiddleCommentViewModel by viewModels()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.commentRecyclerView.layoutManager = LinearLayoutManager(this)

        setContentView(binding.root)

        binding.middleCommentButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                 binding.commentRecyclerView.adapter =
                    CommentAdapter( userCase.getMiddleCommentList() )
            }
        }
        commentVM.middleCommentList.observe(this, { comments ->
            comments?.let {
                binding.commentRecyclerView.adapter = CommentAdapter(it)
            }
        })

    }
}