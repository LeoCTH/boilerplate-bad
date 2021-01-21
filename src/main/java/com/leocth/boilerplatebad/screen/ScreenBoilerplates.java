package com.leocth.boilerplatebad.screen;

import net.minecraft.inventory.Inventory;
import net.minecraft.screen.slot.Slot;

import java.util.function.Consumer;

public class ScreenBoilerplates {
    /**
     * The default visual dimension of an item slot. (18x18)
     */
    public static final int SLOT_DIMENSIONS = 18;

    /**
     * Mass-add a bunch of slots.
     *
     * @param inventory the inventory
     * @param x the initial x position
     * @param y the initial y position
     * @param rows number of rows
     * @param columns number of columns
     * @param startIndex the initial index of the inventory for the slots; use 0 for most cases
     * @param slotSizeX the x component of the dimensions of each slot; defaults to 18
     * @param slotSizeY the y component of the dimensions of each slot; defaults to 18
     * @param addSlotOp use a method reference to ScreenHandler#addSlot
     */
    public static void addSlots(
            Inventory inventory,
            int x, int y,
            int rows, int columns,
            int startIndex,
            int slotSizeX, int slotSizeY,
            Consumer<Slot> addSlotOp
    ) {
        int originalX = x;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                addSlotOp.accept(new Slot(inventory, startIndex, x, y));
                startIndex++;
                x += slotSizeX;
            }
            x = originalX;
            y += slotSizeY;
        }
    }

    /**
     * Behaves exactly like {@link #addSlots(Inventory, int, int, int, int, int, int, Consumer)},
     * except {@code slotSizeX} and {@code slotSizeY} are both set to {@code slotSize}.
     */
    public static void addSlots(
            Inventory inventory,
            int x, int y,
            int rows, int columns,
            int startIndex,
            int slotSize,
            Consumer<Slot> addSlotOp
    ) {
        addSlots(inventory, x, y, rows, columns, startIndex, slotSize, slotSize, addSlotOp);
    }
}
