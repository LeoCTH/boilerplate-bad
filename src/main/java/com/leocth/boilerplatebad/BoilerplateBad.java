package com.leocth.boilerplatebad;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.Item;

public class BoilerplateBad implements ModInitializer {

    private final BoilerplateGenerator generator = new BoilerplateGenerator("boilerplatebad");
    public static Block THONK_BLOCK;
    public static Item EXTRA_THONK_ITEM;

    @Override
    public void onInitialize() {
        THONK_BLOCK = generator.block.registerBlandBlock(new Block(FabricBlockSettings.of(Material.METAL)), "thonk");
        EXTRA_THONK_ITEM = generator.item.registerBlankItem("extra_thonk");
    }
}
