package no.sample.asyncapp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedInputStream
import java.lang.Exception
import java.net.URL
import java.util.concurrent.Semaphore

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


    }



    // loadWebImage downloads and returns the Bitmap
    fun loadWebImage(link:String) : Bitmap? {

        var input:BufferedInputStream? = null

        try {
            val url = URL(link)

            val connection = url.openConnection()
            connection.connect()

            var fileSize = connection.contentLength
            val input = BufferedInputStream(url.openStream(), fileSize)


            return decodeSync(input)

        }
        catch (ex:Exception){
            Log.e("MainActivity", ex.toString())
        }
        finally {
            input?.close()
        }

        return null
    }


    var semaphore = Semaphore(1) // A semaphore which only permits one thread to execute the code.

    private fun decodeSync(input: BufferedInputStream): Bitmap? {
        semaphore.acquire() // Only one thread will acquire it

        var bitmap = BitmapFactory.decodeStream(input)

        semaphore.release() // semaphore is released

        return bitmap;
    }
    
}
