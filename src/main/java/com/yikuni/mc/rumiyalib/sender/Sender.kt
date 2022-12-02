package com.yikuni.mc.rumiyalib.sender

import com.yikuni.mc.rumiyalib.RumiyaLib


interface Sender {
    fun info(msg:String)
    fun warn(msg:String)
    fun error(msg:String)
    fun success(msg:String)
    fun primary(msg:String)
    fun debug(msg: String){
        val boolean = RumiyaLib.debug
        if (boolean) info(msg)
    }
}