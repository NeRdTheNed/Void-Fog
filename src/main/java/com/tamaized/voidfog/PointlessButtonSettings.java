package com.tamaized.voidfog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

public class PointlessButtonSettings {
	
    private static final Gson newGson = new GsonBuilder()
            .setPrettyPrinting()
            .create();
	
	public boolean pointless = false;
	
	private transient Path path;

	public static PointlessButtonSettings load(Path path) {
	    if (Files.isReadable(path)) {
            try (BufferedReader s = Files.newBufferedReader(path)) {
            	PointlessButtonSettings result = newGson.fromJson(s, PointlessButtonSettings.class);

                if (result != null) {
                    return result.save(path);
                }
            } catch (IOException | JsonParseException e) {
                VoidFog.LOGGER.warn("Erorr whilst loading pointless config", e);
            }
        }
	    return new PointlessButtonSettings().save(path);
	}

	/**protected void validate() {
	    //voidParticleDensity = Math.max(0, voidParticleDensity);
	}**/

	private PointlessButtonSettings save(Path path) {
	    this.path = path;
	    //validate();
	    try (BufferedWriter writer = Files.newBufferedWriter(path)) {
	    	newGson.toJson(this, writer);
        } catch (IOException e) {
            VoidFog.LOGGER.warn("Error whilst saving pointless config", e);
        }
	    return this;
	}

	public void save() {
	    save(path);
	}
}
