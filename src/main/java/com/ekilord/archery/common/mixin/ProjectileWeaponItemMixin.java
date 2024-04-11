package com.ekilord.archery.common.mixin;

import com.ekilord.archery.common.ArcheryConstants;
import com.ekilord.archery.common.registry.ArcheryAttributes;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ProjectileWeaponItem;
import org.spongepowered.asm.mixin.Mixin;

import java.util.ArrayList;
import java.util.List;

@Mixin(ProjectileWeaponItem.class)
public class ProjectileWeaponItemMixin {
    private Multimap<Attribute, AttributeModifier> attributeModifiers = null;
    private List<EquipmentSlot> allowedSlots = List.of(EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND);
    private Double customLaunchVelocity = null;

    // Helper, not actual source of truth
    private double projectileDamage = 0;
    public void setProjectileDamage(double value, boolean mainHand, boolean offHand) {
        allowedSlots = new ArrayList<>();
        if (mainHand) {
            allowedSlots.add(EquipmentSlot.MAINHAND);
        }
        if (offHand) {
            allowedSlots.add(EquipmentSlot.OFFHAND);
        }
        projectileDamage = value;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(ArcheryAttributes.RANGED_DAMAGE.get(), new AttributeModifier(ArcheryConstants.RANGED_MODIFIER_ID, "Ranged damage", (double)value, AttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }

    public double getProjectileDamage() {
        return projectileDamage;
    }

    public void setCustomLaunchVelocity(Double value) {
        customLaunchVelocity = value;
    }

    public Double getCustomLaunchVelocity() {
        return customLaunchVelocity;
    }
}
