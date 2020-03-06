package no.sample.asyncapp

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedInputStream
import java.lang.Exception
import java.net.URL

class MainActivity : AppCompatActivity() {


    var links =
            arrayOf(
                    "https://upload.wikimedia.org/wikipedia/commons/2/23/Lake_mapourika_NZ.jpeg",
                    "https://dayhikesneardenver.com/wp-content/uploads/2018/05/booth-creek-trail-mountains-and-aspens.jpg",
                    "https://dayhikesneardenver.com/wp-content/uploads/2016/02/01-sprague-lake-loop-rocky-mountain-national-park-beaver.jpg",
                    "https://cdn.thecrazytourist.com/wp-content/uploads/2017/10/ccimage-shutterstock_214491958.jpg")




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var imageViews =
                 arrayOf(imageView1,
                        imageView2,
                        imageView3,
                        imageView4)


        for (i in 0..3){
            loadWebImage(links.get(i), imageViews.get(i))
        }

    }


    fun loadWebImage(link:String ,  imageView: ImageView){

        var input:BufferedInputStream? = null

        try {
            val url = URL(link)

            val conection = url.openConnection()
            conection.connect()

            var fileSize =conection.contentLength
            val input = BufferedInputStream(url.openStream(), fileSize)
            var bitmap = BitmapFactory.decodeStream(input)

            imageView.setImageBitmap(bitmap)

        }
        catch (ex:Exception){
            Log.e("MainActivity", ex.toString())
        }
        finally {
            input?.close()
        }
    }

}
