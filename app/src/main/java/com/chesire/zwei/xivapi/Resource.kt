package com.chesire.zwei.xivapi

data class Resource<out T>(
    val status: Status,
    val data: T,
    val message: String = ""
) {
    companion object {
        fun <T> success(data: T): Resource<T> = Resource(Status.Success, data)
        fun <T> error(msg: String, data: T): Resource<T> = Resource(Status.Error, data, msg)
        fun <T> loading(data: T): Resource<T> = Resource(Status.Loading, data)
    }
}
