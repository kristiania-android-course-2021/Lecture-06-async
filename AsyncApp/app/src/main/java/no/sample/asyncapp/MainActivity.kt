package no.sample.asyncapp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
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
            DownloaderAsyncTask(imageViews.get(i)).execute(links.get(i))
        }

    }



    inner class DownloaderAsyncTask(var imageView: ImageView) : AsyncTask<String, Int, Bitmap?>(){

        override fun doInBackground(vararg params: String): Bitmap? {
            return loadWebImage(params.get(0), imageView)
        }

        override fun onPostExecute(result: Bitmap?) {
            imageView.setImageBitmap(result)
            super.onPostExecute(result)
        }
    }



    fun loadWebImage(link:String ,  imageView: ImageView) : Bitmap?{

        var input:BufferedInputStream? = null

        try {
            val url = URL(link)

            val conection = url.openConnection()
            conection.connect()

            var fileSize =conection.contentLength
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


    var semaphore = Semaphore(1)

    private fun decodeSync(input: BufferedInputStream): Bitmap? {

        semaphore.acquire()

        var bitmap = BitmapFactory.decodeStream(input)

        semaphore.release()

        return bitmap;
    }
    
}
