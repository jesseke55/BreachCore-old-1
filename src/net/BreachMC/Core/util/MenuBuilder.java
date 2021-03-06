package net.BreachMC.Core.util;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Jesse on 21-11-2015.
 */

public class MenuBuilder {

    private final Inventory inv;

    public MenuBuilder(final int size, final String title) {
        inv = Bukkit.createInventory(null, size, title);
    }

    public MenuBuilder addItem(final int slot, final ItemStack stack) {
        inv.setItem(slot, stack);
        return this;
    }

    public Inventory getInventory() {
        return inv;
    }
}

