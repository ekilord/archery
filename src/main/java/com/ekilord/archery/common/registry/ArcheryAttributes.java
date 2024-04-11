package com.ekilord.archery.common.registry;

import com.ekilord.archery.Archery;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ArcheryAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, Archery.MODID);

    public static void register(IEventBus modEventBus) {
        ATTRIBUTES.register(modEventBus);
    }


    public static final RegistryObject<Attribute> RANGED_DAMAGE = ATTRIBUTES.register("ranged_damage", () -> new RangedAttribute("", 0, 0.0, 1024.0));
}
