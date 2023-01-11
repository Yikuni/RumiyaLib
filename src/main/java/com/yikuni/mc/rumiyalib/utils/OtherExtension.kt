package com.yikuni.mc.rumiyalib.utils

import com.yikuni.mc.rumiyalib.sender.CommandSenderSender
import com.yikuni.mc.rumiyalib.sender.Sender
import org.bukkit.command.CommandSender

fun CommandSender.sender(): Sender{
    return CommandSenderSender(this)
}