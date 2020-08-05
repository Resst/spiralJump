package com.elly.spiraljump.gameplay;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.elly.spiraljump.gameplay.Levels.Level;
import com.elly.spiraljump.tools.Constants;

public class Planet {


    private Sprite sprite;
    private Body body;
    private Level level;

    public static final float RADIUS = 100 * Constants.SCALE;



    public Planet(Level level){
        this.level = level;

        definePlanet();

        sprite = new Sprite(level.getScreen().getGame().getManager().planet.getTexture());
        sprite.setSize(RADIUS * 2, RADIUS * 2);
        sprite.setOrigin(RADIUS, RADIUS);
        sprite.setOriginBasedPosition(body.getPosition().x, body.getPosition().y);

    }

    public void draw(SpriteBatch batch){
        sprite.draw(batch);
    }

    public void update(float dt){

        body.setAngularVelocity(300 * dt);
        sprite.setRotation((float) Math.toDegrees(body.getAngle()));

    }



    private void definePlanet(){
        BodyDef bdef = new BodyDef();
        bdef.allowSleep = false;
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(100 * Constants.SCALE, 100 * Constants.SCALE);
        body = level.getWorld().createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(RADIUS);
        fdef.shape = shape;
        body.createFixture(fdef);

    }


}
