package no.sample.asyncapp.downloader

import java.util.concurrent.Semaphore

class ThreadSafeCounter {
    private val semaphore= Semaphore(1)
    var counter = 0

    fun countMe(){
        semaphore.acquire()
        counter++
        semaphore.release()

    }

    fun deCountMe() {
        semaphore.acquire()

        counter--

        semaphore.release()

    }
}