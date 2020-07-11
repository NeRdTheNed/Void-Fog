package com.tamaized.voidfog.modmenu;

import net.minecraft.client.gui.screen.Screen;

import java.util.function.Function;

import io.github.prospector.modmenu.api.ModMenuApi;

public class VFModMenuFactory implements ModMenuApi {

    @Override
    public String getModId() {
        return "worsevoidfog";
    }

    @Override
    public Function<Screen, ? extends Screen> getConfigScreenFactory() {
        return OptionsScreen::new;
    }
}
