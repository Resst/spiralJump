package com.elly.spiraljump.tools.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class MyAssetManager {

    private AssetManager manager;

    public MyAssetManager(){
        manager = new AssetManager();

        manager.finishLoading();
    }

    public Texture getTexture(String fileName){
        return manager.get(fileName);
    }
}
