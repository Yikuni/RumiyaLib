package com.yikuni.mc.rumiyalib.interceptor

import com.yikuni.mc.reflect.annotation.CommandInterceptor
import com.yikuni.mc.reflect.common.interceptor.Interceptor
import com.yikuni.mc.rumiyalib.RumiyaLib
import org.bukkit.entity.Player


@CommandInterceptor(value = "ban", priority = 1)
class BanInterceptor: Interceptor {
    override fun onCommand(player: Player, args: Array<out String>): Boolean {
        return player.isOp && RumiyaLib.forbiddenBanTable.data.contains(args[0])
    }
}