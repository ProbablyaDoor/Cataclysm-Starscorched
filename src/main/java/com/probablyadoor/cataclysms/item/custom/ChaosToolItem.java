package com.probablyadoor.cataclysms.item.custom;

import com.probablyadoor.cataclysms.block.ModBlocks;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;

import java.util.function.Predicate;

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
            FireballEntity fireballEntity = new FireballEntity(world, user, user.getPos(), 5);
            fireballEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
            fireballEntity.setPos(user.getX(), user.getY()+1, user.getZ());
            fireballEntity.setItem(new ItemStack(Items.ENDER_PEARL));
            world.spawnEntity(fireballEntity);
        }
        return TypedActionResult.success(itemStack, world.isClient());
    }


}
