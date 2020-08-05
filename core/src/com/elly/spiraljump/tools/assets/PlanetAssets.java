package com.elly.spiraljump.tools.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

import java.util.concurrent.ForkJoinPool;

public class PlanetAssets extends Assets {

    public static final String FOLDER = "planets/";

    @Asset(type = Texture.class, folder = FOLDER)
    public static final String PLANET = "planet.png";

    public PlanetAssets(AssetManager manager) {
        super(manager);
    }

    public Texture getTexture(){
        return manager.get(FOLDER + PLANET);
    }



}
