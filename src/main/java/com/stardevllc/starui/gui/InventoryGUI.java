package com.stardevllc.starui.gui;

import com.stardevllc.starui.element.Element;
import com.stardevllc.starui.element.FillerElement;
import com.stardevllc.starui.element.button.Button;
import com.stardevllc.starui.handler.InventoryHandler;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class InventoryGUI implements InventoryHandler {

    protected final Inventory inventory;
    protected final Slot[] slots;
    protected final int rows;
    protected UUID playerUUID;

    public InventoryGUI(int rows, String title, UUID player) {
        this.playerUUID = player;
        this.rows = rows;
        if (rows < 1 || rows > 6) {
            throw new IllegalArgumentException("Rows value is out of range, it can only be between 1 to 6.");
        }

        this.inventory = Bukkit.createInventory(null, rows * 9, ChatColor.translateAlternateColorCodes('&', title));

        this.slots = new Slot[rows * 9];
        for (int i = 0; i < this.slots.length; i++) {
            slots[i] = new Slot(i);
        }
    }

    public InventoryGUI(int rows, String title) {
        this(rows, title, null);
    }

    public void createItems() {

    }

    public void update() {
        if (this.playerUUID == null) {
            return;
        }

        Player player = Bukkit.getPlayer(this.playerUUID);
        if (player == null) {
            return;
        }

        for (Slot slot : this.slots) {
            if (slot.getElement() != null) {
                if (!slot.getElement().isDeleteOnUpdate()) {
                    continue;
                }
            }
            slot.setElement(null);
        }

        createItems();
        decorate(player);
    }

    public void setElement(int index, Element element) {
        Slot slot = this.slots[index];
        if (slot != null) {
            slot.setElement(element);
        }
    }

    public void setElement(int row, int column, Element element) {
        setSlotValue(row, column, element);
    }

    public void removeElement(int row, int column) {
        setSlotValue(row, column, null);
    }

    public void addElement(Element element) {
        for (Slot slot : this.slots) {
            if (slot.getElement() == null) {
                slot.setElement(element);
                break;
            } else if (slot.getElement() instanceof FillerElement fillerElement) {
                if (fillerElement.isReplaceable()) {
                    slot.setElement(element);
                }
            }
        }
    }

    private void setSlotValue(int row, int column, Element value) {
        if (row < 0 || row >= this.rows) {
            throw new IllegalArgumentException("Invalid row value. Supports 0-" + (this.rows - 1));
        }

        if (column < 0 || column > 8) {
            throw new IllegalArgumentException("Invalid column value. Supports 0-8");
        }

        Slot slot = slots[row * 9 + column];
        slot.setElement(value);
    }

    public void decorate(Player player) {
        for (int index = 0; index < this.slots.length; index++) {
            Element element = slots[index].getElement();
            if (element != null) {
                this.inventory.setItem(index, element.getIconCreator().apply(player));
            } else {
                this.inventory.setItem(index, null);
            }
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public void onClick(InventoryClickEvent e) {
        if (e.getRawSlot() < 0) {
            return;
        }

        if (e.getSlot() != e.getRawSlot()) {
            return;
        }

        if (e.getSlot() != e.getRawSlot()) {
            return;
        }

        if (this.slots.length < e.getRawSlot()) {
            return;
        }

        e.setCancelled(true); //TODO This will have to be changed when the InsertElement is implemented

        Slot slot = this.slots[e.getRawSlot()];
        if (slot == null) {
            return;
        }

        if (slot.getElement() == null) {
            return;
        }

        if (slot.getElement() instanceof Button button) {
            if (button.getEventConsumer() != null) {
                button.getEventConsumer().accept(e);
                button.playSound((Player) e.getWhoClicked());
            }
        }
    }

    @Override
    public void onDrag(InventoryDragEvent e) {

    }

    @Override
    public void onOpen(InventoryOpenEvent e) {
        decorate((Player) e.getPlayer());
    }

    @Override
    public void onClose(InventoryCloseEvent e) {

    }
}
