package com.probablyadoor.cataclysms.util;

import com.probablyadoor.cataclysms.entity.custom.IceCrystalEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Tameable;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.scoreboard.AbstractTeam;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public class WeaponHelpers {

    public static void tetherParticles(ServerWorld serverWorld, ParticleEffect particleEffect, Entity target1, Entity target2, int count) {
        Vec3d startPos = target1.getPos().add(0, target1.getHeight() / 2.0, 0);
        Vec3d endPos = target2.getPos().add(0, target2.getHeight() / 2.0, 0);
        Vec3d direction = endPos.subtract(startPos);
        double distance = direction.length();
        Vec3d normalizeDirection = direction.normalize();

        for (int i = 0; i < count; i++) {
            double lerpFactor = (double) i / (count-1);
            Vec3d currentPos = startPos.add(normalizeDirection.multiply(distance * lerpFactor));
            serverWorld.spawnParticles(particleEffect, currentPos.x, currentPos.y, currentPos.z, 1, 0, 0, 0, 0.0);
        }
    }

    public static boolean isFriendlyFire (LivingEntity target, LivingEntity attacker) {
        if (target == null || attacker == null)
            return false;
        if(!isEntityBlacklisted(target, attacker))
            return false;
        if (target == attacker)
            return false;

        AbstractTeam attackerTeam = attacker.getScoreboardTeam();
        AbstractTeam targetTeam = target.getScoreboardTeam();
        if (attackerTeam !=null && targetTeam !=null && target.isTeammate(attacker)) {
            return false;
        }
        if (target instanceof Tameable tameable) {
            if (tameable.getOwner() != null) {
                if (tameable.getOwner() != attacker
                        && (tameable.getOwner() instanceof PlayerEntity ownerPlayer)
                        && attacker instanceof PlayerEntity playerEntity) {
                    return playerEntity.shouldDamagePlayer(ownerPlayer);
                }
                return tameable.getOwner() != attacker;
            }
            return true;
        }
        return true;
    }



    public static boolean isEntityBlacklisted(LivingEntity targeted, LivingEntity player) {
        if (targeted == null || player == null) {
            return false;
        }
        return !(targeted instanceof ArmorStandEntity)
                && !(targeted instanceof IceCrystalEntity);

    }
}
