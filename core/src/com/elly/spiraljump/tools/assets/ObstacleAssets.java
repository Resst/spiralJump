package com.elly.spiraljump.tools.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class ObstacleAssets extends Assets {

    private static final String FOLDER = "obstacles/";

    @Asset(type = Texture.class, folder = FOLDER)
    public static final String
            SPIKES = "spikes.png";


    public ObstacleAssets(AssetManager manager) {
        super(manager);
    }

    @Override
    public String getFolder() {
        return FOLDER;
    }


}
