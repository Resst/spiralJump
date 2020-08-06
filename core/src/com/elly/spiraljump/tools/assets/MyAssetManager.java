package com.elly.spiraljump.tools.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class MyAssetManager {

    private AssetManager manager;
    public PlanetAssets planet;
    public ObstacleAssets obstacles;

    public MyAssetManager(){
        manager = new AssetManager();
        planet = new PlanetAssets(manager);
        obstacles = new ObstacleAssets(manager);

        manager.finishLoading();
    }

    public Texture getTexture(String fileName){
        return manager.get(fileName);
    }

    public void dispose(){
        manager.dispose();
    }
}
