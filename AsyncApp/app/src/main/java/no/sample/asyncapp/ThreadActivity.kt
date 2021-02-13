package no.sample.asyncapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import no.sample.asyncapp.databinding.ActivityThreadBinding

class ThreadActivity : AppCompatActivity() {

    lateinit var binding : ActivityThreadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityThreadBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.buttonCreatThread.setOnClickListener{
            var thread = Thread( MyRunnable() )
            thread.start()
        }
    }


    inner  class MyRunnable : Runnable
    {
        override fun run() {

            Thread.sleep(2000)

            var msg  = Message()
            msg.obj = Thread.currentThread().name
            handler.sendMessage(msg)

            /*this@ThreadActivity.runOnUiThread {
               binding.textView.text =  "${binding.textView.text}\n${threadName}"
            }*/
        }
    }

    var handler = object : Handler(Looper.getMainLooper())
    {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            binding.textView.text =  "${binding.textView.text}\n${msg.obj as String}"
        }
    }
}