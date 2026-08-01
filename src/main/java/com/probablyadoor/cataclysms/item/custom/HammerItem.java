package com.probablyadoor.cataclysms.item.custom;

import com.probablyadoor.cataclysms.entity.custom.FrostfallProjectileEntity;
import com.probablyadoor.cataclysms.sound.SoundRegistry;
import mod.chloeprime.aaaparticles.api.common.AAALevel;
import mod.chloeprime.aaaparticles.api.common.ParticleEmitterInfo;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class HammerItem extends MiningToolItem {
    private static final ParticleEmitterInfo FROSTAURA = new ParticleEmitterInfo(Identifier.of("cataclysms", "frostaura")).scale(0.45F, 0.45F, 0.45F);

    public HammerItem(ToolMaterial material, Settings settings) {
        super(material, BlockTags.PICKAXE_MINEABLE, settings);
    }

    public static List<BlockPos> getBlocksToBeDestroyed(int range, BlockPos initalBlockPos, ServerPlayerEntity player) {
        List<BlockPos> positions = new ArrayList<>();
        HitResult hit = player.raycast(20, 0, false);
        if (hit.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockHit = (BlockHitResult) hit;

            if(blockHit.getSide() == Direction.DOWN || blockHit.getSide() == Direction.UP) {
                for(int x = -range; x <= range; x++) {
                    for(int y = -range; y <= range; y++) {
                        positions.add(new BlockPos(initalBlockPos.getX() + x, initalBlockPos.getY(), initalBlockPos.getZ() + y));
                    }
                }
            }

            if(blockHit.getSide() == Direction.NORTH || blockHit.getSide() == Direction.SOUTH) {
                for(int x = -range; x <= range; x++) {
                    for(int y = -range; y <= range; y++) {
                        positions.add(new BlockPos(initalBlockPos.getX() + x, initalBlockPos.getY() + y, initalBlockPos.getZ()));
                    }
                }
            }

            if(blockHit.getSide() == Direction.EAST || blockHit.getSide() == Direction.WEST) {
                for(int x = -range; x <= range; x++) {
                    for(int y = -range; y <= range; y++) {
                        positions.add(new BlockPos(initalBlockPos.getX(), initalBlockPos.getY() + y, initalBlockPos.getZ() + x));
                    }
                }
            }
        }

        return positions;


    }
    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return !miner.isCreative();
    }
    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, EquipmentSlot.MAINHAND);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (!world.isClient) {
            if (entity instanceof LivingEntity user) {
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 220, 0, false, false));
            }
        }
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World world = target.getWorld();
        if (!world.isClient && world instanceof ServerWorld serverWorld) {
            world.playSound(
                    null,
                    attacker.getX(),
                    attacker.getY(),
                    attacker.getZ(),
                    SoundRegistry.ITEM_FROSTFALL_ATTACK,
                    SoundCategory.NEUTRAL,
                    0.5F,
                    0.8F / (world.getRandom().nextFloat() * 0.8F + 1.6F));
        }
        return true;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPEAR;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        user.setCurrentHand(hand);
        return TypedActionResult.consume(user.getStackInHand(hand));
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 72000;
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (!world.isClient && world instanceof ServerWorld serverWorld) {
            AAALevel.addParticle(serverWorld, false, FROSTAURA.clone().position(user.getX(), user.getY()+0.1, user.getZ()));
        }
    }

    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int ticksUsed) {
        if (user instanceof PlayerEntity playerEntity) {
            int chargeTicks = this.getMaxUseTime(stack, user) - ticksUsed;

            if (chargeTicks >= 10) {
                if (!world.isClient) {
                        world.playSound(
                                null,
                                user.getX(),
                                user.getY(),
                                user.getZ(),
                                SoundRegistry.ITEM_FROSTFALL_THROW,
                                SoundCategory.NEUTRAL,
                                0.5F,
                                0.8F / (world.getRandom().nextFloat() * 0.8F + 1.6F)
                        );
                        FrostfallProjectileEntity frostfall = new FrostfallProjectileEntity(world, playerEntity);
                        frostfall.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f, 1.5f, 0f);
                        world.spawnEntity(frostfall);
                        frostfall.owner = playerEntity;
                        playerEntity.getItemCooldownManager().set(this, 50);
                }
            }
        }
    }
}
