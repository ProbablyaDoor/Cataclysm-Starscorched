package com.probablyadoor.cataclysms.item.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class ChaosToolItem extends Item {

    public ChaosToolItem(Settings settings) {super(settings);}

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(
                null,
                user.getX(),
                user.getY(),
                user.getZ(),
                SoundEvents.ENTITY_SHULKER_BULLET_HIT,
                SoundCategory.NEUTRAL,
                0.5F,
                0.8F / (world.getRandom().nextFloat() * 0.8F + 1.6F)
        );
        if (!world.isClient) {
            LightningEntity lightningEntity = new LightningEntity(world, user, user.pos())
            world.spawnEntity(LightningEntity);
        }
        return TypedActionResult.success(itemStack, world.isClient());
    }
}
