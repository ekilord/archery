package com.ekilord.archery;

import com.ekilord.archery.Archery;
import com.google.common.collect.ImmutableList;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = Archery.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Configuration
{
    public static ForgeConfigSpec COMMON_CONFIG;
    public static int ARROW_BASE_DAMAGE = 2;
    public static ForgeConfigSpec CLIENT_CONFIG;

    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

        COMMON_CONFIG = COMMON_BUILDER.build();

        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }
}
