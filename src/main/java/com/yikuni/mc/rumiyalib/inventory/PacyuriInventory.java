package com.yikuni.mc.rumiyalib.inventory;

import com.yikuni.mc.rumiyalib.sender.ConsoleSender;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PacyuriInventory {
    private String namespace;
    private String title;
    private List<InventoryItem> items;
    private int size;

    public static PacyuriInventory from(Inventory inventory, String title, String namespace){
        PacyuriInventory pacyuriInventory = new PacyuriInventory();
        pacyuriInventory.size = inventory.getSize();
        pacyuriInventory.title = title;
        pacyuriInventory.namespace = namespace;
        int i = 0;
        for (ItemStack itemStack: inventory){
            if (itemStack != null){
                InventoryItem item = InventoryItem.fromItemStack(itemStack);
                item.setIndex(i);
                pacyuriInventory.items.add(item);
            }
            i++;
        }
        return pacyuriInventory;
    }

    public static Inventory of(PacyuriInventory pacyuriInventory){
        Inventory inventory = Bukkit.createInventory(null, pacyuriInventory.size, pacyuriInventory.title);
        for (InventoryItem item: pacyuriInventory.items){
            ItemStack itemStack = InventoryItem.of(item);
            ConsoleSender.INSTANCE.info("加载的物品的数量: " + itemStack.getAmount());
            inventory.setItem(item.getIndex(), itemStack);
            ConsoleSender.INSTANCE.info("加载了物品: " + item.getMaterial().name() + " : index = " + item.getIndex());
        }
        ConsoleSender.INSTANCE.info("背包是否是空: " + inventory.isEmpty());

        return inventory;
    }

    public PacyuriInventory() {
        size = 9;
        items = new ArrayList<>();
    }

    public PacyuriInventory(String namespace, String title, List<InventoryItem> items, int size) {
        this.namespace = namespace;
        this.title = title;
        this.items = items;
        this.size = size;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<InventoryItem> getItems() {
        return items;
    }

    public void setItems(List<InventoryItem> items) {
        this.items = items;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
