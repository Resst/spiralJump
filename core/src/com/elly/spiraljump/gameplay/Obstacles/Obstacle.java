package com.elly.spiraljump.gameplay.Obstacles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.elly.spiraljump.gameplay.Planet;
import com.elly.spiraljump.tools.Constants;
import com.elly.spiraljump.tools.assets.ObstacleAssets;

public abstract class Obstacle {

    protected Planet planet;
    protected Body body;
    protected Sprite sprite;
    protected ObstacleAssets assets;

    protected Vector2 size;

    private final float STANDARD_SIZE = 1;

    private final float startAngle = -90;

    public Obstacle(Planet planet){
        this.planet = planet;
        assets = planet.getLevel().getScreen().getGame().getManager().obstacles;
        size = new Vector2(STANDARD_SIZE, STANDARD_SIZE);

        defineObstacle();
        defineSprite();
    }

    public void draw(SpriteBatch batch){
        sprite.draw(batch);
    }

    public void update(float dt){
        body.setAngularVelocity(((float) Math.toRadians(planet.getRotationSpeed())));
        sprite.rotate(planet.getRotationSpeed() * dt);
    }

    public void defineObstacle(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(planet.getCenter());
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.allowSleep = false;
        bdef.angle = ((float) Math.toRadians(startAngle));
        body = planet.getLevel().getWorld().createBody(bdef);

        Fixture fixture = defineCollider();
        fixture.setUserData("obstacle");
        Filter filter = new Filter();
        filter.categoryBits = Constants.OBSTACLE_BIT;
        filter.maskBits = Constants.PLAYER_BIT;
        fixture.setFilterData(filter);
    }

    public void defineSprite(){
        setUpSprite();

        sprite.setSize(size.x, size.y);
        sprite.setOrigin(size.x / 2, -Planet.RADIUS);
        sprite.setOriginBasedPosition(planet.getCenter().x, planet.getCenter().y);
        sprite.setRotation(startAngle);
    }

    public abstract Fixture defineCollider();
    public abstract void setUpSprite();


}
