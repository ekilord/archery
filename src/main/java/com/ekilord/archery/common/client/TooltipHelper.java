package com.ekilord.archery.common.client;

import com.ekilord.archery.common.registry.ArcheryAttributes;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.world.item.ItemStack.ATTRIBUTE_MODIFIER_FORMAT;

public class TooltipHelper {
    public static void updateTooltip(ItemStack itemStack, List<Component> tooltip) {
        if (itemStack.getItem() instanceof ProjectileWeaponItem) {
            mergeTooltips(tooltip);
            //replaceAttributeLines_BlueWithGreen(lines);
        }
    }

    private static void mergeTooltips(List<Component> tooltip) {
        tooltip.add(Component.literal("\n"));
        tooltip.add(Component.translatable("item.modifiers.main_hand").withStyle(ChatFormatting.GRAY));
    }

    private static void mergeAttributeLines_MainHandOffHand(List<Component> tooltip) {
        System.out.println("Merging attribute lines");
        List<Component> heldInHandLines = new ArrayList<>();
        List<Component> mainHandAttributes = new ArrayList<>();
        List<Component> offHandAttributes = new ArrayList<>();
        for (int i = 0; i < tooltip.size(); i++) {
            var line = tooltip.get(i);
            var content = line.getContents();
            if (content instanceof TranslatableContents translatableText) {
                if (translatableText.getKey().startsWith("item.modifiers")) {
                    heldInHandLines.add(line);
                }
                if (translatableText.getKey().startsWith("attribute.modifier")) {
                    if (heldInHandLines.size() == 1) {
                        mainHandAttributes.add(line);
                    }
                    if (heldInHandLines.size() == 2) {
                        offHandAttributes.add(line);
                    }
                }
            }
        }
        if(heldInHandLines.size() == 2) {
            var mainHandLine = tooltip.indexOf(heldInHandLines.get(0));
            var offHandLine = tooltip.indexOf(heldInHandLines.get(1));
            tooltip.remove(mainHandLine);
            tooltip.add(mainHandLine, Component.translatable("item.modifiers.both_hands").withStyle(ChatFormatting.GRAY));
            tooltip.remove(offHandLine);
            for (var offhandAttribute: offHandAttributes) {
                if(mainHandAttributes.contains(offhandAttribute)) {
                    tooltip.remove(tooltip.lastIndexOf(offhandAttribute));
                }
            }

            var lastIndex = tooltip.size() - 1;
            var lastLine = tooltip.get(lastIndex);
            if (lastLine.getString().isEmpty()) {
                tooltip.remove(lastIndex);
            }
        }
    }

    private static void replaceAttributeLines_BlueWithGreen(List<Component> tooltip) {
        System.out.println("Replacing attribute lines");
        var attributeTranslationKey = ArcheryAttributes.RANGED_DAMAGE.get().getDescriptionId();
        for (int i = 0; i < tooltip.size(); i++)  {
            var line = tooltip.get(i);
            var content = line.getContents();
//            System.out.println(i + ": " + content + " " + line.getClass());
            if (content instanceof TranslatableContents translatable) {
                var isProjectileAttributeLine = false;
                var attributeValue = 0.0;
//                System.out.println("Is translatable content");
                if (translatable.getKey().startsWith("attribute.modifier.plus.0")) { // `.0` suffix for addition
//                    System.out.println("Is attribute line");
                    for (var arg: translatable.getArgs()) {
//                        System.out.println("Sub-content type: " + arg.getClass());
                        if (arg instanceof String string) {
                            try {
                                var number = Double.valueOf(string);
                                attributeValue = number;
                            } catch (Exception ignored) { }
                        }
                        if (arg instanceof Component attributeText) {
                            if (attributeText.getContents() instanceof TranslatableContents attributeTranslatable) {
//                                System.out.println("Translatable sub-content: " + arg);
                                if (attributeTranslatable.getKey().startsWith(attributeTranslationKey)) {
//                                    System.out.println("Projectile attribute found");
                                    isProjectileAttributeLine = true;
                                }
                            }
                        }
                    }
                }

                if (isProjectileAttributeLine && attributeValue > 0) {
                    // The construction of this line is copied from ItemStack.class
                    var greenAttributeLine = Component.literal(" ")
                            .append(
                                    Component.translatable("attribute.modifier.equals." + AttributeModifier.Operation.ADDITION,
                                            ATTRIBUTE_MODIFIER_FORMAT.format(attributeValue), Component.translatable(attributeTranslationKey))
                            )
                            .withStyle(ChatFormatting.DARK_GREEN);
                    tooltip.set(i, greenAttributeLine);
                }
            }
        }
    }
}