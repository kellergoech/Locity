package de.zw.locity.datatypes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import de.zw.locity.databinding.ListItemBinding

class ArticleAdapter(val listener: ItemClickListener<Article>) : ListAdapter<Article, CustomViewHolder>(
    Companion
) {
    companion object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return  oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return  oldItem.Id == newItem.Id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)

        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val currentArticle = getItem(position)
        val itemBinding = holder.binding as ListItemBinding

        itemBinding.root.setOnClickListener { listener.onClickListener(currentArticle) }
        itemBinding.article = currentArticle
        itemBinding.executePendingBindings()
    }
}