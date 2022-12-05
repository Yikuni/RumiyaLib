package com.yikuni.mc.rumiyalib.inventory;

public class ItemEnchant {
    private String enchantment;
    private int level;

    public ItemEnchant() {
    }

    public ItemEnchant(String enchantment, int level) {
        this.enchantment = enchantment;
        this.level = level;
    }

    public String getEnchantment() {
        return enchantment;
    }

    public void setEnchantment(String enchantment) {
        this.enchantment = enchantment;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
