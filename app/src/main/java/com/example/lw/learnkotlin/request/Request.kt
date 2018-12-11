package com.example.lw.learnkotlin.request

/**
 * Created on 2018/11/23.
 * @author Alan
 */
interface Request<T> {
    fun execute(): T
}
