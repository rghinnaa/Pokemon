package com.assignment.pokemon.utils

import android.util.Log
import com.assignment.pokemon.data.remote.Result
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.Response
import java.net.SocketTimeoutException

inline fun <reified T> flowResponse(
    crossinline responseBody: suspend () -> Response<T>
) = flow<Result<T>> {
    val response = responseBody.invoke()

    if(response.isSuccessful) {
        response.body()?.let { result ->
            emit(Result.success(result))
        }
    } else {
        emit(Result.error(response.errorBody().toString(), response.code()))
    }

}
    .onStart { emit(Result.loading()) }
    .flowOn(Dispatchers.IO)
    .retryWhen { cause, attempt ->
        attempt <= 3 && cause is SocketTimeoutException
    }
    .catch { throwable ->
        Firebase.crashlytics.recordException(throwable)
        Log.e("Error","@${T::class.java} : $throwable")
        emit(Result.error("Timeout", code = 500))
    }