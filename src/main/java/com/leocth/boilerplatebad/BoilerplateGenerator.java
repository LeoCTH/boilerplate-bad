package com.leocth.boilerplatebad;

import com.leocth.boilerplatebad.block.BlockGenerator;
import com.leocth.boilerplatebad.item.ItemGenerator;
import net.devtech.arrp.api.RRPCallback;
import net.devtech.arrp.api.RuntimeResourcePack;

import java.util.Random;

public final class BoilerplateGenerator {

    private final String modid;
    private final RuntimeResourcePack resourcePack;
    public final BlockGenerator block;
    public final ItemGenerator item;

    public BoilerplateGenerator(String modid) {
        this(modid, RuntimeResourcePack.create(modid + ":boilerplate_generator_" + generateRandomId()));
    }

    public BoilerplateGenerator(String modid, RuntimeResourcePack resourcePack) {
        this.modid = modid;
        this.resourcePack = resourcePack;
        RRPCallback.EVENT.register(a -> a.add(resourcePack));
        block = new BlockGenerator(modid, resourcePack);
        item = new ItemGenerator(modid, resourcePack);
    }

    private static String generateRandomId() {
        return String.format("%08x", new Random().nextInt());
    }

    public RuntimeResourcePack getResourcePack() {
        return resourcePack;
    }
}
