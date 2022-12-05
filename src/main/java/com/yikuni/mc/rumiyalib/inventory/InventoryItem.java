package com.yikuni.mc.rumiyalib.inventory;

import com.yikuni.mc.rumiyalib.utils.ItemUtil;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class InventoryItem {
    private int index;
    private String name;
    private Material material;
    private List<ItemEnchant> enchants;
    private List<String> lores;
    private List<NBTPair> nbtList;
    private int count;

    public static InventoryItem fromItemStack(ItemStack itemStack){
        InventoryItem item = new InventoryItem();
        ItemMeta itemMeta = itemStack.getItemMeta();
        // 设置附魔
        itemMeta.getEnchants().forEach((enchantment, level) -> {
            item.enchants.add(new ItemEnchant(enchantment.getKey().getKey(), level));
        });

        item.lores = itemMeta.getLore();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        Set<NamespacedKey> keys = container.getKeys();
        for (NamespacedKey key: keys){
            String namespace = key.getNamespace();
            String s = container.get(key, PersistentDataType.STRING);
            item.nbtList.add(new NBTPair(namespace ,key.getKey(), s));
        }
        item.material = itemStack.getType();
        item.name = itemMeta.hasDisplayName()? itemMeta.getDisplayName(): itemMeta.getLocalizedName();
        item.index = -1;
        item.count = itemStack.getAmount();
        return item;
    }

    public static ItemStack of(InventoryItem item){
        return ItemUtil.createItem(item.name, item.material, item.enchants, item.lores, item.nbtList, item.count);
    }

    public InventoryItem() {
        enchants = new ArrayList<>();
        lores = new ArrayList<>();
        nbtList = new ArrayList<>();

    }

    public InventoryItem(int index, String name, Material material, List<ItemEnchant> enchants, List<String> lores, List<NBTPair> nbtList) {
        this.index = index;
        this.name = name;
        this.material = material;
        this.enchants = enchants;
        this.lores = lores;
        this.nbtList = nbtList;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public List<ItemEnchant> getEnchants() {
        return enchants;
    }

    public void setEnchants(List<ItemEnchant> enchants) {
        this.enchants = enchants;
    }

    public List<String> getLores() {
        return lores;
    }

    public void setLores(List<String> lores) {
        this.lores = lores;
    }

    public List<NBTPair> getNbtList() {
        return nbtList;
    }

    public void setNbtList(List<NBTPair> nbtList) {
        this.nbtList = nbtList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
