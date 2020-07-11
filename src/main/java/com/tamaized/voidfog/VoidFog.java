package com.tamaized.voidfog;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;

public class VoidFog implements ClientModInitializer {

	public static final Logger LOGGER = LogManager.getLogger("worsevoidfog");

	public static final FogParticleSpawner particleSpawner = new FogParticleSpawner();
	//public static final FogColor fogColor = new FogColor();
	//public static final FogRenderer renderer = new FogRenderer();

	public static Settings config = new Settings();
	public static PointlessButtonSettings pointlessButton = new PointlessButtonSettings();

    @Override
    public void onInitializeClient() {
        config = Settings.load(FabricLoader.getInstance().getConfigDirectory().toPath().resolve("voidfog.json"));
        pointlessButton = PointlessButtonSettings.load(FabricLoader.getInstance().getConfigDirectory().toPath().resolve("pointless-button.json"));
        ClientTickCallback.EVENT.register(this::onTick);
        VoidFog.LOGGER.info("[Worse Void Fog] Your game is now worse!");
    }

    private void onTick(MinecraftClient client) {
        if (!client.isPaused() && client.world != null && client.getCameraEntity() != null) {
            if (config.enabled) {
                particleSpawner.update(client.world, client.getCameraEntity());

                /**if (config.imABigBoi) {
                    particleSpawner.updateBigBoi(client.world, client.getCameraEntity());
                }**/
            }
        }
    }
}
