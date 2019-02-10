package com.chesire.zwei.xivapi

sealed class Resource<T> {
    class Success<T>(val data: T) : Resource<T>()
    class Error<T>(val msg: String) : Resource<T>()
}
