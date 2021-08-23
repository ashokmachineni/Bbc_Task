package com.android.apptask.utils

fun interface OnClick<T> {

    operator fun invoke(item: T, position: Int)

}