package com.example.lw.learnkotlin.domin

interface Command<T> {
    fun execute():T
}