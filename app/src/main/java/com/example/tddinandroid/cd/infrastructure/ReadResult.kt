package com.example.tddinandroid.cd.infrastructure

sealed class ReadResult<out T : Any> {
    class Success<out T : Any>(val value: T) : ReadResult<T>()
    object NotAvailable : ReadResult<Nothing>()
}