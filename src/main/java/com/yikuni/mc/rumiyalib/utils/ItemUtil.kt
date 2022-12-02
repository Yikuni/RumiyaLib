@file:JvmName("ItemUtil")
package com.yikuni.mc.rumiyalib.utils


import net.md_5.bungee.api.chat.ComponentBuilder
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.ItemTag
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.chat.hover.content.Item
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

public val oreArray = arrayOf(
    Material.COAL_ORE,
    Material.DEEPSLATE_COAL_ORE,
    Material.IRON_ORE,
    Material.DEEPSLATE_IRON_ORE,
    Material.COPPER_ORE,
    Material.DEEPSLATE_COPPER_ORE,
    Material.GOLD_ORE,
    Material.DEEPSLATE_GOLD_ORE,
    Material.REDSTONE_ORE,
    Material.DEEPSLATE_REDSTONE_ORE,
    Material.EMERALD_ORE,
    Material.DEEPSLATE_EMERALD_ORE,
    Material.LAPIS_ORE,
    Material.DEEPSLATE_LAPIS_ORE,
    Material.DIAMOND_ORE,
    Material.DEEPSLATE_DIAMOND_ORE,
    Material.NETHER_GOLD_ORE,
    Material.NETHER_QUARTZ_ORE
)


fun createItem(name: String, material: Material, enchant: Enchantment? = null, level: Int = 0, lores: List<String>? = null, nbtMap: HashMap<NamespacedKey, String>? = null, count: Int = 1): ItemStack{
    val item = ItemStack(material, count)
    val itemMeta = item.itemMeta!!
    itemMeta.setDisplayName(name)
    if (lores != null){
        itemMeta.lore = lores
    }
    if (enchant != null){
        itemMeta.addEnchant(enchant, level, true)
    }
    if (nbtMap != null){
        val container = itemMeta.persistentDataContainer
        nbtMap.forEach { (k, v) ->  container.set(k, PersistentDataType.STRING, v)}
    }
    item.itemMeta = itemMeta
    return item
}

fun createItem(name: String, material: Material, enchantMap: Map<Enchantment, Int>, lores: List<String>, nbtMap: Map<NamespacedKey, String>? = null, count: Int = 1): ItemStack{
    val item = ItemStack(material, count)
    val itemMeta = item.itemMeta!!
    itemMeta.setDisplayName(name)
    itemMeta.lore = lores
    enchantMap.forEach { (k, v)->
        itemMeta.addEnchant(k, v, true)
    }
    val container = itemMeta.persistentDataContainer
    if (nbtMap != null){
        val container = itemMeta.persistentDataContainer
        nbtMap.forEach { (k, v) ->  container.set(k, PersistentDataType.STRING, v)}
    }
    item.itemMeta = itemMeta
    return item
}


fun isOre(type: Material): Boolean{
    return type in arrayOf(
        Material.COAL_ORE,
        Material.DEEPSLATE_COAL_ORE,
        Material.IRON_ORE,
        Material.DEEPSLATE_IRON_ORE,
        Material.COPPER_ORE,
        Material.DEEPSLATE_COPPER_ORE,
        Material.GOLD_ORE,
        Material.DEEPSLATE_GOLD_ORE,
        Material.REDSTONE_ORE,
        Material.DEEPSLATE_REDSTONE_ORE,
        Material.EMERALD_ORE,
        Material.DEEPSLATE_EMERALD_ORE,
        Material.LAPIS_ORE,
        Material.DEEPSLATE_LAPIS_ORE,
        Material.DIAMOND_ORE,
        Material.DEEPSLATE_DIAMOND_ORE,
        Material.NETHER_GOLD_ORE,
        Material.NETHER_QUARTZ_ORE
    )
}