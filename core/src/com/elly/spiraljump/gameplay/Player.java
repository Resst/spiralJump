package com.elly.spiraljump.gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.elly.spiraljump.gameplay.Levels.Level;
import com.elly.spiraljump.tools.Constants;
import com.elly.spiraljump.tools.assets.PlanetAssets;

public class Player {

    private Level level;

    private Body body;
    private Vector2 size;
    private Sprite sprite;

    public boolean canJump = false;

    public Player(Level level){
        this.level = level;

        size = new Vector2(1, 1);
        definePlayer();
        defineSprite();
    }

    public void update(float dt){
        sprite.setOriginBasedPosition(body.getPosition().x, body.getPosition().y);

        if (Gdx.input.isKeyPressed(Input.Keys.W) && canJump)
            jump();
    }

    public void draw(SpriteBatch batch){
        sprite.draw(batch);
    }

    public void jump(){
        body.applyLinearImpulse(0, 10, body.getPosition().x, body.getPosition().y, true);
        canJump = false;
    }



    public void definePlayer(){
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.allowSleep = false;
        bdef.position.set(level.getPlanet().getCenter().x, level.getPlanet().getCenter().y + level.getPlanet().getRadius() + size.y / 2);
        bdef.gravityScale = 5;
        body = level.getPlanet().getLevel().getWorld().createBody(bdef);
        body.setUserData(this);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(size.x / 2, size.y / 2);
        fdef.shape = shape;
        fdef.filter.categoryBits = Constants.PLAYER_BIT;
        fdef.filter.maskBits = Constants.GROUND_BIT | Constants.OBSTACLE_BIT;
        body.createFixture(fdef);
    }

    public void defineSprite(){
        sprite = new Sprite(level.getPlanet().getLevel().getScreen().getGame().getManager().planet.getTexture(PlanetAssets.PLANET));

        sprite.setSize(size.x, size.y);
        sprite.setOrigin(size.x / 2, size.y / 2);
    }




}
