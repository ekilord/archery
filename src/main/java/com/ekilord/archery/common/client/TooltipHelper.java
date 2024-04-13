package com.ekilord.archery.common.client;

import com.ekilord.archery.common.registry.ArcheryAttributes;
import com.ekilord.archery.common.registry.ArcheryItems;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import java.util.List;
import java.util.Map;

import static net.minecraft.world.item.ItemStack.ATTRIBUTE_MODIFIER_FORMAT;

public class  TooltipHelper {
    public static void updateTooltip(LivingEntity pEntity, ItemStack pItemStack, List<Component> pTooltip) {
        if (pItemStack.getItem() instanceof ProjectileWeaponItem) {
            mergeTooltips(pEntity, pItemStack, pTooltip);
        }
    }

    private static void mergeTooltips(LivingEntity pEntity, ItemStack pItemStack, List<Component> pTooltip) {
        EquipmentSlot equipmentSlot = LivingEntity.getEquipmentSlotForItem(pItemStack);
        Multimap<Attribute, AttributeModifier> modifierMultimap = pItemStack.getAttributeModifiers(equipmentSlot);
        if (!modifierMultimap.isEmpty()) {
            pTooltip.add(CommonComponents.EMPTY);
            pTooltip.add(Component.translatable("item.modifiers." + equipmentSlot.getName()).withStyle(ChatFormatting.GRAY));

            for (Map.Entry<Attribute, AttributeModifier> entry : modifierMultimap.entries()) {
                AttributeModifier attributeModifier = entry.getValue();
                double currentAttribute = attributeModifier.getAmount();
                boolean modifiesThis = false;
                if (pEntity != null) {
                    if (attributeModifier.getId() == ArcheryItems.RANGED_DAMAGE_MODIFIER_ID) {
                        currentAttribute += pEntity.getAttributeBaseValue(ArcheryAttributes.RANGED_DAMAGE.get());
                        currentAttribute += (double) EnchantmentHelper.getDamageBonus(pItemStack, MobType.UNDEFINED);
                        modifiesThis = true;
                    } else if (attributeModifier.getId() == ArcheryItems.DRAW_SPEED_MODIFIER_ID) {
                        currentAttribute += pEntity.getAttributeBaseValue(ArcheryAttributes.DRAW_SPEED.get());
                        modifiesThis = true;
                    }
                }

                double modifiedCurrentAttribute;
                if (attributeModifier.getOperation() != AttributeModifier.Operation.MULTIPLY_BASE && attributeModifier.getOperation() != AttributeModifier.Operation.MULTIPLY_TOTAL) {
                    if (entry.getKey().equals(Attributes.KNOCKBACK_RESISTANCE)) {
                        modifiedCurrentAttribute = currentAttribute * 10.0D;
                    } else {
                        modifiedCurrentAttribute = currentAttribute;
                    }
                } else {
                    modifiedCurrentAttribute = currentAttribute * 100.0D;
                }

                if (modifiesThis) {
                    pTooltip.add(CommonComponents.space().append(Component.translatable("attribute.modifier.equals." + attributeModifier.getOperation().toValue(), ATTRIBUTE_MODIFIER_FORMAT.format(modifiedCurrentAttribute), Component.translatable(entry.getKey().getDescriptionId()))).withStyle(ChatFormatting.DARK_GREEN));
                } else if (currentAttribute > 0.0D) {
                    pTooltip.add(Component.translatable("attribute.modifier.plus." + attributeModifier.getOperation().toValue(), ATTRIBUTE_MODIFIER_FORMAT.format(modifiedCurrentAttribute), Component.translatable(entry.getKey().getDescriptionId())).withStyle(ChatFormatting.BLUE));
                } else if (currentAttribute < 0.0D) {
                    modifiedCurrentAttribute *= -1.0D;
                    pTooltip.add(Component.translatable("attribute.modifier.take." + attributeModifier.getOperation().toValue(), ATTRIBUTE_MODIFIER_FORMAT.format(modifiedCurrentAttribute), Component.translatable(entry.getKey().getDescriptionId())).withStyle(ChatFormatting.RED));
                }
            }
        }
    }
}