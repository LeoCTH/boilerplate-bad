package com.leocth.boilerplatebad.block;

import com.google.common.collect.ImmutableList;
import com.leocth.boilerplatebad.Generator;
import com.leocth.boilerplatebad.RegistrableResults;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.devtech.arrp.json.blockstate.JState;
import net.devtech.arrp.json.loot.JLootTable;
import net.devtech.arrp.json.models.JModel;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.Contract;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static net.devtech.arrp.json.blockstate.JState.variant;
import static net.devtech.arrp.json.models.JModel.textures;

public class BlockGenerator extends Generator<Block, BlockGenerator> {

    private Function<Identifier, JState> blockStateGetter;
    private Function<Identifier, JModel> modelGetter;
    private Function<Identifier, JLootTable> lootTableGetter;
    private Function<Identifier, Block> blockGetter;
    private List<Identifier> ids;

    public BlockGenerator(String modid, RuntimeResourcePack resourcePack) {
        super(modid, resourcePack);
    }

    @Contract(value = "_ -> this", mutates = "this")
    public BlockGenerator setIds(List<Identifier> ids) {
        this.ids = ImmutableList.copyOf(ids);
        return this;
    }

    @Contract(value = "_ -> this", mutates = "this")
    public BlockGenerator setIds(Identifier... ids) {
        this.ids = ImmutableList.copyOf(ids);
        return this;
    }

    @Contract(value = "_ -> this", mutates = "this")
    public BlockGenerator setIds(String... paths) {
        //noinspection UnstableApiUsage
        this.ids = Arrays.stream(paths)
                    .map(s -> new Identifier(namespace, s))
                    .collect(ImmutableList.toImmutableList());
        return this;
    }

    @Contract(value = "_ -> this", mutates = "this")
    public BlockGenerator blockState(Function<Identifier, JState> getter) {
        this.blockStateGetter = getter;
        return this;
    }

    @Contract(value = "_ -> this", mutates = "this")
    public BlockGenerator model(Function<Identifier, JModel> getter) {
        this.modelGetter = getter;
        return this;
    }

    @Contract(value = "_ -> this", mutates = "this")
    public BlockGenerator lootTable(Function<Identifier, JLootTable> getter) {
        this.lootTableGetter = getter;
        return this;
    }

    @Contract(value = "_ -> this", mutates = "this")
    public BlockGenerator block(Function<Identifier, Block> getter) {
        this.blockGetter = getter;
        return this;
    }

    @Contract(value = "_ -> this", mutates = "this")
    public BlockGenerator block(Block block) {
        this.blockGetter = ignored -> block;
        return this;
    }

    @Contract(value = "_ -> this", mutates = "this")
    public BlockGenerator block(AbstractBlock.Settings settings) {
        return this.block(new Block(settings));
    }

    public BlockGenerator applyPreset(BlockGeneratorPreset preset) {
        this.modelGetter = preset;
    }

    @Override
    public RegistrableResults<Block> build() {
        return null;
    }


    /**
     * Registers a block that is mostly uninteresting, like a true decoration block
     * hanging there in the backyard.
     */
    public <T extends Block> T registerBlandBlock(T block, String path) {
        Identifier id = new Identifier(namespace, path);
        Registry.register(Registry.BLOCK, id, block);
        Registry.register(Registry.ITEM, id, new BlockItem(block, new FabricItemSettings()));

        resourcePack.addBlockState(
                JState.state()
                        .add(variant()
                                .put("", JState.model(prefixPathAsString(id, "block")))
                        ),
                id
        );

        resourcePack.addModel(
                JModel.model("minecraft:block/cube_all")
                        .textures(textures()
                                .var("all", prefixPathAsString(id, "block"))
                        ),
                prefixPath(id, "block")
        );
        resourcePack.addModel(
                JModel.model(prefixPathAsString(id, "block")),
                prefixPath(id, "item")
        );

        return block;
    }
}
