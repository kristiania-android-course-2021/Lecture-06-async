package no.sample.asyncapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.recyclerview.widget.LinearLayoutManager
import no.sample.asyncapp.adapter.ImageLinkAdapter
import no.sample.asyncapp.databinding.ActivityImagesBinding
import java.util.*


class ImagesActivity : AppCompatActivity() {


    var links =
            arrayOf( "https://scontent.fosl1-1.fna.fbcdn.net/v/t1.0-9/57162439_313932025963228_7369044018753175552_n.jpg?_nc_cat=111&ccb=3&_nc_sid=730e14&_nc_ohc=kegjWvd8bNgAX9UfFyb&_nc_ht=scontent.fosl1-1.fna&oh=3bc543d3e5fe0912decd7ac701d963aa&oe=604A833E",
                    "https://upload.wikimedia.org/wikipedia/commons/2/23/Lake_mapourika_NZ.jpeg",
                    "https://dayhikesneardenver.com/wp-content/uploads/2018/05/booth-creek-trail-mountains-and-aspens.jpg",
                    "https://dayhikesneardenver.com/wp-content/uploads/2016/02/01-sprague-lake-loop-rocky-mountain-national-park-beaver.jpg",
                    "https://cdn.thecrazytourist.com/wp-content/uploads/2017/10/ccimage-shutterstock_214491958.jpg",
                    "https://a4.pbase.com/o6/82/431282/1/110170717.pEgOCQgn.Horseheadfinal.jpg",
                    "https://a4.pbase.com/o12/03/946003/1/171408140.MpMvGPWg.AColorfulBarnonaGrayWinterDay.jpg",
                    "https://a4.pbase.com/o6/82/431282/1/127056477.6zFHDq5l.BlueMounta_posite.jpg",
                    "https://a4.pbase.com/o6/11/394311/1/32764357.gJK3B5Zl.CRW_3874aa.jpg",
                    "https://a4.pbase.com/o3/82/431282/1/133389315.pQEoB7Zl.TheSydneyOperaHouse.JPG",
                    "https://a4.pbase.com/o12/79/763779/1/170405323.lobcojDm.NewMex_2020-01474.jpg",
                    "https://a4.pbase.com/o2/87/510887/1/106767801.Ixu4Kg9d.DoiIntanonNT10222008063807.tiff.jpg",
                    "https://a4.pbase.com/o2/87/510887/1/106767802.IvY5qwTO.DoiIntanonNT10222008064541.tiff.jpg",
                    "https://a4.pbase.com/o4/87/510887/1/102422749.OvBVcHz0.ErawanNationalPark08NT0357.jpg",
                    "https://scontent.fosl1-1.fna.fbcdn.net/v/t1.0-9/57162439_313932025963228_7369044018753175552_n.jpg?_nc_cat=111&ccb=3&_nc_sid=730e14&_nc_ohc=kegjWvd8bNgAX9UfFyb&_nc_ht=scontent.fosl1-1.fna&oh=3bc543d3e5fe0912decd7ac701d963aa&oe=604A833E",
                    "https://upload.wikimedia.org/wikipedia/commons/2/23/Lake_mapourika_NZ.jpeg",
                    "https://dayhikesneardenver.com/wp-content/uploads/2018/05/booth-creek-trail-mountains-and-aspens.jpg",
                    "https://dayhikesneardenver.com/wp-content/uploads/2016/02/01-sprague-lake-loop-rocky-mountain-national-park-beaver.jpg",
                    "https://cdn.thecrazytourist.com/wp-content/uploads/2017/10/ccimage-shutterstock_214491958.jpg",
                    "https://a4.pbase.com/o6/82/431282/1/110170717.pEgOCQgn.Horseheadfinal.jpg",
                    "https://a4.pbase.com/o12/03/946003/1/171408140.MpMvGPWg.AColorfulBarnonaGrayWinterDay.jpg",
                    "https://a4.pbase.com/o6/82/431282/1/127056477.6zFHDq5l.BlueMounta_posite.jpg",
                    "https://a4.pbase.com/o6/11/394311/1/32764357.gJK3B5Zl.CRW_3874aa.jpg",
                    "https://a4.pbase.com/o3/82/431282/1/133389315.pQEoB7Zl.TheSydneyOperaHouse.JPG",
                    "https://a4.pbase.com/o12/79/763779/1/170405323.lobcojDm.NewMex_2020-01474.jpg",
                    "https://a4.pbase.com/o2/87/510887/1/106767801.Ixu4Kg9d.DoiIntanonNT10222008063807.tiff.jpg",
                    "https://a4.pbase.com/o2/87/510887/1/106767802.IvY5qwTO.DoiIntanonNT10222008064541.tiff.jpg",
                    "https://a4.pbase.com/o4/87/510887/1/102422749.OvBVcHz0.ErawanNationalPark08NT0357.jpg")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityImagesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = ImageLinkAdapter(links)


    }


}
