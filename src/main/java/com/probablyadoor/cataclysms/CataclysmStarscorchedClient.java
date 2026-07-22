package com.probablyadoor.cataclysms;

import com.probablyadoor.cataclysms.entity.ModEntities;
import com.probablyadoor.cataclysms.entity.client.FrostfallProjectileModel;
import com.probablyadoor.cataclysms.entity.client.FrostfallProjectileRenderer;
import com.probablyadoor.cataclysms.entity.client.IceCrystalModel;
import com.probablyadoor.cataclysms.entity.client.IceCrystalRenderer;
import com.probablyadoor.cataclysms.item.ModItems;
import com.probablyadoor.cataclysms.item.custom.NightveilItem;
import com.probablyadoor.cataclysms.keybind.ModKeyBinds;
import com.probablyadoor.cataclysms.particle.ModParticles;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class CataclysmStarscorchedClient implements ClientModInitializer {


    @Override
    public void onInitializeClient() {
        ModKeyBinds.registerKeys();
        ModParticles.registerParticleFactories();

        EntityModelLayerRegistry.registerModelLayer(FrostfallProjectileModel.FROSTFALL, FrostfallProjectileModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.FROSTFALL, FrostfallProjectileRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(IceCrystalModel.ICE_CRYSTAL, IceCrystalModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.ICE_CRYSTAL, IceCrystalRenderer::new);

        registerRangedWeaponPredicates(ModItems.NIGHTVEIL);
        //System.out.println(ModItems.NIGHTVEIL instanceof CrossbowItem);
    }
    public static void registerRangedWeaponPredicates(Item item) {
        ModelPredicateProviderRegistry.register(item, Identifier.of("pull"), (itemStack, clientWorld, livingEntity, seed) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else if (item instanceof NightveilItem crossbow) {
                return calculateDrawSpeed(itemStack, livingEntity, crossbow.getDrawSpeed());
            }
            return 0.0F;
        });


        if (item instanceof CrossbowItem) {
            ModelPredicateProviderRegistry.register(item, Identifier.of("pulling"), (itemStack, clientWorld, livingEntity, seed) -> {
                if (livingEntity == null) {
                    return 0.0F;
                } else {
                    return livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack && !NightveilItem.isCharged(itemStack) ? 1.0F : 0.0F;
                }
            });

            ModelPredicateProviderRegistry.register(item, Identifier.of("charged"), (itemStack, clientWorld, livingEntity, seed) -> {
                if (livingEntity == null) {
                    return 0.0F;
                } else {
                    return NightveilItem.isCharged(itemStack) ? 1.0F : 0.0F;
                }
            });

            ModelPredicateProviderRegistry.register(item, Identifier.of("firework"), (itemStack, clientWorld, livingEntity, seed) -> {
                if (livingEntity == null) {
                    return 0.0F;
                } else {
                    return NightveilItem.isCharged(itemStack) && NightveilItem.hasProjectile(itemStack,
                            Items.FIREWORK_ROCKET) ? 1.0F : 0.0F;
                }
            });
        }
    }
    private static float calculateDrawSpeed(ItemStack itemStack, LivingEntity livingEntity, float drawSpeed) {
        int useTicks = itemStack.getMaxUseTime(livingEntity) - livingEntity.getItemUseTimeLeft();
        if (itemStack.getItem() instanceof NightveilItem) {
            if (NightveilItem.isCharged(itemStack)) {
                return 0.0F;
            }

            return (float) useTicks / drawSpeed;
        }

        return 0.0F;
    }
}
