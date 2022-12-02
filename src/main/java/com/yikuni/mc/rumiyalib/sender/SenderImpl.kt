package com.yikuni.mc.rumiyalib.sender


import com.yikuni.mc.rumiyalib.RumiyaLib
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.ComponentBuilder
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import java.util.logging.Logger

/**
 * 玩家消息发送器
 */
class PlayerSender(private val player: Player): Sender {
    private val log: Logger = RumiyaLib.getInstance().logger
    override fun info(msg: String) {
        player.sendMessage("${ChatColor.GRAY}$msg")
        log.info("Player ${player.name} received msg: $msg")
    }

    override fun warn(msg: String) {
        player.sendMessage("${ChatColor.YELLOW}$msg")
        log.info("Player ${player.name} received msg: $msg")
    }

    override fun error(msg: String) {
        player.sendMessage("${ChatColor.RED}$msg")
        log.info("Player ${player.name} received msg: $msg")
    }

    override fun success(msg: String) {
        player.sendMessage("${ChatColor.GREEN}$msg")
        log.info("Player ${player.name} received msg: $msg")
    }

    override fun primary(msg: String) {
        player.sendMessage("${ChatColor.BLUE}$msg")
        log.info("Player ${player.name} received msg: $msg")
    }
}



/**
 * 服务器消息发送器
 */
object ServerSender: Sender {
    private val log: Logger = RumiyaLib.getInstance().logger
    override fun info(msg: String) {
        Bukkit.broadcastMessage("${ChatColor.DARK_GREEN}[Server] ${ChatColor.GRAY}$msg")
    }

    override fun warn(msg: String) {
        Bukkit.broadcastMessage("${ChatColor.DARK_GREEN}[Server] ${ChatColor.YELLOW}$msg")
    }

    override fun error(msg: String) {
        Bukkit.broadcastMessage("${ChatColor.DARK_GREEN}[Server] ${ChatColor.RED}$msg")
    }

    override fun success(msg: String) {
        Bukkit.broadcastMessage("${ChatColor.DARK_GREEN}[Server] ${ChatColor.GREEN}$msg")
    }

    override fun primary(msg: String) {
        Bukkit.broadcastMessage("${ChatColor.DARK_GREEN}[Server] ${ChatColor.BLUE}$msg")
    }
    fun chat(msg: String){
        Bukkit.broadcastMessage("${ChatColor.DARK_GREEN}[Server] ${ChatColor.WHITE}$msg")
    }
}

object ChatSender{
    fun send(comps: Array<out BaseComponent>){
        CoroutineScope(Dispatchers.Default).launch {
            Bukkit.getServer().onlinePlayers.forEach {
                it.spigot().sendMessage(*comps)
            }
        }
    }
}

class MultiSender(private val senders: Array<Sender>): Sender {
    override fun info(msg: String) {
        senders.forEach {
            it.info(msg)
        }
    }

    override fun warn(msg: String) {
        senders.forEach {
            it.warn(msg)
        }
    }

    override fun error(msg: String) {
        senders.forEach {
            it.error(msg)
        }
    }

    override fun success(msg: String) {
        senders.forEach {
            it.success(msg)
        }
    }

    override fun primary(msg: String) {
        senders.forEach {
            it.primary(msg)
        }
    }

}

class PlayerActionBarSender(private val player: Player): Sender {
    override fun info(msg: String) {
        send(msg, net.md_5.bungee.api.ChatColor.GRAY)
    }

    override fun warn(msg: String) {
        send(msg, net.md_5.bungee.api.ChatColor.YELLOW)
    }

    override fun error(msg: String) {
        send(msg, net.md_5.bungee.api.ChatColor.RED)
    }

    override fun success(msg: String) {
        send(msg, net.md_5.bungee.api.ChatColor.GREEN)
    }

    override fun primary(msg: String) {
        send(msg, net.md_5.bungee.api.ChatColor.BLUE)
    }

    private fun send(msg: String, color: net.md_5.bungee.api.ChatColor){
        val components = ComponentBuilder().append(msg).color(color).create()
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, *components)
    }
}
