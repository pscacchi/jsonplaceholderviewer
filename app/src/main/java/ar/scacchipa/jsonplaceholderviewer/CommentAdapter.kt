package ar.scacchipa.jsonplaceholderviewer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.scacchipa.jsonplaceholderviewer.databinding.CommentLayoutBinding

class CommentAdapter (
    private val comments: List<Comment> = listOf()
):RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    class CommentViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val binding = CommentLayoutBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.comment_layout, parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        comments[position].let { comment ->
            holder.binding.idTv .text = comment.id.toString()
            holder.binding.postIdTv.text = comment.postId.toString()
            holder.binding.emailTv.text = comment.email
            holder.binding.bodyTv.text = comment.body
        }
    }

    override fun getItemCount(): Int {
        return comments.size
    }


}