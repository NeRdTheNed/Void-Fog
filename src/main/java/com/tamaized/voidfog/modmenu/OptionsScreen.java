package com.tamaized.voidfog.modmenu;

import javax.annotation.Nullable;

import com.minelittlepony.common.client.gui.GameGui;
import com.minelittlepony.common.client.gui.element.Button;
import com.minelittlepony.common.client.gui.element.Label;
import com.minelittlepony.common.client.gui.element.Slider;
import com.minelittlepony.common.client.gui.element.Toggle;
import com.tamaized.voidfog.PointlessButtonSettings;
import com.tamaized.voidfog.Settings;
import com.tamaized.voidfog.VoidFog;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;

class OptionsScreen extends GameGui {
	
    public OptionsScreen(@Nullable Screen parent) {
        super(new TranslatableText("menu.voidfog.title"), parent);
    }

    @Override
    public void init() {
        int left =  width / 2 - 100;
        int row = height / 4 - 24;

        Settings config = VoidFog.config;
        PointlessButtonSettings pointless = VoidFog.pointlessButton;

        addButton(new Label(width / 2, 30)).setCentered().getStyle()
                .setText(getTitle().asString());

        addButton(new Slider(left, row += 35, 0, 10000, config.voidParticleDensity))
            .onChange(config::setParticleDensity)
            .setFormatter(this::formatValue);

        addButton(new Toggle(left, row += 25, config.enabled))
            .onChange(enabled -> config.enabled = enabled)
            .getStyle()
                .setText("menu.voidfog.enabled");

        addButton(new Toggle(left, row += 25, config.disableInCreative))
            .onChange(enabled -> config.disableInCreative = enabled)
            .getStyle()
                .setText("menu.voidfog.creative");

        addButton(new Toggle(left, row += 25, config.respectTorches))
            .onChange(enabled -> config.respectTorches = enabled)
            .getStyle()
                .setText("menu.voidfog.torches");

        addButton(new Toggle(left, row += 25, pointless.pointless))
            .onChange(enabled -> pointless.pointless = enabled)
            .getStyle()
                .setTooltip("menu.voidfog.bigboi.tooltip")
                .setText("menu.voidfog.bigboi");

        addButton(new Button(left, row += 44)
            .onClick(sender -> finish()))
            .getStyle()
                .setText("gui.done");
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float partialTicks) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, partialTicks);
    }

    @Override
    public void onClose() {
        VoidFog.config.save();
        VoidFog.pointlessButton.save();
        super.onClose();
    }

    private String formatValue(float value) {
        if (value <= 0) {
            return "menu.voidfog.particles.min";
        }

        if (value >= 10000) {
            return "menu.voidfog.particles.max";
        }

        if (value == 1000) {
            return "menu.voidfog.particles.default";
        }

        return I18n.translate("menu.voidfog.particles", (int)Math.floor(value));
    }
}
