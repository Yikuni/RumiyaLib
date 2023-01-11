package com.yikuni.mc.rumiyalib.command

import com.yikuni.mc.reflect.annotation.YikuniCommand
import com.yikuni.mc.reflect.annotation.YikuniEvent
import com.yikuni.mc.rumiyalib.RumiyaLib
import com.yikuni.mc.rumiyalib.utils.sender
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerCommandPreprocessEvent

@YikuniEvent
class ForbiddenCommands: Listener {
    private val forbiddenCommands = RumiyaLib.getInstance().config.getStringList("forbiddenCommands")
    @EventHandler
    fun onCommand(event: PlayerCommandPreprocessEvent){
        if (forbiddenCommands.contains(event.message.substring(1, event.message.indexOf(' ')))){
            event.isCancelled = true
            event.player.sender().error("This command is not allowed in this server!")
        }
    }

}