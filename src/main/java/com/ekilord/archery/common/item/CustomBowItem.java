package com.ekilord.archery.common.item;

import com.ekilord.archery.Configuration;
import com.ekilord.archery.common.registry.ArcheryAttributes;
import com.ekilord.archery.common.registry.ArcheryConstants;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

import java.util.function.Predicate;

public class CustomBowItem extends ProjectileWeaponItem implements Vanishable {

    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public CustomBowItem(Properties pProperties, int pProjectileDamageModifier, float pDrawSpeedModifier) {
        super(pProperties);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(ArcheryAttributes.PROJECTILE_DAMAGE.get(), new AttributeModifier(ArcheryConstants.PROJECTILE_DAMAGE_MODIFIER_ID, "Weapon modifier", (double)pProjectileDamageModifier, AttributeModifier.Operation.ADDITION));
        builder.put(ArcheryAttributes.DRAW_SPEED.get(), new AttributeModifier(ArcheryConstants.DRAW_SPEED_MODIFIER_ID, "Weapon modifier", (double)pDrawSpeedModifier, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot pEquipmentSlot) {
        return pEquipmentSlot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(pEquipmentSlot);
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return null;
    }

    @Override
    public int getDefaultProjectileRange() {
        return 0;
    }
}
