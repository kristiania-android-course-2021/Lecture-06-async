package no.sample.asyncapp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
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

            var thread = Thread( MyDownloader(links.get(i), imageViews.get(i)) );
            thread.start()

        }

    }

    var handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            when( msg.what){
                0 ->
                {
                    var dpack =  msg.obj as DataPacket
                    dpack.imageView.setImageBitmap(dpack.bitmap)

                    Log.d("Message", msg.toString())
                }
            }

        }
    }


    inner class MyDownloader( var link:String ,  var imageView: ImageView)  : Runnable{
        override fun run() {
            loadWebImage(link, imageView)
        }
    }

    data class DataPacket (var imageView: ImageView, var bitmap: Bitmap?)

    fun loadWebImage(link:String ,  imageView: ImageView){

        var input:BufferedInputStream? = null

        try {
            val url = URL(link)

            val conection = url.openConnection()
            conection.connect()

            var fileSize =conection.contentLength
            val input = BufferedInputStream(url.openStream(), fileSize)


            var bitmap = decodeSync(input)


            var msg = Message()
            msg.what = 0
            msg.obj = DataPacket(imageView, bitmap)


            handler.sendMessage(msg)

        }
        catch (ex:Exception){
            Log.e("MainActivity", ex.toString())
        }
        finally {
            input?.close()
        }
    }


    var semaphore = Semaphore(1)

    private fun decodeSync(input: BufferedInputStream): Bitmap? {

        semaphore.acquire()

        var bitmap = BitmapFactory.decodeStream(input)

        semaphore.release()

        return bitmap;
    }




}
