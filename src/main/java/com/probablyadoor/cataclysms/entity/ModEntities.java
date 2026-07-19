package com.probablyadoor.cataclysms.entity;

import com.probablyadoor.cataclysms.CataclysmStarscorched;
import com.probablyadoor.cataclysms.entity.custom.FrostfallProjectileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<FrostfallProjectileEntity> FROSTFALL = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(CataclysmStarscorched.MOD_ID, "frostfall"),
            EntityType.Builder.<FrostfallProjectileEntity>create(FrostfallProjectileEntity::new, SpawnGroup.MISC)
            .dimensions(0.75f, 2f).build());

    public static void registerModEntities() {
        CataclysmStarscorched.LOGGER.info("Registering Mod Entities for " + CataclysmStarscorched.MOD_ID);
    }
}
