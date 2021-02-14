package no.sample.asyncapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import no.sample.asyncapp.R
import no.sample.asyncapp.databinding.ImageItemBinding
import no.sample.asyncapp.downloader.ImageDownloader


class ImageLinkAdapter(private val list: Array<String>) : RecyclerView.Adapter<ImageLinkAdapter.ImageViewHolder>()
{
    var imageDownloader = ImageDownloader()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding =  ImageItemBinding.inflate(LayoutInflater.from(parent.context))

        return ImageViewHolder(binding, imageDownloader)
    }


    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    class ImageViewHolder(private val binding: ImageItemBinding, val downloader: ImageDownloader) :
        RecyclerView.ViewHolder(binding.root)
    {

        fun bind( link: String) {
            binding.imageView.setImageResource(R.drawable.ic_downloading_black_48dp)
            downloader.download(link, binding.imageView)
        }

    }
}
