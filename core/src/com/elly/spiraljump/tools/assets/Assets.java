package com.elly.spiraljump.tools.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

public abstract class Assets {


    protected HashMap<String, Animation<TextureRegion>> animations;
    protected AssetManager manager;

    public Assets(AssetManager manager){
        this.manager = manager;
        AssetAnalyser.parseAssets(manager, this);
        animations = AssetAnalyser.parseAnimated(this);
    }

    public Animation<TextureRegion> getAnimation(String key){
        return animations.get(key);
    }

}
