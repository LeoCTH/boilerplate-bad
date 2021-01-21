package com.leocth.boilerplatebad;

import net.devtech.arrp.api.RuntimeResourcePack;
import net.minecraft.util.Identifier;

public abstract class Generator<T, This extends Generator<T, This>> {

    public final String namespace;
    protected final RuntimeResourcePack resourcePack;

    public Generator(String namespace, RuntimeResourcePack resourcePack) {
        this.namespace = namespace;
        this.resourcePack = resourcePack;
    }

    public abstract RegistrableResults<T> build();

    @SuppressWarnings("unchecked")
    public This buildAndOutput(RegistrableResults<T> to) {
        to.putAll(this.build());
        return (This)this;
    }

    protected static String prefixPathAsString(Identifier id, String prefix) {
        return id.getNamespace() + ":" + prefix + "/" + id.getPath();
    }

    protected static Identifier prefixPath(Identifier id, String prefix) {
        return new Identifier(id.getNamespace(), prefix + "/" + id.getPath());
    }
}
