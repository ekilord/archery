package com.ekilord.archery.common.client;

import com.ekilord.archery.Archery;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Archery.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void onToolTip(ItemTooltipEvent event) {
        TooltipHelper.updateTooltip(event.getEntity(), event.getItemStack(), event.getToolTip());
    }
}
