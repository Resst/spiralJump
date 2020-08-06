package com.elly.spiraljump.gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.elly.spiraljump.gameplay.Levels.Level;
import com.elly.spiraljump.gameplay.Obstacles.Obstacle;
import com.elly.spiraljump.gameplay.Obstacles.Triangle;
import com.elly.spiraljump.tools.Constants;
import com.elly.spiraljump.tools.assets.PlanetAssets;

public class Planet {


    private Sprite sprite;
    private Body body;
    private Level level;

    Array<Obstacle> obstacles = new Array<>();

    private float rotationSpeed = 5;

    private float radius = Constants.WIDTH_IN_CELLS / 2 * 2.6131f;



    public Planet(Level level){
        this.level = level;

        definePlanet();
        defineSprite();

    }

    public void draw(SpriteBatch batch){
        sprite.draw(batch);
        for (Obstacle o : obstacles)
            o.draw(batch);
    }

    public void update(float dt){
        sprite.rotate(rotationSpeed * dt);

        for (Obstacle o : obstacles)
            o.update(dt);

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
            obstacles.add(new Triangle(this));

    }

    public Vector2 getCenter(){
        return body.getPosition();
    }

    public float getRadius(){
        return radius;
    }

    public float getRotationSpeed() {
        return rotationSpeed;
    }

    public Level getLevel() {
        return level;
    }

    private void definePlanet(){
        BodyDef bdef = new BodyDef();
        bdef.allowSleep = false;
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set(level.getScreen().getCamera().position.x - Constants.WIDTH_IN_CELLS / 4,
                level.getScreen().getCamera().position.y - Constants.HEIGHT_IN_CELLS / 2 - Constants.WIDTH_IN_CELLS / 2 * 2.4142f + Constants.WIDTH_IN_CELLS / 8);
        body = level.getWorld().createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        EdgeShape shape = new EdgeShape();
        shape.set(-Constants.WIDTH_IN_CELLS / 2, radius, Constants.WIDTH_IN_CELLS / 2, radius);
        fdef.shape = shape;
        fdef.filter.categoryBits = Constants.GROUND_BIT;
        fdef.filter.maskBits = Constants.PLAYER_BIT;
        body.createFixture(fdef).setUserData("ground");

    }

    private void defineSprite(){
        sprite = new Sprite(level.getScreen().getGame().getManager().planet.getTexture(PlanetAssets.PLANET));
        sprite.setSize(radius * 2, radius * 2);
        sprite.setOrigin(radius, radius);
        sprite.setOriginBasedPosition(body.getPosition().x, body.getPosition().y);
    }


}
