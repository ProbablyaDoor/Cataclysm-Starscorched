package com.probablyadoor.cataclysms.sound;

import com.probablyadoor.cataclysms.CataclysmStarscorched;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class SoundRegistry {
    private SoundRegistry() {
        // private empty constructor to avoid accidental instantiation
    }

    public static final SoundEvent ITEM_DAYBREAKER_ATTACK = registerSound("daybreaker_attack");
    public static final SoundEvent ITEM_FROSTFALL_ATTACK = registerSound("frostfall_attack");
    public static final SoundEvent ITEM_MAGICGBANE_ATTACK = registerSound("magicbane_attack");
    public static final SoundEvent ITEM_FROSTFALL_THROW = registerSound("frostfall_throw");
    public static final SoundEvent ITEM_FROSTFALL_HIT = registerSound("frostfall_hit");



    // actual registration of all the custom SoundEvents
    private static SoundEvent registerSound(String id) {
        Identifier identifier = Identifier.of(CataclysmStarscorched.MOD_ID, id);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));

    }
    public static void initialize() {
        CataclysmStarscorched.LOGGER.info("Registering " + CataclysmStarscorched.MOD_ID + " Sounds");
        // Technically this method can stay empty, but some developers like to notify
        // the console, that certain parts of the mod have been successfully initialized
    }
}