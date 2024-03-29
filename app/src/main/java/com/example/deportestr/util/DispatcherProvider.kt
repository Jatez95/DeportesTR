package com.example.deportestr.util

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
     val mainDispatcher: CoroutineDispatcher
     val ioDispatcher: CoroutineDispatcher
     val cpuDispatcher: CoroutineDispatcher
}