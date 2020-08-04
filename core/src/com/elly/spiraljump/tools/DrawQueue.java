package com.elly.spiraljump.tools;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class DrawQueue extends Array<Array<Drawable>>{

    private int maxLayer = -1;

    public static final int
            UI = 0,
            FOREGROUND = 1,
            ENTITIES = 2,
            GROUND = 3,
            BACKGROUND = 4;

    public void add(Drawable object, int layer){
        while (layer > maxLayer){
            super.add(new Array<Drawable>());
            maxLayer++;
        }
        this.get(layer).add(object);
    }

    public void draw(SpriteBatch batch){
        for (int i = this.maxLayer; i >= 0; i--){
            for (Drawable o : this.get(i)){
                batch.begin();
                o.draw(batch);
                batch.end();
            }
        }

        if (!isEmpty() && get(maxLayer).isEmpty()){
            this.removeIndex(maxLayer);
            maxLayer--;
        }
    }

    @Override
    public void clear(){
        super.clear();
        maxLayer = -1;
    }

}
