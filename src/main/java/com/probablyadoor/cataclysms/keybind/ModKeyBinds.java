package com.probablyadoor.cataclysms.keybind;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ModKeyBinds {
    public static final KeyBinding C_KEYBIND = KeyBindingHelper.registerKeyBinding(
            new KeyBinding("key.cataclysms.primaryatk", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_C, "key.categories.misc")
    );
    public static final KeyBinding V_KEYBIND = KeyBindingHelper.registerKeyBinding(
            new KeyBinding("key.cataclysms.secondaryatk", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_V, "key.categories.misc")
    );


    public static void registerKeys() {

    }
}
