package com.example.sunnyweather.logic


import android.util.Log
import androidx.lifecycle.liveData
import com.example.sunnyweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers

object Repository {
    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            Log.d("test",query)
            val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
            if (placeResponse.status == "ok") {
                Log.d("ok", placeResponse.toString())
                val places = placeResponse.places
                Result.success(places)
            } else {
                Log.d("error1", placeResponse.toString())
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        } catch (e: Exception) {
            Log.d("error2",e.toString())
            Result.failure(e)
        }
        emit(result)
    }
}
