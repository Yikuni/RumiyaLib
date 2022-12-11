@file:JvmName("ItemUtil")
package com.yikuni.mc.rumiyalib.utils


import com.yikuni.mc.rumiyalib.RumiyaLib
import com.yikuni.mc.rumiyalib.inventory.ItemEnchant
import com.yikuni.mc.rumiyalib.inventory.NBTPair
import de.tr7zw.nbtapi.NBTItem
import net.md_5.bungee.api.chat.ComponentBuilder
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.ItemTag
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.chat.hover.content.Item
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
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

fun getItemText(itemStack: ItemStack): TextComponent {
    val nbtItem = NBTItem(itemStack)
    val item = Item(itemStack.type.key.key, 1, ItemTag.ofNbt(nbtItem.toString()))
    val name: String
    val itemMeta = itemStack.itemMeta
    name = if (itemMeta!!.hasDisplayName()) {
        itemMeta.displayName
    } else {
        itemStack.type.key.key
    }
    return TextComponent(
        *ComponentBuilder().append("[").append(name)
            .event(HoverEvent(HoverEvent.Action.SHOW_ITEM, item)).append("]").create()
    )
}

fun createItem(name: String, material: Material, enchant: Enchantment? = null, level: Int = 0, lores: List<String>? = null, nbtMap: HashMap<NamespacedKey, String>? = null, count: Int = 1): ItemStack{
    val item = ItemStack(material, count)
    val itemMeta = item.itemMeta?: createItemMeta(material)!!
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
    val itemMeta = item.itemMeta?: createItemMeta(material)!!
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

fun createItem(name: String, material: Material, enchantList: List<ItemEnchant>, lores: List<String>, nbtList: List<NBTPair>, count: Int = 1): ItemStack{
    val item = ItemStack(material, count)
    val itemMeta = item.itemMeta?: createItemMeta(material)!!
    itemMeta.setDisplayName(name)
    itemMeta.lore = lores
    enchantList.forEach {
        itemMeta.addEnchant(Enchantment.getByKey(NamespacedKey("minecraft", it.enchantment))!!, it.level, true)
    }
    val container = itemMeta.persistentDataContainer
    nbtList.forEach { container.set(NamespacedKey(it.namespace, it.key), PersistentDataType.STRING, it.value)}
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

fun createItemMeta(material: Material): ItemMeta?{
    return RumiyaLib.getInstance().server.itemFactory.getItemMeta(material)
}