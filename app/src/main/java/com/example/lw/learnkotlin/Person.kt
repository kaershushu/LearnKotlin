package com.example.lw.learnkotlin

/**
 * Created on 2018/11/23.
 * @author Alan
 */
class Person {
    var name:String = ""
        get() = field.toUpperCase()
        set(value) {
            field = value.toLowerCase()
        }
}