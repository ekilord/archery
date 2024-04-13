package com.ekilord.archery.common.registry;

import com.ekilord.archery.Archery;
import com.ekilord.archery.common.item.CustomBowItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.UUID;

public class ArcheryItems {
    public static final UUID RANGED_DAMAGE_MODIFIER_ID = UUID.fromString("f39e5e0c-8117-4eff-80cc-b955c3826618");
    public static final UUID DRAW_SPEED_MODIFIER_ID = UUID.fromString("12a777ae-3802-4fbe-8251-e39080d084ff");

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Archery.MODID);

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }

    //ITEMS
    public static final RegistryObject<Item> WOODEN_SHORTBOW = ITEMS.register("wooden_shortbow", () -> new CustomBowItem(new Item.Properties(), 10, 1.5f));
}
