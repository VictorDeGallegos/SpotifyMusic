package com.example.music.albums

import android.view.View
import com.bumptech.glide.Glide
import com.example.music.R
import com.example.music.databinding.EachItemBinding
import com.xwray.groupie.viewbinding.BindableItem

class AlbumItem(var albumModel: AlbumModel): BindableItem<EachItemBinding>() {
    override fun bind(binding: EachItemBinding, position: Int) {
       binding.textView.text=albumModel.title
        Glide.with(binding.root.context).load(albumModel.img).into(binding.imageViewAlbum)
    }

    override fun getLayout(): Int {
        return R.layout.each_item
    }

    override fun initializeViewBinding(view: View): EachItemBinding {
        return EachItemBinding.bind(view)
    }
}