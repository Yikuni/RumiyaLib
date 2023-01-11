package com.yikuni.mc.rumiyalib.command

import com.yikuni.mc.reflect.annotation.YikuniCommand
import com.yikuni.mc.rumiyalib.RumiyaLib
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

@YikuniCommand("forbiddenBan")
class ForbiddenBan: CommandExecutor, TabCompleter {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()){
            return false
        }
        return when(args[0]){
            "add" ->{
                if (args.size != 2 || RumiyaLib.forbiddenBanTable.data.contains(args[1])) false
                else{
                    RumiyaLib.forbiddenBanTable.add(args[1])
                    RumiyaLib.forbiddenBanTable.save()
                    true
                }
            }
            "remove" ->{
                if (args.size != 2 || !RumiyaLib.forbiddenBanTable.data.contains(args[1])) false
                else{
                    RumiyaLib.forbiddenBanTable.remove(args[1])
                    true
                }
            }
            "list" ->{
                val builder = StringBuilder()
                RumiyaLib.forbiddenBanTable.forEach {
                    builder.append(it)
                    builder.append(" | ")
                }
                sender.sendMessage(builder.toString())
                true
            }
            "help" ->{
                sender.sendMessage("暂时没有帮助文档")
                true
            }
            else ->{
                false
            }

        }
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): MutableList<String>? {
        return if (args.size == 1){
            arrayOf("add", "remove", "list", "help").filter { it.startsWith(args[0]) }.toMutableList()
        }else null
    }
}