package com.elly.spiraljump.tools.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.HashMap;

public class AssetAnalyser {

    public static HashMap<String, Animation<TextureRegion>> parseAnimated(Object o){
        HashMap<String, Animation<TextureRegion>> map = new HashMap<>();

        Class clas = o.getClass();
        Field[] fields = clas.getFields();

        for (Field f:fields) {
            if (f.isAnnotationPresent(Animated.class)){
                try{
                    Animated animated = f.getAnnotation(Animated.class);

                    //getting animation information
                    Texture texture = new Texture(animated.folder() + f.get(o));
                    int frameCount = animated.frames() == 0 ? texture.getWidth() / animated.width() : animated.frames();
                    int frameWidth = animated.width() == 0 ? texture.getWidth() / animated.frames() : animated.width();
                    int frameHeight = texture.getHeight();

                    //adding frames
                    Array<TextureRegion> frames = new Array<>();
                    for(int i = 0; i < frameCount; i++){
                        frames.add(new TextureRegion(texture, i * frameWidth, 0, frameWidth, frameHeight));
                    }
                    Animation<TextureRegion> animation = new Animation<>(1,frames);

                    //ending
                    frames.clear();
                    map.put(f.get(o).toString(), animation);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }

    public static void parseAssets(AssetManager manager, Object o){
        Class clas = o.getClass();
        Field[] fields = clas.getFields();
        for (Field f: fields){
            if (f.isAnnotationPresent(Asset.class)){
                try {
                    Asset asset = f.getAnnotation(Asset.class);
                    manager.load(asset.folder() + f.get(o), asset.type());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

//for assets like textures, sounds, etc.
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Asset{
    Class type();

    String folder();
}

//only for texture animations
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Animated{
    String folder();

    int frames() default 0;

    int width() default 0;
}

