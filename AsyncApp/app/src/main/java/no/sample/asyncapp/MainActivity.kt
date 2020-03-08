package no.sample.asyncapp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedInputStream
import java.lang.Exception
import java.net.URL
import java.util.concurrent.Semaphore

class MainActivity : AppCompatActivity() {


    //An array of string containing links of images
    var links =
            arrayOf(
                    "https://upload.wikimedia.org/wikipedia/commons/2/23/Lake_mapourika_NZ.jpeg",
                    "https://dayhikesneardenver.com/wp-content/uploads/2018/05/booth-creek-trail-mountains-and-aspens.jpg",
                    "https://dayhikesneardenver.com/wp-content/uploads/2016/02/01-sprague-lake-loop-rocky-mountain-national-park-beaver.jpg",
                    "https://cdn.thecrazytourist.com/wp-content/uploads/2017/10/ccimage-shutterstock_214491958.jpg")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //An array of ImageViews declared in the activity layout
        var imageViews =
                 arrayOf(imageView1,
                        imageView2,
                        imageView3,
                        imageView4)

        for (i in 0..3){ // Kotlin loop that will start from index 0 to 3

            var thread = Thread( MyDownloader(links.get(i), imageViews.get(i)) );
            thread.start()

        }

    }

    // A runnable implementation that calls loadWebImage
    inner class MyDownloader( var link:String ,  var imageView: ImageView)  : Runnable {

        override fun run() {
            try
            {
                loadWebImage(link, imageView)
            }
            catch ( ex : InterruptedException)
            {
                Log.e(MyDownloader::javaClass.name, ex.toString())
            }
        }
    }


    // A method that downloads Image and assigns the bitmap into ImageView using runOnUiThread
    fun loadWebImage(link:String ,  imageView: ImageView){

        var input:BufferedInputStream? = null

        try {
            val url = URL(link)

            val conection = url.openConnection()
            conection.connect()

            var fileSize =conection.contentLength
            val input = BufferedInputStream(url.openStream(), fileSize)


            var bitmap = decodeSync(input)

            this@MainActivity.runOnUiThread {
                imageView.setImageBitmap(bitmap)
            }

        }
        catch (ex:Exception){
            Log.e("MainActivity", ex.toString())
        }
        finally {
            input?.close()
        }
    }

    // A semaphore object that can only be acquired by one thread
    var semaphore = Semaphore(1)
    // Synchronized method that applies semaphore to ensure single thread access at any given time
    private fun decodeSync(input: BufferedInputStream): Bitmap? {

        semaphore.acquire()
        var bitmap = BitmapFactory.decodeStream(input)
        semaphore.release()
        return bitmap;
    }




}
