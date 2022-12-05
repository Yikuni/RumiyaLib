package com.yikuni.mc.rumiyalib.command

import com.yikuni.mc.reflect.annotation.YikuniCommand
import com.yikuni.mc.rumiyalib.RumiyaLib
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

@YikuniCommand(value = "debug", description = "开启/关闭调试")
class DebugCommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        sender.sendMessage("Debug set to ${!RumiyaLib.debug}")
        RumiyaLib.debug = !RumiyaLib.debug
        return true
    }
}