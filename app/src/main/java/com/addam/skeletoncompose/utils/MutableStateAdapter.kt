package com.addam.skeletoncompose.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * Created by addam on 24/11/2022
 */
/**
 * Created by addam on 16/11/2022
 * From: https://www.droidcon.com/2022/04/13/two-way-data-binding-in-jetpack-compose/
 * From: https://gist.github.com/burnoo/730902ddb31a5b6db5cda65ff0d46fe5
 */
class MutableStateAdapter<T>(
    private val state: State<T>,
    private val mutate: (T) -> Unit
): MutableState<T> {
    override var value: T
        get() = state.value
        set(value) {mutate(value)}

    override fun component1(): T = value

    override fun component2(): (T) -> Unit = { value = it}

}

//Flow
@Composable
fun <T> MutableStateFlow<T>.collectAsMutableState(
    context: CoroutineContext = EmptyCoroutineContext
): MutableState<T> = MutableStateAdapter(
    state = collectAsState(context),
    mutate = {value = it}
)

//Livedata
@Composable
fun <T> MutableLiveData<T>.observeAsMutableState(
    initialValue: T
): MutableState<T> = MutableStateAdapter(
    state = observeAsMutableState(initialValue),
    mutate = { postValue(it) }
)

// RxJava 2/3
//@Composable
//fun <T> PublishSubject<T>.subscribeAsMutableState(
//    initialValue: T
//): MutableState<T> = MutableStateAdapter(
//    state = subscribeAsState(initialValue),
//    mutate = { onNext(it) }
//)