package no.sample.asyncapp.downloader

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.ImageView
import java.io.BufferedInputStream
import java.lang.Exception
import java.net.URL
import java.util.concurrent.Semaphore

class ImageDownloader() {

    var semaphore = Semaphore(1)

    fun download (link: String, imageView: ImageView){
        var thread = Thread( DownloadTask(link, imageView) )
        thread.start()
    }

    inner class DownloadTask( var link:String ,  var imageView: ImageView)  : Runnable {
        override fun run() {

            var bitmap = getDownloadBitmap(link)
            var msg = Message()
            msg.what = 0
            msg.obj = DataPacket(imageView, bitmap)

            handler.sendMessage(msg)
        }
    }



    private var handler = object : Handler(Looper.getMainLooper()) {

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

    private fun getDownloadBitmap(link:String) : Bitmap?{

        var input: BufferedInputStream? = null

        try {
            val url = URL(link)

            val conection = url.openConnection()
            conection.connect()

            var fileSize =conection.contentLength
            val input = BufferedInputStream(url.openStream(), fileSize)
            return  decodeSync(input)

        }
        catch (ex: Exception){
            Log.e("MainActivity", ex.toString())
        }
        finally {
            input?.close()
        }

        return null
    }

    private fun decodeSync(input: BufferedInputStream): Bitmap? {

        semaphore.acquire()

        var bitmap = BitmapFactory.decodeStream(input)

        semaphore.release()

        return bitmap;
    }

    data class DataPacket (var imageView: ImageView, var bitmap: Bitmap?)

}