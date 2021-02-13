package no.sample.asyncapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.recyclerview.widget.LinearLayoutManager
import no.sample.asyncapp.adapter.ImageLinkAdapter
import no.sample.asyncapp.databinding.ActivityImagesBinding


class ImagesActivity : AppCompatActivity() {


    var links =
            arrayOf( "https://scontent.fosl1-1.fna.fbcdn.net/v/t1.0-9/57162439_313932025963228_7369044018753175552_n.jpg?_nc_cat=111&ccb=3&_nc_sid=730e14&_nc_ohc=kegjWvd8bNgAX9UfFyb&_nc_ht=scontent.fosl1-1.fna&oh=3bc543d3e5fe0912decd7ac701d963aa&oe=604A833E",
                    "https://upload.wikimedia.org/wikipedia/commons/2/23/Lake_mapourika_NZ.jpeg",
                    "https://dayhikesneardenver.com/wp-content/uploads/2018/05/booth-creek-trail-mountains-and-aspens.jpg",
                    "https://dayhikesneardenver.com/wp-content/uploads/2016/02/01-sprague-lake-loop-rocky-mountain-national-park-beaver.jpg",
                    "https://cdn.thecrazytourist.com/wp-content/uploads/2017/10/ccimage-shutterstock_214491958.jpg")




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityImagesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = ImageLinkAdapter(links)

    }
}
