package com.probablyadoor.cataclysms.util;

import com.probablyadoor.cataclysms.entity.custom.IceCrystalEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Tameable;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.scoreboard.AbstractTeam;

public class FriendlyFireCheck {

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
