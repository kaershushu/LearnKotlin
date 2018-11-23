package com.example.lw.learnkotlin.request

/**
 * Created on 2018/11/23.
 * @author Alan
 */
class RequestImpl : Request {
    override fun request(strategy: RequestStrategy): String = strategy.request()
}