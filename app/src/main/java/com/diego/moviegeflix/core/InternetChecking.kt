package com.diego.moviegeflix.core

import kotlinx.coroutines.coroutineScope
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

object InternetChecking {
    //This method return (true) if there are connection to Internet and (false) if there are not it
    suspend fun isNetworkConnect() = coroutineScope {
        return@coroutineScope try {
            val sock = Socket()
            val socketAddress = InetSocketAddress("8.8.8.8",53)
            //Validate if there are connection to internet.
            sock.connect(socketAddress,2000)
            sock.close()
            true
        }catch (e: IOException){
            false
        }
    }
}